package com.framework.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationFrontDto {
    @JsonProperty("reservationId")
    private Long id;
    private String client;
    private Integer nbPeople;
    private String dateheure;
    private HotelDto hotel;
}
