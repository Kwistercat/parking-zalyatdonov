package com.zalyatdinov.parking.domain.entity;

import com.zalyatdinov.parking.domain.dto.CarDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String model;

    private String mark;

    private String stateNumber;

    private String color;

    private String photo;

    @OneToOne
    private ParkPlace parkPlace;

    public static Car from(CarDto dto) {
        Car car = new Car();
        BeanUtils.copyProperties(dto, car);
        return car;
    }
}
