package com.zalyatdinov.parking.domain.dto;

import com.zalyatdinov.parking.domain.entity.ParkPlace;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

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
