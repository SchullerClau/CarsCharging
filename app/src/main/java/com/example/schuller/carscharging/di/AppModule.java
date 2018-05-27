package com.example.schuller.carscharging.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.schuller.carscharging.model.CarsChargerDatabase;
import com.example.schuller.carscharging.repo.Repository;
import com.example.schuller.carscharging.viewmodel.CustomViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * AppModule
 * Created by paul on 23/05/2018.
 */

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    CarsChargerDatabase getDatabase() {
        return Room
                .databaseBuilder(context.getApplicationContext(), CarsChargerDatabase.class, CarsChargerDatabase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    Repository getRepository(CarsChargerDatabase carsChargerDatabase) {
        return new Repository(carsChargerDatabase);
    }

    @Provides
    @Singleton
    CustomViewModelFactory getViewModel(Repository songRepository) {
        return new CustomViewModelFactory(songRepository);
    }
}
