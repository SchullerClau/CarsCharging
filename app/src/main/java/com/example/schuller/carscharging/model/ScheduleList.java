package com.example.schuller.carscharging.model;

/**
 * Created by schuller on 6/20/18.
 */

public class ScheduleList {
    String data;
    String carId;
    String stationEmail;
    String ora;

    public ScheduleList(){

    }

    public ScheduleList(String data, String carId, String stationEmail, String ora) {
        this.data = data;
        this.carId = carId;
        this.stationEmail = stationEmail;
        this.ora = ora;
    }

    public String getData() {
        return data;
    }

    public String getCarId() {
        return carId;
    }

    public String getStationEmail() {
        return stationEmail;
    }

    public String getOra() {
        return ora;
    }
}
