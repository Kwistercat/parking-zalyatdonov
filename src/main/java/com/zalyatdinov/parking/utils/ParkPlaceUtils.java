package com.zalyatdinov.parking.utils;

import com.zalyatdinov.parking.domain.entity.Car;
import com.zalyatdinov.parking.domain.entity.ParkPlace;
import com.zalyatdinov.parking.domain.entity.ParkStatus;
import com.zalyatdinov.parking.domain.entity.PayStatus;

public class ParkPlaceUtils {

    public static void freeParkPlace(ParkPlace parkPlace) {
        parkPlace.setParkStatus(ParkStatus.FREE);
        parkPlace.setPayStatus(PayStatus.NOT_PAID);
        parkPlace.setStateNumber(null);
    }

    public static void takeParkPlace(Car car, ParkPlace parkPlace) {
        parkPlace.setParkStatus(ParkStatus.BUSY);
        parkPlace.setPayStatus(PayStatus.NOT_PAID);
        parkPlace.setStateNumber(car.getStateNumber());
    }

}
