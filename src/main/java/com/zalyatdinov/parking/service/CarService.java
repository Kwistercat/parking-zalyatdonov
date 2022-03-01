package com.zalyatdinov.parking.service;

import com.zalyatdinov.parking.domain.dto.CarDto;
import com.zalyatdinov.parking.domain.dto.ParkPlaceDto;
import com.zalyatdinov.parking.domain.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car findCarById(Long carId);

    Car saveCar(CarDto carDto);

    Car updateCar(Long carId, CarDto carDto);

    void deleteCar(Long carId);

    void setParkPlace(Long carId, ParkPlaceDto parkPlaceDto);

}
