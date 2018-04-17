package com.example.schuller.carscharging.NotLoggedIn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.schuller.carscharging.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

//                final String email = mEmail.getText().toString();
//                final String password = mPassword.getText().toString();
//
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
}
