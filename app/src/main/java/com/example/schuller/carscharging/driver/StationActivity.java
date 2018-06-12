package com.example.schuller.carscharging.driver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.schuller.carscharging.CarsCharger;
import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.adapter.StationAdapter;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * StationActivity
 * Created by schuller on 23/05/2018.
 */

public class StationActivity extends AppCompatActivity {

    @Inject
    StationViewModel stationViewModel;

    public StationActivity() {
        super();
        CarsCharger.Companion.diInjector().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_station);

        RecyclerView stationsRecycler = findViewById(R.id.recycler_stations);
        stationsRecycler.setLayoutManager(new LinearLayoutManager(this));
        stationsRecycler.setItemAnimator(new DefaultItemAnimator());
        stationsRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        stationViewModel.loadStation()
                .observe(this, stations -> {
                    Timber.d("Stations ->" + stations);
                    StationAdapter stationAdapter = new StationAdapter(stations);
                    stationsRecycler.setAdapter(stationAdapter);
                });

        stationViewModel.getStations();
    }
}

