package com.example.schuller.carscharging.adapter;


import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.schuller.carscharging.R;


public class DriverScheduleAdapter extends BaseAdapter {

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";
    public static final String THIRD_COLUMN="Third";

    public DriverScheduleAdapter(Activity activity, ArrayList<HashMap<String, String>> list){
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
        TextView txtThird;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        DriverScheduleAdapter.ViewHolder holder;

        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.activity_driver_schedule_row, null);
            holder=new DriverScheduleAdapter.ViewHolder();

            holder.txtFirst=(TextView) convertView.findViewById(R.id.driverScheduleHour);
            holder.txtSecond=(TextView) convertView.findViewById(R.id.driverScheduleCarId);
            holder.txtThird=(TextView) convertView.findViewById(R.id.driverScheduleButton);

            convertView.setTag(holder);
        }else{

            holder=(DriverScheduleAdapter.ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map=list.get(position);
        holder.txtFirst.setText(map.get(FIRST_COLUMN));
        holder.txtSecond.setText(map.get(SECOND_COLUMN));
        holder.txtThird.setText(map.get(THIRD_COLUMN));

        holder.txtThird.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ((ListView) parent).performItemClick(v, position, 0); // Let the event be handled in onItemClick()
            }
        });

        return convertView;
    }

}
