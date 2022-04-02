package com.zalyatdinov.parking.domain.entity;

import com.zalyatdinov.parking.domain.dto.ParkPlaceDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;

@Entity
@Table(name = "parkplace")
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

    public ParkPlace(ParkPlaceDto dto) {
        try {
            BeanUtils.copyProperties(this, dto);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
