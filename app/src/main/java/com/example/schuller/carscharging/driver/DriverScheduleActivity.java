package com.example.schuller.carscharging.driver;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.adapter.DriverScheduleAdapter;
import com.example.schuller.carscharging.model.ScheduleList;
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
 * Created by schuller on 6/18/18.
 */

public class DriverScheduleActivity extends AppCompatActivity{

    private ArrayList<HashMap<String, String>> list;
    public static final String FIRST_COLUMN="First";
    public static final String SECOND_COLUMN="Second";
    public static final String THIRD_COLUMN="Third";

    private Button selectDay;
    private OnSelectDateListener dateListener;
    private Calendar calendar;

    private String selectedYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    private String selectedMonth = String.valueOf(Calendar.getInstance().get(Calendar.MONTH)+1);
    private String selectedDay = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference mDb = database.getReference("Schedule");
    final DatabaseReference mStationDb = database.getReference("Stations");
    final DatabaseReference mDriverDb = database.getReference("Driver");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Integer.parseInt(selectedMonth) < 10)
            selectedMonth = "0"+selectedMonth;
        String currentDate = selectedDay + "-" + selectedMonth + "-" +selectedYear;

        Intent intent = getIntent();
        String stationName = intent.getStringExtra("stationName");
        String driverName = intent.getStringExtra("driverName");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_schedule);

        ListView listView=findViewById(R.id.driverScheduleList);

        populateList();

        mDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (long i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                    String iDate = dataSnapshot.child(Long.toString(i)).child("data").getValue(String.class);
                    String iEmail = dataSnapshot.child(Long.toString(i)).child("stationEmail").getValue(String.class);
                    String mCarId = dataSnapshot.child(Long.toString(i)).child("carId").getValue(String.class);
                    String mHour = dataSnapshot.child(Long.toString(i)).child("ora").getValue(String.class);
                    mStationDb.addValueEventListener(new ValueEventListener() {

                        public void onDataChange(DataSnapshot stationSnapshot) {

                                for (long k = 0; k < stationSnapshot.getChildrenCount()-1; k++){
                                    if (iDate.equals(currentDate)
                                            && iEmail.equals(stationSnapshot.child(Long.toString(k)).child("email").getValue(String.class))
                                            && stationSnapshot.child(Long.toString(k)).child("Nume").getValue(String.class).equals(stationName) )
                                           {
                                        for (int j = 0; j < list.size(); j++) {
                                            if (list.get(j).get(FIRST_COLUMN).equals(mHour)) {
                                                list.get(j).put(SECOND_COLUMN, mCarId);
                                                list.get(j).put(THIRD_COLUMN, "");
                                            }
                                        }
                                    }
                                }
                            DriverScheduleAdapter adapter=new DriverScheduleAdapter(DriverScheduleActivity.this, list);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    long viewId = view.getId();
                                    if (viewId == R.id.driverScheduleButton && list.get(position).get(SECOND_COLUMN).equals("")){
                                        addSchedule(stationName, currentDate, list.get(position).get(FIRST_COLUMN), driverName, dataSnapshot.getChildrenCount());
                                    }

                                }
                            });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        selectDay = findViewById(R.id.driverScheduleGetCalendar);
        calendar = Calendar.getInstance();

        dateListener = new OnSelectDateListener() {
            @Override
            public void onSelect(List<Calendar> calendar) {
                String mTime = String.valueOf(calendar.get(0).getTime());
                String[] splittedDateTime = mTime.split(" ");
                selectedYear = splittedDateTime[5];
                selectedMonth = getConvertedMonth(splittedDateTime[1]);
                selectedDay = splittedDateTime[2];

                String currentDate = selectedDay + "-" + selectedMonth + "-" +selectedYear;

                populateList();

                mDb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (long i = 0; i < dataSnapshot.getChildrenCount(); i++) {
                            String iDate = dataSnapshot.child(Long.toString(i)).child("data").getValue(String.class);
                            String iEmail = dataSnapshot.child(Long.toString(i)).child("stationEmail").getValue(String.class);
                            String mCarId = dataSnapshot.child(Long.toString(i)).child("carId").getValue(String.class);
                            String mHour = dataSnapshot.child(Long.toString(i)).child("ora").getValue(String.class);
                            mStationDb.addValueEventListener(new ValueEventListener() {

                                public void onDataChange(DataSnapshot stationSnapshot) {

                                    for (long k = 0; k < stationSnapshot.getChildrenCount(); k++){
                                        if (iDate.equals(currentDate)
                                                && iEmail.equals(stationSnapshot.child(Long.toString(k)).child("email").getValue(String.class))
                                                && stationSnapshot.child(Long.toString(k)).child("Nume").getValue(String.class).equals(stationName) )
                                        {
                                            for (int j = 0; j < list.size(); j++) {
                                                if (list.get(j).get(FIRST_COLUMN).equals(mHour)) {
                                                    list.get(j).put(SECOND_COLUMN, mCarId);
                                                    list.get(j).put(THIRD_COLUMN, "");
                                                }
                                            }
                                        }
                                    }
                                    DriverScheduleAdapter adapter=new DriverScheduleAdapter(DriverScheduleActivity.this, list);
                                    listView.setAdapter(adapter);
                                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            long viewId = view.getId();
                                            if (viewId == R.id.driverScheduleButton && list.get(position).get(SECOND_COLUMN).equals("")){
                                                addSchedule(stationName, currentDate, list.get(position).get(FIRST_COLUMN), driverName, dataSnapshot.getChildrenCount());
                                            }

                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        };


        selectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder builder = new DatePickerBuilder(DriverScheduleActivity.this, dateListener)
                        .pickerType(CalendarView.ONE_DAY_PICKER)
                        .headerColor(R.color.colorPrimary)
                        .selectionColor(R.color.colorPrimary);

                DatePicker datePicker = builder.build();
                datePicker.show();
            }
        });

    }

    private void addSchedule(String iEmail, String iDate, String iHour, String iDriver, Long position){

        String driverEmail = iDriver.substring(0, iDriver.length()-4)+",com";
        mDriverDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String driverCarId = dataSnapshot.child(driverEmail).child("carId").getValue(String.class);
                mStationDb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (int u=0; u<dataSnapshot.getChildrenCount();u++)
                            if(iEmail.equals(dataSnapshot.child(Long.toString(u)).child("Nume").getValue(String.class))) {

                                String stationEmail = dataSnapshot.child(Long.toString(u)).child("email").getValue(String.class);
                                ScheduleList schedule = new ScheduleList(iDate, driverCarId, stationEmail, iHour);

                                mDb.child(Long.toString(position)).setValue(schedule);
                            }
                        }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private String getConvertedMonth(String month){
        if (month.equals("Jan")){
            month = "01";
        }
        if (month.equals("Feb")){
            month = "02";
        }
        if (month.equals("Mar")){
            month = "03";
        }
        if (month.equals("Apr")){
            month = "04";
        }
        if (month.equals("May")){
            month = "05";
        }
        if (month.equals("Jun")){
            month = "06";
        }
        if (month.equals("Jul")){
            month = "07";
        }
        if (month.equals("Aug")){
            month = "08";
        }
        if (month.equals("Sep")){
            month = "09";
        }
        if (month.equals("Oct")){
            month = "10";
        }
        if (month.equals("Nov")){
            month = "11";
        }
        if (month.equals("Dec")){
            month = "12";
        }
        return month;
    }

    private void populateList() {
        // TODO Auto-generated method stub

        list=new ArrayList<HashMap<String,String>>();

        HashMap<String,String> hashmap=new HashMap<String, String>();
        hashmap.put(FIRST_COLUMN, "00:00 - 02:00");
        hashmap.put(SECOND_COLUMN, "");
        hashmap.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap);

        HashMap<String,String> hashmap2=new HashMap<String, String>();
        hashmap2.put(FIRST_COLUMN, "02:00 - 04:00");
        hashmap2.put(SECOND_COLUMN, "");
        hashmap2.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap2);

        HashMap<String,String> hashmap3=new HashMap<String, String>();
        hashmap3.put(FIRST_COLUMN, "04:00 - 06:00");
        hashmap3.put(SECOND_COLUMN, "");
        hashmap3.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap3);

        HashMap<String,String> hashmap4=new HashMap<String, String>();
        hashmap4.put(FIRST_COLUMN, "06:00 - 08:00");
        hashmap4.put(SECOND_COLUMN, "");
        hashmap4.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap4);

        HashMap<String,String> hashmap5=new HashMap<String, String>();
        hashmap5.put(FIRST_COLUMN, "08:00 - 10:00");
        hashmap5.put(SECOND_COLUMN, "");
        hashmap5.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap5);

        HashMap<String,String> hashmap6=new HashMap<String, String>();
        hashmap6.put(FIRST_COLUMN, "10:00 - 12:00");
        hashmap6.put(SECOND_COLUMN, "");
        hashmap6.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap6);

        HashMap<String,String> hashmap7=new HashMap<String, String>();
        hashmap7.put(FIRST_COLUMN, "12:00 - 14:00");
        hashmap7.put(SECOND_COLUMN, "");
        hashmap7.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap7);

        HashMap<String,String> hashmap8=new HashMap<String, String>();
        hashmap8.put(FIRST_COLUMN, "14:00 - 16:00");
        hashmap8.put(SECOND_COLUMN, "");
        hashmap8.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap8);

        HashMap<String,String> hashmap9=new HashMap<String, String>();
        hashmap9.put(FIRST_COLUMN, "16:00 - 18:00");
        hashmap9.put(SECOND_COLUMN, "");
        hashmap9.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap9);

        HashMap<String,String> hashmap10=new HashMap<String, String>();
        hashmap10.put(FIRST_COLUMN, "18:00 - 20:00");
        hashmap10.put(SECOND_COLUMN, "");
        hashmap10.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap10);

        HashMap<String,String> hashmap11=new HashMap<String, String>();
        hashmap11.put(FIRST_COLUMN, "20:00 - 22:00");
        hashmap11.put(SECOND_COLUMN, "");
        hashmap11.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap11);

        HashMap<String,String> hashmap12=new HashMap<String, String>();
        hashmap12.put(FIRST_COLUMN, "22:00 - 24:00");
        hashmap12.put(SECOND_COLUMN, "");
        hashmap12.put(THIRD_COLUMN, "Make an appointment");
        list.add(hashmap12);
    }
}
