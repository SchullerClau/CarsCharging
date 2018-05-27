package com.example.schuller.carscharging.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.driver.AvailableStation;
import com.example.schuller.carscharging.model.Driver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class LoginActivity extends AppCompatActivity {

    Button mLogin;
    TextView mRegister;
    EditText mEmail;
    EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLogin = findViewById(R.id.login);
        mRegister = findViewById(R.id.registration);

        //Init Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_driver = database.getReference().child("Driver");

        mLogin.setOnClickListener(view -> {

            final String email = mEmail.getText().toString();
            final String password = mPassword.getText().toString();

            final ProgressDialog progressBar = new ProgressDialog(LoginActivity.this);
            progressBar.setMessage("Loading...");
            progressBar.show();

            table_driver.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //Check if user don't exists in database
                    if (dataSnapshot.child(encodeString(email)).exists()) {
                        progressBar.dismiss();
                        //Get User Information
                        Driver driver = dataSnapshot.child(encodeString(email)).getValue(Driver.class);

                        if (driver != null) {
                            if (Objects.equals(driver.getPassword(), password)) {
                                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Intent availableStation = new Intent(LoginActivity.this, AvailableStation.class);
                                availableStation.putExtra("user", driver.getName());
                                startActivity(availableStation);
                            } else {
                                Toast.makeText(LoginActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            progressBar.dismiss();
                            Toast.makeText(LoginActivity.this, "User don't exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressBar.dismiss();
                        Toast.makeText(LoginActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    public String encodeString(String string) {
        return string.replace(".", ",");
    }
}
