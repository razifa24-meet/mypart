package com.example.csmypart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText phoneNumber;
    private EditText password;
    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        userName = findViewById(R.id.user_name);
        phoneNumber = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        Button signUpButton = findViewById(R.id.sign_up_button);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneStr = phoneNumber.getText().toString();
                String passwordStr = password.getText().toString();
                String userNameStr = userName.getText().toString();

                signUpUser(phoneStr, passwordStr);
            }
        });
    }

    private void signUpUser(String phone, String password) {
        // Firebase Authentication does not support phone number authentication directly through signInWithEmailAndPassword.
        // You would typically use Firebase's phone authentication.
        // For example purposes, assuming email and password registration is required:
        mAuth.createUserWithEmailAndPassword(phone + "@example.com", password) // Mock email since Firebase Auth requires email.
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success, update UI with the signed-in user's information
                            Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign up fails, display a message to the user.
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
