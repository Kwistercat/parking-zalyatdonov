package com.zalyatdinov.parking.controllers;


import com.zalyatdinov.parking.domain.entity.Car;
import com.zalyatdinov.parking.service.serviceImpl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarController {
    private CarServiceImpl carServiceImpl;

    @Autowired
    public void setCarServiceImpl(CarServiceImpl carServiceImpl) {
        this.carServiceImpl = carServiceImpl;
    }


    @GetMapping("/cars")
    public String findAll() {
        List<Car> cars = carServiceImpl.findAll();
        return "cars";
    }

    public String createCar(Model model) {
        return null;
    }


    //Создание автомобиля;
    // • Просмотр списка автомобилей;
    // • Редактирование автомобиля;
    // • Назначение парковочного места автомобилю;
    // • Удаление автомобиля;
}
