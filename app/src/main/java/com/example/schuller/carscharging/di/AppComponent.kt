package com.example.schuller.carscharging.di


import com.example.schuller.carscharging.driver.StationActivity
import com.example.schuller.carscharging.driver.StationViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(stationViewModel: StationViewModel)
    fun inject(stationActivity: StationActivity)

}