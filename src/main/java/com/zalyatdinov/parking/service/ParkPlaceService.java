package com.zalyatdinov.parking.service;

import com.zalyatdinov.parking.domain.dto.ParkPlaceDto;
import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import com.zalyatdinov.parking.domain.entity.PayStatus;
import org.springframework.stereotype.Service;


public interface ParkPlaceService {

    ParkPlace saveParkPlace(ParkPlaceDto parkPlaceDto);

    void deleteParkPlace(Long parkId);

    ParkPlace updateParkStatus(ParkStatus parkStatus, Long parkId);

    ParkPlace updatePayStatus(PayStatus payStatus, Long parkId);

}
