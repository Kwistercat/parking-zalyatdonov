package com.zalyatdinov.parking.controllers;


import com.zalyatdinov.parking.model.Car;
import com.zalyatdinov.parking.services.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {

    private CarService carService;

    @GetMapping("/cars")
    public String findAll() {
        List<Car> cars = carService.getAllCars();
        return "cars";
    }
}
