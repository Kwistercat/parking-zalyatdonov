package com.zalyatdinov.parking.service.serviceImpl;

import com.zalyatdinov.parking.domain.dto.ParkPlaceDto;
import com.zalyatdinov.parking.domain.entity.Car;
import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import com.zalyatdinov.parking.domain.entity.PayStatus;
import com.zalyatdinov.parking.exception.NotFoundException;
import com.zalyatdinov.parking.repositories.ParkRepository;
import com.zalyatdinov.parking.service.ParkPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkPlaceServiceImpl implements ParkPlaceService {
    private final ParkRepository parkRepository;

    @Override
    public ParkPlace saveParkPlace(ParkPlaceDto parkPlaceDto) {
        ParkPlace parkPlace = new ParkPlace(parkPlaceDto);
        return parkRepository.save(parkPlace);
    }

    @Override
    public List<ParkPlace> findAll() {
        return parkRepository.findAll();
    }

    @Override
    public void deleteParkPlace(Long parkId) {
        Optional<ParkPlace> parkPlaceOptional = parkRepository.findById(parkId);
        parkPlaceOptional.ifPresent(parkPlace -> {
                    if (parkPlace.getParkStatus() == ParkStatus.BUSY) {
                        throw new NotFoundException("место занято, нельзя удалить ");
                    } else parkRepository.delete(parkPlace);
                }
        );
        parkPlaceOptional.orElseThrow(() -> new NotFoundException("No such park " + parkId));
    }

    @Override
    public ParkPlace updateParkStatus(ParkStatus parkStatus, Long parkId) {
        Optional<ParkPlace> parkPlaceOptional = parkRepository.findById(parkId);
        ParkPlace parkPlace = parkPlaceOptional.orElseThrow(() -> new NotFoundException("No such park " + parkId));
        parkPlace.setParkStatus(parkStatus);
        return parkRepository.save(parkPlace);
    }

    @Override
    public ParkPlace updatePayStatus(PayStatus payStatus, Long parkId) {
        Optional<ParkPlace> parkPlaceOptional = parkRepository.findById(parkId);
        ParkPlace parkPlace = parkPlaceOptional.orElseThrow(() -> new NotFoundException("No such park " + parkId));
        parkPlace.setPayStatus(payStatus);
        return parkRepository.save(parkPlace);
    }
}
