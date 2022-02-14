package com.zalyatdinov.parking.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkPlaceDto {
    private Long id;
    private int number;
    private String stateNumber;
    private String parkStatus;
    private String parkPayStatus;
}
