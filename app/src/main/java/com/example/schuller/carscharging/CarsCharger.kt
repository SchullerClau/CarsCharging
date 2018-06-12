package com.example.schuller.carscharging

import android.app.Application
import com.example.schuller.carscharging.di.AppComponent
import com.example.schuller.carscharging.di.AppModule
import com.example.schuller.carscharging.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import timber.log.Timber

/**
 * com.example.schuller.carscharging.CarsCharger
 * Created by paul on 23/05/2018.
 */

class CarsCharger : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)

        deleteDatabase("stations")

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

    }

    companion object {
        private var appComponent: AppComponent? = null

        fun diInjector(): AppComponent = when (appComponent) {
            null -> throw RuntimeException("Dagger is not ready. Please initialize it at the top of Application#onCreate() method.")
            else -> appComponent!!
        }
    }
}
