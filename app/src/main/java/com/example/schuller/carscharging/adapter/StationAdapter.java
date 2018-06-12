package com.example.schuller.carscharging.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.model.Station;

import java.util.List;

import timber.log.Timber;

/**
 * Adapter
 * Created by schuller on 23/05/2018.
 */

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {

    private List<Station> stations;

    public StationAdapter(List<Station> stations) {
        this.stations = stations;
    }

    @Override
    public StationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_station, parent, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StationViewHolder holder, int position) {
        Station station = stations.get(position);
        Timber.d("STATION NAME -> " + station.getName());
        holder.name.setText(station.getName());
        holder.address.setText(station.getAddress());
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    class StationViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView address;

        StationViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.station_name);
            address = itemView.findViewById(R.id.station_address);
        }
    }
}
