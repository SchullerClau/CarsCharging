package com.example.schuller.carscharging.driver;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.schuller.carscharging.CarsCharger;
import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.adapter.StationAdapter;
import com.example.schuller.carscharging.di.AppComponent;
import com.example.schuller.carscharging.viewmodel.CustomViewModelFactory;
import com.example.schuller.carscharging.viewmodel.StationViewModel;

import javax.inject.Inject;

/**
 * AvailableStation
 * Created by schuller on 23/05/2018.
 */

public class AvailableStation extends AppCompatActivity {

    @Inject
    CustomViewModelFactory viewModelFactory;
    private StationViewModel viewModel;
    private RecyclerView stationsRecycler;
    private TextView userName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_station);

        getAppComponent().inject(this);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(StationViewModel.class);
        stationsRecycler = findViewById(R.id.recycler_stations);
        stationsRecycler.setLayoutManager(new LinearLayoutManager(this));
        stationsRecycler.setItemAnimator(new DefaultItemAnimator());
        stationsRecycler.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        userName = findViewById(R.id.user_name);

        viewModel.getStations().observe(this, stations -> {
            StationAdapter adapter = new StationAdapter(stations);
            stationsRecycler.setAdapter(adapter);
        });

        Intent intent = getIntent();
        String user = intent.getStringExtra("user");
        userName.setText(user);

    }

    public AppComponent getAppComponent() {
        return ((CarsCharger) getApplication()).getAppComponent();
    }
}

