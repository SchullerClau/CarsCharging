package com.example.schuller.carscharging.stations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.example.schuller.carscharging.R;

import java.util.Calendar;
import java.util.List;

public class ListaProgram extends AppCompatActivity {

    private Button selectDay;
    private OnSelectDateListener dateListener;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_program);

        selectDay = findViewById(R.id.select_day_button);
        calendar = Calendar.getInstance();
        calendar.set(2018,8,8);

        dateListener = new OnSelectDateListener() {
            @Override
            public void onSelect(List<Calendar> calendars) {
                Toast.makeText(ListaProgram.t, Toast.LENGTH_SHORT).show();
            }
        };


        selectDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerBuilder builder = new DatePickerBuilder(ListaProgram.this, dateListener)
                        .pickerType(CalendarView.ONE_DAY_PICKER)
                        .headerColor(R.color.colorPrimary)
                        .selectionColor(R.color.colorPrimary);

                DatePicker datePicker = builder.build();
                datePicker.show();
            }
        });
    }
}
