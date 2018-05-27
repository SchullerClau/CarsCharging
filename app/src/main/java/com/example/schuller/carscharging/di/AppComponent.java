package com.example.schuller.carscharging.di;

import com.example.schuller.carscharging.driver.AvailableStation;

import javax.inject.Singleton;

import dagger.Component;

/**
 * AppComponent
 * Created by paul on 23/05/2018.
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(AvailableStation availableStation);

}
