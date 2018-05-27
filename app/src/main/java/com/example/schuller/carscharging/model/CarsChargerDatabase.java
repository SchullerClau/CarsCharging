package com.example.schuller.carscharging.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.schuller.carscharging.dao.DaoStations;

/**
 * CarsChargerDatabase
 * Created by paul on 23/05/2018.
 */


@Database(entities = {Station.class}, version = 1)
public abstract class CarsChargerDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "carchargerDB";

    public abstract DaoStations daoStations();

}
