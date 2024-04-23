package com.example.logintricyfare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signupEmail, signupUsername, signupFullName, signupPhoneNumber, signupPassword;
    private Button signupButton;
    private TextView LoginRedirectText;
    FirebaseFirestore firestore;
    FirebaseDatabase database;
    DatabaseReference reference;
    String userID;
    public static final String TAG = "TAG";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
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

                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String email = signupEmail.getText().toString().trim();
                String username = signupUsername.getText().toString().trim();
                String fullName = signupFullName.getText().toString().trim();
                String phoneNumber = signupPhoneNumber.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();

                HelperClass helperClass = new HelperClass(fullName, username, email, phoneNumber, pass);
                reference.child(fullName).setValue(helperClass);

                if (email.isEmpty()) {
                    signupEmail.setError("Email cannot be empty");
                }
                if (username.isEmpty()) {
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
                                userID = auth.getCurrentUser().getUid();
                                DocumentReference documentReference = firestore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("fname", fullName);
                                user.put("username",username);
                                user.put("email", email);
                                user.put("phoneNumber", phoneNumber);
                                documentReference.set(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "User profile created for " + userID);
                                                } else {
                                                    Log.d(TAG, "Failed to create user profile: " + task.getException().toString());
                                                }
                                            }
                                        });


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

