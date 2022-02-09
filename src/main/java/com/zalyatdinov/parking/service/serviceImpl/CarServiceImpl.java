package com.zalyatdinov.parking.service.serviceImpl;


import com.zalyatdinov.parking.domain.dto.CarDto;
import com.zalyatdinov.parking.domain.entity.Car;
import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import com.zalyatdinov.parking.exception.NotFoundException;
import com.zalyatdinov.parking.repositories.CarsRepository;
import com.zalyatdinov.parking.repositories.ParkRepository;
import com.zalyatdinov.parking.service.CarService;
import com.zalyatdinov.parking.utils.ParkPlaceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    private CarsRepository carsRepository;
    private ParkRepository parkRepository;

    @Autowired
    public void setParkRepository(ParkRepository parkRepository) {
        this.parkRepository = parkRepository;
    }

    @Autowired
    public void setCarsRepository(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Override
    public List<Car> findAll() {
        return carsRepository.findAll();
    }

    @Override
    public Car findCarById(Long carId) {
        Optional<Car> carOptional = carsRepository.findById(carId);
        return carOptional.orElseThrow(() -> new NotFoundException("No such car " + carId));
    }

    @Override
    public Car saveCar(CarDto carDto) {
        return carsRepository.save(Car.from(carDto));
    }

    @Override
    public Car updateCar(Long carId, CarDto carDto) {
        Optional<Car> carOptional = carsRepository.findById(carId);
        Car car = carOptional.orElseThrow(() -> new NotFoundException("No such car " + carId));
        car = Car.from(carDto);
        car.setId(carId);
        ParkPlace parkPlace = car.getParkPlace();
        if (parkPlace != null) {
            if (parkPlace.getParkStatus().equals(ParkStatus.BUSY)) {
                ParkPlaceUtils.freeParkPlace(parkPlace);
            } else {
                ParkPlaceUtils.takeParkPlace(car, parkPlace);
            }
            parkRepository.save(parkPlace);
        }
        return carsRepository.save(car);
    }

    @Override
    public void deleteCar(Long carId) {
        Optional<Car> carOptional = carsRepository.findById(carId);
        carOptional.ifPresent(car -> {
            Optional<ParkPlace> parkPlaceOptional = Optional.of(car.getParkPlace());
            parkPlaceOptional.ifPresent(parkPlace -> {
                ParkPlaceUtils.freeParkPlace(parkPlace);
                parkRepository.save(parkPlace);
            });
            carsRepository.delete(car);
        });
    }
}
