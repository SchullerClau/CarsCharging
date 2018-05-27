package com.example.schuller.carscharging.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.schuller.carscharging.model.Station;
import com.example.schuller.carscharging.repo.Repository;

import java.util.List;

/**
 * View Model
 * Created by paul on 23/05/2018.
 */

public class StationViewModel extends ViewModel {

    Repository repository;

    StationViewModel(Repository repository) {
        this.repository = repository;
        repository.syncStation();
    }

    public LiveData<List<Station>> getStations() {
        return repository.getStation();
    }
}
