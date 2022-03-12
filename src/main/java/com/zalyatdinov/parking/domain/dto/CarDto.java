package com.zalyatdinov.parking.domain.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = CarDto.class)
public class CarDto implements Serializable {
    private Long id;
    private String model;
    private String mark;
    private String stateNumber;
    private String color;
    private String photo;
    private ParkPlaceDto parkPlaceDto;
}
