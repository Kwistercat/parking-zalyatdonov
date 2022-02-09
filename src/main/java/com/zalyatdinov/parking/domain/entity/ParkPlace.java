package com.zalyatdinov.parking.domain.entity;

import com.zalyatdinov.parking.domain.dto.CarDto;
import com.zalyatdinov.parking.domain.dto.ParkPlaceDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class ParkPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private int number;

    private String stateNumber;

    @Enumerated(value = EnumType.STRING)
    private ParkStatus parkStatus;

    @Enumerated(value = EnumType.STRING)
    private PayStatus payStatus;

    public static ParkPlace from(ParkPlaceDto dto) {
        ParkPlace parkPlace = new ParkPlace();
        BeanUtils.copyProperties(dto, parkPlace);
        return parkPlace;
    }
}
