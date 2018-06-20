package com.example.schuller.carscharging.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schuller.carscharging.BuildConfig;
import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.driver.StationActivity;
import com.example.schuller.carscharging.model.Driver;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CreateAccountActivity extends AppCompatActivity {

    private TextView mBackToLogin;
    private EditText mCreateName;
    private EditText mCreateEmail;
    private EditText mCreatePassword;
    private EditText mCreateCarID;
    private EditText mPhoneNumber;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_driver = database.getReference("Drivers");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mCreateName = findViewById(R.id.createName);
        mCreateEmail = findViewById(R.id.createEmail);
        mCreatePassword = findViewById(R.id.createPassword);
        mCreateCarID = findViewById(R.id.createCarID);
        mPhoneNumber = findViewById(R.id.phoneNumber);
        mBackToLogin = findViewById(R.id.back_to_login);

        Button mCreateAccount = findViewById(R.id.createAccountButton);

        mCreateAccount.setOnClickListener(view -> {
            signUp();
        });

        mBackToLogin.setOnClickListener(view ->{
            Intent intent = new Intent (CreateAccountActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

    public void signUp() {

        final String name = mCreateName.getText().toString();
        final String email = mCreateEmail.getText().toString();
        final String carId = mCreateCarID.getText().toString();
        final String number = mPhoneNumber.getText().toString();

        auth.createUserWithEmailAndPassword(mCreateEmail.getText().toString(), mCreatePassword.getText().toString())
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(CreateAccountActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                        table_driver.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                writeNewUser(email, name, carId, number);
                                Toast.makeText(CreateAccountActivity.this, "Added successful", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        Intent intent = new Intent(CreateAccountActivity.this, StationActivity.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(CreateAccountActivity.this, "Sign up failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void writeNewUser(String email, String name, String carId, String phoneNumber) {
        Driver user = new Driver(email, name, carId, phoneNumber);
        table_driver.child(encodeString(email)).setValue(user);
    }

    public String encodeString(String string) {
        return string.replace(".", ",");
    }
}
