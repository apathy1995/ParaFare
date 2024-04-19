package com.example.logintricyfare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupUsername, signupFullName, signupPhoneNumber, signupPassword;
    private Button signupButton;
    private TextView LoginRedirectText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        signupEmail = findViewById(R.id.username);
        signupUsername = findViewById(R.id.user);
        signupFullName = findViewById(R.id.fullname);
        signupPhoneNumber = findViewById(R.id.phonenumber);
        signupPassword = findViewById(R.id.password);
        signupButton = findViewById(R.id.signupButton);
        LoginRedirectText = findViewById(R.id.LoginRedirectText);

        signupButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = signupEmail.getText().toString().trim();
                String user = signupUsername.getText().toString().trim();
                String fullName = signupFullName.getText().toString().trim();
                String phoneNumber = signupPhoneNumber.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();


                if (email.isEmpty()) {
                    signupEmail.setError("Email cannot be empty");
                }
                if (user.isEmpty()) {
                    signupUsername.setError("Username cannot be empty");
                }
                if (fullName.isEmpty()) {
                    signupFullName.setError("Full name cannot be empty");
                }
                if (phoneNumber.isEmpty()) {
                    signupPhoneNumber.setError("Phone Number cannot be error");
                }
                if (pass.isEmpty()) {
                    signupPassword.setError("Password cannot be empty");
                } else {
                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Signup Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, com.example.logintricyfare.LoginActivity.class));

                            } else {
                                Toast.makeText(SignUpActivity.this, "Signup Failed!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
        LoginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, com.example.logintricyfare.LoginActivity.class));
            }
        });
    }
}

