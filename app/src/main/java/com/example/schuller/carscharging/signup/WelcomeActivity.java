package com.example.schuller.carscharging.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.stations.ListaProgram;

public class WelcomeActivity extends AppCompatActivity {

    Button findMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findMore = findViewById(R.id.find_more);
        findMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this, ListaProgram.class);
                startActivity(intent);
            }
        });
    }
}
