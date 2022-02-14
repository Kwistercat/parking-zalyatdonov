package com.zalyatdinov.parking.controllers;


import com.zalyatdinov.parking.domain.dto.CarDto;
import com.zalyatdinov.parking.domain.entity.Car;
import com.zalyatdinov.parking.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/cars")
    public List<Car> findAll() {
        return carService.findAll();
    }

    @PostMapping("/cars")
    public Car createCar(@RequestBody CarDto carDto) {
        return carService.saveCar(carDto);
    }

    @PutMapping("/cars/{car-id}")
    public Car updateCar(@PathVariable("car-id") Long id, @RequestBody CarDto carDto) {
        return carService.updateCar(id, carDto);
    }

    @DeleteMapping("/cars/{car-id}")
    public void deleteCar(@PathVariable("car-id") Long id) {
        carService.deleteCar(id);
    }

}
