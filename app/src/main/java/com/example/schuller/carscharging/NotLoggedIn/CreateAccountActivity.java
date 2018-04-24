package com.example.schuller.carscharging.NotLoggedIn;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.zModel.Driver;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class CreateAccountActivity extends AppCompatActivity {

    private EditText mCreateName, mCreateEmail, mCreatePassword, mCreateCarID;
    private Button mCreateAccount;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mCreateName = findViewById(R.id.createName);
        mCreateEmail = findViewById(R.id.createEmail);
        mCreatePassword = findViewById(R.id.createPassword);
        mCreateCarID = findViewById(R.id.createCarID);

        mCreateAccount = findViewById(R.id.createAccoutButton);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        final String name = mCreateName.getText().toString();
        final String email = mCreateEmail.getText().toString();
        final String password = mCreatePassword.getText().toString();
        final String carId = mCreateCarID.getText().toString();

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String driver_id = mAuth.getCurrentUser().getUid();
                                    DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers").child(driver_id);

                                    Map newPost = new HashMap();
                                    newPost.put("name", name);
                                    newPost.put("email", email);
                                    newPost.put("password", password);
                                    newPost.put("carId", carId);

                                    current_user_db.setValue(newPost);

                                    Toast.makeText(CreateAccountActivity.this, "User was created", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(CreateAccountActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
