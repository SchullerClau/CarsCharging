package com.example.schuller.carscharging.remotedatasource;

import com.example.schuller.carscharging.model.Station;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Client
 * Created by paul on 23/05/2018.
 */


public interface Client {

    @GET("/Stations")
    Call<List<Station>> getAllStationsFromFirebase();
}
