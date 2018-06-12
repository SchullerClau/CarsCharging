package com.example.schuller.carscharging.model;

/**
 * Model
 * Created by schuller on 4/10/18.
 */

public class Driver {
    private String email;
    private String name;
    private String carId;
    private String phoneNumber;

    public Driver() {
    }

    public Driver(String email, String name, String carId, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.carId = carId;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
