package com.example.schuller.carscharging.api;

import com.example.schuller.carscharging.model.Station;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface StationsApi {

    @GET("/Stations.json")
    Single<List<Station>> getStations();
}
