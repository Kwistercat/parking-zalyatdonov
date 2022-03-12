package com.zalyatdinov.parking.controllers;

import com.zalyatdinov.parking.domain.dto.ParkPlaceDto;
import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import com.zalyatdinov.parking.domain.entity.PayStatus;
import com.zalyatdinov.parking.service.ParkPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParkPlaceController {
    private final ParkPlaceService parkPlaceService;

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MANAGER')")
    @PostMapping("/places/{park-id}/parkStatus")
    public ParkPlace updateParkStatus(@PathVariable("park-id") Long parkId, @RequestBody ParkStatus status) {
        return parkPlaceService.updateParkStatus(status, parkId);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MANAGER')")
    @PostMapping("/places/{park-id}/payStatus")
    public ParkPlace updatePayStatus(@PathVariable("park-id") Long parkId, @RequestBody PayStatus payStatus) {
        return parkPlaceService.updatePayStatus(payStatus, parkId);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MANAGER')")
    @DeleteMapping("/places/{place-id}")
    public void deleteParkPlace(@PathVariable("place-id") Long id) {
        parkPlaceService.deleteParkPlace(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MANAGER')")
    @PostMapping("/places")
    public ParkPlace createParkPlace(@RequestBody ParkPlaceDto parkPlaceDto) {
        return parkPlaceService.saveParkPlace(parkPlaceDto);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MANAGER')")
    @GetMapping("/places")
    public List<ParkPlace> findAll() {
        return parkPlaceService.findAll();
    }


}
