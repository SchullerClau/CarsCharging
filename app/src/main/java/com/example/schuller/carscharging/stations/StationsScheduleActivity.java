package com.example.schuller.carscharging.stations;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.adapter.StationScheduleAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by schuller on 6/17/18.
 */

public class StationsScheduleActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, String>> list;
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";
    public static final String THIRD_COLUMN="Third";

    TextView mBlacklistButton;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference mDb = database.getReference("Schedule");


    private Button selectDay;
    private OnSelectDateListener dateListener;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        String intentEmail = intent.getStringExtra("stationEmail");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_schedule);

        ListView listView=findViewById(R.id.stationScheduleList);

        populateList();

        mDb.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (long i=0; i<dataSnapshot.getChildrenCount(); i++){
                    if (dataSnapshot.child(Long.toString(i)).child("data").getValue(String.class).equals("18-07-2018") && dataSnapshot.child(Long.toString(i)).child("stationEmail").getValue(String.class).equals(intentEmail)) {
                        for(int j = 0; j<list.size(); j++) {
                            String mHour = dataSnapshot.child(Long.toString(i)).child("ora").getValue(String.class);
                            if (list.get(j).get(FIRST_COLUMN).equals(mHour)) {
                                String mCarId = dataSnapshot.child(Long.toString(i)).child("carId").getValue(String.class);
                                list.get(j).put(SECOND_COLUMN, mCarId);
                                list.get(j).put(THIRD_COLUMN, "Add to Blacklist");
                            }
                        }
                    }
                }
                StationScheduleAdapter adapter=new StationScheduleAdapter(StationsScheduleActivity.this, list);
                listView.setAdapter(adapter);
            }
            public void onCancelled(DatabaseError databaseError) {
            }

        });

        mBlacklistButton = findViewById(R.id.stationScheduleGoToBlacklist);

        mBlacklistButton.setOnClickListener(view -> {
            Intent intentBlack = new Intent(StationsScheduleActivity.this, StationsBlacklistActivity.class);
            intentBlack.putExtra("stationEmailBlack", intentEmail);
            startActivity(intentBlack);
        });

        selectDay = findViewById(R.id.stationScheduleGetCalendar);
        calendar = Calendar.getInstance();
        calendar.set(2018,8,8);
        dateListener = new OnSelectDateListener() {
            @Override
            public void onSelect(List<Calendar> calendars) {
            }
        };


        selectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder builder = new DatePickerBuilder(StationsScheduleActivity.this, dateListener)
                        .pickerType(CalendarView.ONE_DAY_PICKER)
                        .headerColor(R.color.colorPrimary)
                        .selectionColor(R.color.colorPrimary);

                DatePicker datePicker = builder.build();
                datePicker.show();
            }
        });
    }

    private void populateList() {
        // TODO Auto-generated method stub

        list=new ArrayList<HashMap<String,String>>();

        HashMap<String,String> hashmap=new HashMap<String, String>();
        hashmap.put(FIRST_COLUMN, "00:00 - 02:00");
        hashmap.put(SECOND_COLUMN, "");
        hashmap.put(THIRD_COLUMN, "");
        list.add(hashmap);

        HashMap<String,String> hashmap2=new HashMap<String, String>();
        hashmap2.put(FIRST_COLUMN, "02:00 - 04:00");
        hashmap2.put(SECOND_COLUMN, "");
        hashmap2.put(THIRD_COLUMN, "");
        list.add(hashmap2);

        HashMap<String,String> hashmap3=new HashMap<String, String>();
        hashmap3.put(FIRST_COLUMN, "04:00 - 06:00");
        hashmap3.put(SECOND_COLUMN, "");
        hashmap3.put(THIRD_COLUMN, "");
        list.add(hashmap3);

        HashMap<String,String> hashmap4=new HashMap<String, String>();
        hashmap4.put(FIRST_COLUMN, "06:00 - 08:00");
        hashmap4.put(SECOND_COLUMN, "");
        hashmap4.put(THIRD_COLUMN, "");
        list.add(hashmap4);

        HashMap<String,String> hashmap5=new HashMap<String, String>();
        hashmap5.put(FIRST_COLUMN, "08:00 - 10:00");
        hashmap5.put(SECOND_COLUMN, "");
        hashmap5.put(THIRD_COLUMN, "");
        list.add(hashmap5);

        HashMap<String,String> hashmap6=new HashMap<String, String>();
        hashmap6.put(FIRST_COLUMN, "10:00 - 12:00");
        hashmap6.put(SECOND_COLUMN, "");
        hashmap6.put(THIRD_COLUMN, "");
        list.add(hashmap6);

        HashMap<String,String> hashmap7=new HashMap<String, String>();
        hashmap7.put(FIRST_COLUMN, "12:00 - 14:00");
        hashmap7.put(SECOND_COLUMN, "");
        hashmap7.put(THIRD_COLUMN, "");
        list.add(hashmap7);

        HashMap<String,String> hashmap8=new HashMap<String, String>();
        hashmap8.put(FIRST_COLUMN, "14:00 - 16:00");
        hashmap8.put(SECOND_COLUMN, "");
        hashmap8.put(THIRD_COLUMN, "");
        list.add(hashmap8);

        HashMap<String,String> hashmap9=new HashMap<String, String>();
        hashmap9.put(FIRST_COLUMN, "16:00 - 18:00");
        hashmap9.put(SECOND_COLUMN, "");
        hashmap9.put(THIRD_COLUMN, "");
        list.add(hashmap9);


        HashMap<String,String> hashmap10=new HashMap<String, String>();
        hashmap10.put(FIRST_COLUMN, "18:00 - 20:00");
        hashmap10.put(SECOND_COLUMN, "");
        hashmap10.put(THIRD_COLUMN, "");
        list.add(hashmap10);

        HashMap<String,String> hashmap11=new HashMap<String, String>();
        hashmap11.put(FIRST_COLUMN, "20:00 - 22:00");
        hashmap11.put(SECOND_COLUMN, "");
        hashmap11.put(THIRD_COLUMN, "");
        list.add(hashmap11);

        HashMap<String,String> hashmap12=new HashMap<String, String>();
        hashmap12.put(FIRST_COLUMN, "22:00 - 24:00");
        hashmap12.put(SECOND_COLUMN, "");
        hashmap12.put(THIRD_COLUMN, "");
        list.add(hashmap12);
    }
}
