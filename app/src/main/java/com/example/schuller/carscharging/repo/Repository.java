package com.example.schuller.carscharging.repo;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.schuller.carscharging.dao.DaoStations;
import com.example.schuller.carscharging.model.CarsChargerDatabase;
import com.example.schuller.carscharging.model.Station;
import com.example.schuller.carscharging.remotedatasource.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Repository
 * Created by paul on 23/05/2018.
 */

public class Repository {
    private static final String TAG = Repository.class.getSimpleName();
    private static final String BASE_URL = "https://carscharger.firebaseio.com";
    DaoStations daoStations;


    public Repository(CarsChargerDatabase carsChargerDatabase) {
        this.daoStations = carsChargerDatabase.daoStations();
    }

    public LiveData<List<Station>> getStation() {
        return daoStations.getStations();
    }

    public void syncStation() {
        Retrofit builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        Client client = builder.create(Client.class);

        Call<List<Station>> call = client.getAllStationsFromFirebase();

        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                List<Station> station = response.body();
                assert station != null;
                Station stations = new Station();
                daoStations.saveSongsInRoomDb(stations);
            }

            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });

    }
}
