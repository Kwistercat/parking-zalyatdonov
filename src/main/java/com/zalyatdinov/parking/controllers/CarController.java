package com.zalyatdinov.parking.controllers;


import com.zalyatdinov.parking.domain.dto.CarDto;
import com.zalyatdinov.parking.domain.dto.ParkPlaceDto;
import com.zalyatdinov.parking.domain.entity.Car;
import com.zalyatdinov.parking.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final JmsTemplate jmsTemplate;

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MANAGER')")
    @GetMapping("/cars")
    public List<Car> findAll() {
        return carService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MANAGER')")
    @PostMapping("/cars")
    public Car createCar(@RequestBody CarDto carDto) {
        return carService.saveCar(carDto);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MANAGER')")
    @PutMapping("/cars/{car-id}")
    public Car updateCar(@PathVariable("car-id") Long id, @RequestBody CarDto carDto) {
        jmsTemplate.convertAndSend("sample.queue", carDto);
        System.out.println("Message sent to Queue");
        return carService.updateCar(id, carDto);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MANAGER')")
    @DeleteMapping("/cars/{car-id}")
    public void deleteCar(@PathVariable("car-id") Long id) {
        carService.deleteCar(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('MANAGER')")
    @PostMapping("/cars/{car-id}")
    public void setParkPlace(@PathVariable("car-id") Long id, @RequestBody ParkPlaceDto parkPlaceDto) {
        carService.setParkPlace(id, parkPlaceDto);
    }

}
