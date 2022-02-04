package com.zalyatdinov.parking.entities;

public class Car {

    private String carModel;
    private String carMark;
    private String stateNumber;
    private String color;
    private String photo;
    private ParkNumber parkNumber;

    public Car(String carModel, String carMark, String stateNumber, String color, String photo, ParkNumber parkNumber) {
        this.carModel = carModel;
        this.carMark = carMark;
        this.stateNumber = stateNumber;
        this.color = color;
        this.photo = photo;
        this.parkNumber = parkNumber;
    }

    public ParkNumber getParkNumber() {
        return parkNumber;
    }

    public void setParkNumber(ParkNumber parkNumber) {
        this.parkNumber = parkNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarMark() {
        return carMark;
    }

    public void setCarMark(String carMark) {
        this.carMark = carMark;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
