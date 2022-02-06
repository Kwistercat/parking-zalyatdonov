package com.zalyatdinov.parking.model;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column
    private String model;

    @Column
    private String mark;

    @Column
    private String stateNumber;

    @Column
    private String color;

    @Column
    private String photo;


    @OneToOne
    private ParkPlace parkPlace;

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ParkPlace getParkPlace() {
        return parkPlace;
    }

    public void setParkPlace(ParkPlace parkPlace) {
        this.parkPlace = parkPlace;
    }

    public String getCarModel() {
        return model;
    }

    public void setCarModel(String model) {
        this.model = model;
    }

    public String getCarMark() {
        return mark;
    }

    public void setCarMark(String mark) {
        this.mark = mark;
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