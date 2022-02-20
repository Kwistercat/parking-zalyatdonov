package com.zalyatdinov.parking.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {
    private Long id;
    private String model;
    private String mark;
    private String stateNumber;
    private String color;
    private String photo;
    private ParkPlaceDto parkPlaceDto;
}
