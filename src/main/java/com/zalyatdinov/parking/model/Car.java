package com.zalyatdinov.parking.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String model;

    private String mark;

    private String stateNumber;

    private String color;

    private String photo;


    @OneToOne
    @JoinColumn(name = "place_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
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
