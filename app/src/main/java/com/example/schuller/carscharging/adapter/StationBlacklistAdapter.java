package com.example.schuller.carscharging.adapter;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.schuller.carscharging.R;

public class StationBlacklistAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";

    public StationBlacklistAdapter(Activity activity, ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder{
        TextView txtFirst;
        TextView txtSecond;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;

        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.activity_station_blacklist_row, null);
            holder=new ViewHolder();

            holder.txtFirst=(TextView) convertView.findViewById(R.id.blacklistCarId);
            holder.txtSecond = (TextView) convertView.findViewById(R.id.blacklistButton);

            convertView.setTag(holder);
        }else{

            holder=(ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map=list.get(position);
        holder.txtFirst.setText(map.get(FIRST_COLUMN));
        holder.txtSecond.setText(map.get(SECOND_COLUMN));

        return convertView;
    }

}