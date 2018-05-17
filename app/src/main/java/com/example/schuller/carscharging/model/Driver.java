package com.example.schuller.carscharging.model;

/**
 * Model
 * Created by schuller on 4/10/18.
 */

public class Driver {

    private String name;
    private String password;
    private String carId;
    private String phoneNumber;

    public Driver() {
    }

    public Driver(String name, String password, String carId, String phoneNumber) {
        this.name = name;
        this.password = password;
        this.carId = carId;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
