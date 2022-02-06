package com.zalyatdinov.parking.model;

import javax.persistence.*;

@Entity
@Table
public class ParkPlace {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private int number;

    @Column
    private String stateNumber;

    @Column
    private String parkStatus;

    @Column
    private String parkPayStatus;

    public ParkPlace() {
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
