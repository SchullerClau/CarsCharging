package com.example.schuller.carscharging.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.schuller.carscharging.model.Station;

import java.util.List;

/**
 * DaoStations
 * Created by paul on 23/05/2018.
 */


@Dao
public interface DaoStations {

    @Query("SELECT * FROM stations")
    LiveData<List<Station>> getStations();

    @Insert
    void saveSongsInRoomDb(Station... stations);

}
