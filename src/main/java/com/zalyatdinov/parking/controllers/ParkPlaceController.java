package com.zalyatdinov.parking.controllers;

import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import com.zalyatdinov.parking.domain.entity.PayStatus;
import com.zalyatdinov.parking.service.ParkPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParkPlaceController {
    private final ParkPlaceService parkPlaceService;

    @PostMapping("/places/{park-id}/parkStatus")
    public ParkPlace updateParkStatus(@PathVariable("park-id") Long parkId, @RequestBody ParkStatus status) {
        return parkPlaceService.updateParkStatus(status, parkId);
    }

    @PostMapping("/places/{park-id}/payStatus")
    public ParkPlace updatePayStatus(@PathVariable("park-id") Long parkId, @RequestBody PayStatus payStatus) {
        return parkPlaceService.updatePayStatus(payStatus, parkId);
    }
}
