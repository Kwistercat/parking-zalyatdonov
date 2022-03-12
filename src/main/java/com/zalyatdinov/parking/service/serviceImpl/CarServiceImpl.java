package com.zalyatdinov.parking.service.serviceImpl;


import com.zalyatdinov.parking.domain.dto.CarDto;
import com.zalyatdinov.parking.domain.dto.ParkPlaceDto;
import com.zalyatdinov.parking.domain.entity.Car;
import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import com.zalyatdinov.parking.domain.entity.PayStatus;
import com.zalyatdinov.parking.exception.NotFoundException;
import com.zalyatdinov.parking.repositories.CarsRepository;
import com.zalyatdinov.parking.repositories.ParkRepository;
import com.zalyatdinov.parking.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarsRepository carsRepository;
    private final ParkRepository parkRepository;
    private final JmsTemplate jmsTemplate;

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
        Car car = new Car(carDto);
        if (carDto.getParkPlaceDto() != null) {
            car.setParkPlace(new ParkPlace(carDto.getParkPlaceDto()));
        }
        jmsTemplate.convertAndSend("my_topic", parkRepository.countAllByParkStatus(ParkStatus.FREE));
        System.out.println("Message sent to Topic");
        return carsRepository.save(car);
    }

    @Override
    public void setParkPlace(Long carId, ParkPlaceDto parkPlaceDto) {
        Optional<Car> carOptional = carsRepository.findById(carId);
        ParkPlace parkPlace = new ParkPlace(parkPlaceDto);
        if (parkRepository.existsById(parkPlace.getId())) {
            carOptional.ifPresent(car -> {
                if (parkPlace.getParkStatus().equals(ParkStatus.FREE)) {
                    car.setParkPlace(parkPlace);
                    parkRepository.save(parkPlace);
                } else throw new NotFoundException("место занято");
            });
        } else throw new NotFoundException("No such park ");
    }

    @Override
    public Car updateCar(Long carId, CarDto carDto) {
        if (carsRepository.existsById(carId)) {
            Car car = new Car(carDto);
            return carsRepository.save(car);
        } else throw new NotFoundException("No such car " + carId);
    }

    @Override
    public void deleteCar(Long carId) {
        Optional<Car> carOptional = carsRepository.findById(carId);
        carOptional.ifPresent(car -> {
            ParkPlace parkPlace = car.getParkPlace();
            if (parkPlace != null) {
                freeParkPlace(parkPlace);
                parkRepository.save(parkPlace);
            }
            carsRepository.delete(car);
        });
    }

    private static void freeParkPlace(ParkPlace parkPlace) {
        parkPlace.setParkStatus(ParkStatus.FREE);
        parkPlace.setPayStatus(PayStatus.NOT_PAID);
        parkPlace.setStateNumber(null);
    }

    private static void takeParkPlace(Car car, ParkPlace parkPlace) {
        parkPlace.setParkStatus(ParkStatus.BUSY);
        parkPlace.setPayStatus(PayStatus.NOT_PAID);
        parkPlace.setStateNumber(car.getStateNumber());
    }
}
