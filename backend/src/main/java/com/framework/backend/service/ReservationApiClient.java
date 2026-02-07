package com.framework.backend.service;

import com.framework.backend.dto.ApiResponse;
import com.framework.backend.dto.ReservationFrontDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
public class ReservationApiClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ReservationApiClient(RestTemplate restTemplate,
                                @Value("${backoffice.api.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public List<ReservationFrontDto> getReservationsFromBackOffice() {
        String url = baseUrl + "/reservations";
        ResponseEntity<ApiResponse<ReservationFrontDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse<ReservationFrontDto>>() {}
        );
        
        ApiResponse<ReservationFrontDto> body = response.getBody();
        return (body != null && body.getData() != null) ? body.getData() : Collections.emptyList();
    }

    public List<ReservationFrontDto> getReservationsByDate(LocalDate date) {
        // Récupérer toutes les réservations
        List<ReservationFrontDto> allReservations = getReservationsFromBackOffice();
        
        // Si aucune date n'est spécifiée, retourner toutes les réservations
        if (date == null) {
            return allReservations;
        }
        
        // Filtrer côté Java car l'API ne supporte pas le filtre par date
        return allReservations.stream()
                .filter(reservation -> {
                    if (reservation.getDateheure() == null) {
                        return false;
                    }
                    // Format: "2026-02-07 18:12:00.0"
                    String dateStr = reservation.getDateheure().substring(0, 10);
                    return dateStr.equals(date.toString());
                })
                .collect(java.util.stream.Collectors.toList());
    }
}
