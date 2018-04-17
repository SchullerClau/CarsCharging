package com.example.schuller.carscharging.zModel;

/**
 * Model
 * Created by schuller on 4/10/18.
 */

public class Driver {
    private String email;
    private String password;
    private String carId;

    public Driver(){}

    public Driver(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
