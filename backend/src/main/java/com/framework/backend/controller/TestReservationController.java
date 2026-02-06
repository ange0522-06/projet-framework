package com.framework.backend.controller;

import com.framework.backend.dto.HotelDto;
import com.framework.backend.dto.ReservationFrontDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Contrôleur de TEST avec données mockées
 * Permet de tester l'application SANS le back-office
 * 
 * URL de test : http://localhost:8080/backend/test/reservations
 */
@Controller
@RequestMapping("/test")
public class TestReservationController {

    @GetMapping("/reservations")
    public String showTestReservations(Model model) {
        List<ReservationFrontDto> reservations = createMockData();
        model.addAttribute("reservations", reservations);
        model.addAttribute("testMode", true);
        return "listeReservation";
    }

    /**
     * Crée des données de test
     */
    private List<ReservationFrontDto> createMockData() {
        List<ReservationFrontDto> mockData = new ArrayList<>();

        // Réservation 1
        HotelDto hotel1 = new HotelDto();
        hotel1.setId(1L);
        hotel1.setName("Hôtel Royal Paris");
        hotel1.setAddresse("123 Rue de Rivoli, Paris");
        hotel1.setPhone("+33 1 42 60 34 12");
        
        ReservationFrontDto r1 = new ReservationFrontDto();
        r1.setId(1L);
        r1.setClient("Martin Dupont");
        r1.setNbPeople(2);
        r1.setDateheure("2026-02-15 14:00");
        r1.setHotel(hotel1);
        mockData.add(r1);

        // Réservation 2
        HotelDto hotel2 = new HotelDto();
        hotel2.setId(2L);
        hotel2.setName("Grand Hôtel de Lyon");
        hotel2.setAddresse("45 Avenue Jean Jaurès, Lyon");
        hotel2.setPhone("+33 4 72 56 78 90");
        
        ReservationFrontDto r2 = new ReservationFrontDto();
        r2.setId(2L);
        r2.setClient("Sophie Bernard");
        r2.setNbPeople(4);
        r2.setDateheure("2026-03-10 18:30");
        r2.setHotel(hotel2);
        mockData.add(r2);

        // Réservation 3
        HotelDto hotel3 = new HotelDto();
        hotel3.setId(3L);
        hotel3.setName("Hôtel de la Plage");
        hotel3.setAddresse("78 Promenade des Anglais, Nice");
        hotel3.setPhone("+33 4 93 87 65 43");
        
        ReservationFrontDto r3 = new ReservationFrontDto();
        r3.setId(3L);
        r3.setClient("Pierre Moreau");
        r3.setNbPeople(3);
        r3.setDateheure("2026-01-20 12:00");
        r3.setHotel(hotel3);
        mockData.add(r3);

        return mockData;
    }
}
