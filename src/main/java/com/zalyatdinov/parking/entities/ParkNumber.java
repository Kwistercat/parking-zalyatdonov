package com.zalyatdinov.parking.entities;

public class ParkNumber {
    private int number;
    private String stateNumber;
    private String parkStatus;
    private String parkPayStatus;

    public ParkNumber(int number, String stateNumber, String parkStatus, String parkPayStatus) {
        this.number = number;
        this.stateNumber = stateNumber;
        this.parkStatus = parkStatus;
        this.parkPayStatus = parkPayStatus;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStateNumber() {
        return stateNumber;
    }

    public void setStateNumber(String stateNumber) {
        this.stateNumber = stateNumber;
    }

    public String getParkStatus() {
        return parkStatus;
    }

    public void setParkStatus(String parkStatus) {
        this.parkStatus = parkStatus;
    }

    public String getParkPayStatus() {
        return parkPayStatus;
    }

    public void setParkPayStatus(String parkPayStatus) {
        this.parkPayStatus = parkPayStatus;
    }

}
