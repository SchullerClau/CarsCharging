package com.example.schuller.carscharging.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.schuller.carscharging.repo.Repository;

/**
 * CustomViewModelFactory
 * Created by paul on 23/05/2018.
 */

public class CustomViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    public CustomViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StationViewModel.class)) {
            return (T) new StationViewModel(repository);
        }
        throw new IllegalArgumentException("Required class is not supported");
    }
}
