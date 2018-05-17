package com.example.schuller.carscharging.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.model.Driver;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CreateAccountActivity extends AppCompatActivity {

    private EditText mCreateName;
    private EditText mCreateEmail;
    private EditText mCreatePassword;
    private EditText mCreateCarID;
    private EditText mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mCreateName = findViewById(R.id.createName);
        mCreateEmail = findViewById(R.id.createEmail);
        mCreatePassword = findViewById(R.id.createPassword);
        mCreateCarID = findViewById(R.id.createCarID);
        mPhoneNumber = findViewById(R.id.phoneNumber);

        Button mCreateAccount = findViewById(R.id.createAccountButton);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_driver = database.getReference("Driver");

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = mCreateName.getText().toString();
                final String email = mCreateEmail.getText().toString();
                final String password = mCreatePassword.getText().toString();
                final String carId = mCreateCarID.getText().toString();
                final String number = mPhoneNumber.getText().toString();

                if (name.isEmpty()) {
                    mCreateName.setError(getString(R.string.m_create_name));
                    mCreateName.requestFocus();
                } else if (email.isEmpty()) {
                    mCreateEmail.setError(getString(R.string.m_create_email));
                    mCreateEmail.requestFocus();
                } else if (password.length() < 6) {
                    mCreatePassword.setError(getString(R.string.m_create_password));
                    mCreatePassword.requestFocus();
                } else if (carId.isEmpty()) {
                    mCreateCarID.setError(getString(R.string.m_create_carId));
                    mCreateCarID.requestFocus();
                } else {
                    final ProgressDialog progressBar = new ProgressDialog(CreateAccountActivity.this);
                    progressBar.setMessage("Loading...");
                    progressBar.show();

                    table_driver.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //check if already exists user
                            if (dataSnapshot.child((encodeString(email))).exists()) {
                                progressBar.dismiss();
                                Toast.makeText(CreateAccountActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                progressBar.dismiss();
                                Driver driver = new Driver(name, password, carId, number);
                                table_driver.child(encodeString(email)).setValue(driver);
                                Toast.makeText(CreateAccountActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public String encodeString(String string) {
        return string.replace(".", ",");
    }
}
