package com.framework.backend.controller;

import com.framework.backend.model.ReservationTemp;
import com.framework.backend.service.ReservationTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationTempApiController {

    @Autowired
    private ReservationTempService reservationTempService;

    @GetMapping
    public ResponseEntity<List<ReservationTemp>> listReservations(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<ReservationTemp> reservations = reservationTempService.listReservations(startDate, endDate);
        return ResponseEntity.ok(reservations);
    }
}
