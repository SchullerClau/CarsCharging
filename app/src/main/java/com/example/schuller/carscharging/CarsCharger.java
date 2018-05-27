package com.example.schuller.carscharging;

import android.app.Application;

import com.example.schuller.carscharging.di.AppComponent;
import com.example.schuller.carscharging.di.AppModule;
import com.example.schuller.carscharging.di.DaggerAppComponent;

/**
 * com.example.schuller.carscharging.CarsCharger
 * Created by paul on 23/05/2018.
 */

public class CarsCharger extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        deleteDatabase("carchargerDB");

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

}
