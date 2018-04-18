package com.example.schuller.carscharging.NotLoggedIn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.schuller.carscharging.R;
import com.example.schuller.carscharging.zModel.Driver;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;


public class CreateAccountActivity extends AppCompatActivity {

    private EditText mCreateName, mCreateEmail, mCreatePassword, mCreateCarID ;
    private Button mCreateAccount;
    private DatabaseReference mDatabase;

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

        final String name = mCreateName.getText().toString();
        final String email = mCreateEmail.getText().toString();
        final String password = mCreatePassword.getText().toString();
        final String carId = mCreateCarID.getText().toString();



//                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d("TAG", "createUserWithEmail:success");
//                            writeNewDriver(email, password);
//                            Toast.makeText(LoginActivity.this, "Connection Successful", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
    }

    private void writeNewDriver(String name, String email, String password, String carId) {
        Driver driver = new Driver(name, email, password, carId);
        mDatabase.child("Users").child("Drivers").setValue(driver);
    }
}
