package com.framework.backend.controller;

import com.framework.backend.model.ReservationTemp;
import com.framework.backend.service.ReservationTempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationTempViewController {

    @Autowired
    private ReservationTempService reservationTempService;

    @GetMapping
    public String listReservations(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            Model model) {

        List<ReservationTemp> reservations = reservationTempService.listReservations(startDate, endDate);
        model.addAttribute("reservations", reservations);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "reservations/list";
    }
}
