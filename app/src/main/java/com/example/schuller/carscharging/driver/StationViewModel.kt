package com.example.schuller.carscharging.driver

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.schuller.carscharging.CarsCharger
import com.example.schuller.carscharging.api.StationsApi
import com.example.schuller.carscharging.model.Station
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StationViewModel : ViewModel() {

    @Inject
    lateinit var stationApi: StationsApi

    private val stations: MutableLiveData<List<Station>> = MutableLiveData()


    init {
        stations.value = listOf()
        CarsCharger.diInjector().inject(this)
    }

    fun getStations() {
        stationApi.stations
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    stations.value = it
                })
    }

    fun loadStation(): LiveData<List<Station>> = stations
}
