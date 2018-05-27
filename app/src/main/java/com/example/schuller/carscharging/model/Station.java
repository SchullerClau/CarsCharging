package com.example.schuller.carscharging.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Model
 * Created by schuller on 4/12/18.
 */

@Entity(tableName = "stations")
public class Station {
    @PrimaryKey
    @NonNull
    private String email;
    private String password;
    private String stationId;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;

    public Station() {
    }

    public Station(@NonNull String email, String password, String stationId, String name, String address, Double latitude, Double longitude) {
        this.email = email;
        this.password = password;
        this.stationId = stationId;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
