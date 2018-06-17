package com.example.schuller.carscharging.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.driver.MapActivity;
import com.example.schuller.carscharging.driver.StationActivity;
import com.example.schuller.carscharging.stations.StationsSchedule;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {

    Button mLogin;
    TextView mRegister;
    EditText mEmail;
    EditText mPassword;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDb = database.getReference("Stations");


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

        mLogin.setOnClickListener(view -> signIn());

        mRegister.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }


    public void singInAsStation() {
        mDb.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnap) {
                for (long i = 0; i < dataSnap.getChildrenCount(); i++) {
                    String getEmail = dataSnap.child(Long.toString(i)).child("email").getValue(String.class);
                    if (mEmail.getText().toString().equals(getEmail) && mPassword.getText().toString().equals("123456")) {
                        Intent intent = new Intent(LoginActivity.this, StationActivity.class);
                        startActivity(intent);
                    }
                }
            }
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void signIn() {
        if (fieldsAreValid()) {
            auth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MapActivity.class);
                            startActivity(intent);
                        } else {
                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, "Email & Password cannot be empty!", Toast.LENGTH_SHORT).show();
        }
        singInAsStation();
    }

    private boolean fieldsAreValid() {
        return !TextUtils.isEmpty(mEmail.getText()) && !TextUtils.isEmpty(mPassword.getText());
    }
}