package com.example.schuller.carscharging.signup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schuller.carscharging.BuildConfig;
import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.driver.StationActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivity extends AppCompatActivity {

    Button mLogin;
    TextView mRegister;
    EditText mEmail;
    EditText mPassword;
    FirebaseAuth auth = FirebaseAuth.getInstance();


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

        setDebugValues();
    }

    private void setDebugValues() {
        if (BuildConfig.DEBUG) {
            mEmail.setText("test@gmail.com");
            mPassword.setText("123456");
        }
    }

    public void signIn() {
        if (fieldsAreValid()) {
            auth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString())
                    .addOnCompleteListener(this, task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, StationActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Sign in failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, "Email & Password cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean fieldsAreValid() {
        return !TextUtils.isEmpty(mEmail.getText()) && !TextUtils.isEmpty(mPassword.getText());
    }
}