package com.zalyatdinov.parking.services;


import com.zalyatdinov.parking.model.Car;
import com.zalyatdinov.parking.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private CarsRepository carsRepository;

    @Autowired
    public void setCarsRepository(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    public Car getById(Long id) {
        return carsRepository.getById(id);
    }

    public List<Car> getAllCars() {
        return carsRepository.findAll();
    }

    public void deleteById(Long id) {
        carsRepository.deleteById(id);
    }

    public void add(Car car) {
        carsRepository.save(car);
    }
}
