package com.framework.backend.service;

import com.framework.backend.model.ReservationTemp;
import com.framework.backend.repository.ReservationTempRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationTempService {

    @Autowired
    private ReservationTempRepository reservationTempRepository;

    public List<ReservationTemp> listReservations(LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;

        if (startDate != null) {
            startDateTime = startDate.atStartOfDay();
        }

        if (endDate != null) {
            endDateTime = endDate.plusDays(1).atStartOfDay().minusNanos(1);
        }

        return reservationTempRepository.findByDateRange(startDateTime, endDateTime);
    }

    public List<ReservationTemp> getAllReservations() {
        return reservationTempRepository.findAll();
    }
}
