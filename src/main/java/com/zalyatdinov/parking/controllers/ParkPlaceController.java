package com.zalyatdinov.parking.controllers;

import com.zalyatdinov.parking.domain.dto.CarDto;
import com.zalyatdinov.parking.domain.dto.ParkPlaceDto;
import com.zalyatdinov.parking.domain.entity.Car;
import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import com.zalyatdinov.parking.domain.entity.PayStatus;
import com.zalyatdinov.parking.service.ParkPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/places/{place-id}")
    public void deleteParkPlace(@PathVariable("place-id") Long id) {
        parkPlaceService.deleteParkPlace(id);
    }

    @PostMapping("/places")
    public ParkPlace createParkPlace(@RequestBody ParkPlaceDto parkPlaceDto) {
        return parkPlaceService.saveParkPlace(parkPlaceDto);
    }

}
