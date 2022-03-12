package com.zalyatdinov.parking.domain.entity;

import com.zalyatdinov.parking.domain.dto.CarDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

@Entity
@Table
@Data
@NoArgsConstructor
public class Car implements Serializable {
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

    public Car(CarDto dto) {
        if (dto.getId() == null) {
            dto.setId(0L);
        }
        try {
            BeanUtils.copyProperties(this, dto);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
