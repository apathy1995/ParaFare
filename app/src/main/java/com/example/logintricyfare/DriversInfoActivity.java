package com.example.logintricyfare;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DriversInfoActivity extends AppCompatActivity {

    TextView driversName, driversBodyNumber, driversEmail;
    ImageView driversProfile;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers_info);

        driversName = findViewById(R.id.driver_name);
        driversEmail = findViewById(R.id.driver_email);
        driversBodyNumber = findViewById(R.id.bodynumber);
        driversProfile = findViewById(R.id.driver_profilebg);

        extras = getIntent().getExtras();

        if (extras != null){
            driversName.setText(extras.getString("Name"));
            driversEmail.setText(extras.getString("Email"));
            driversBodyNumber.setText(extras.getString("BodyNumber"));
            driversProfile.setImageResource(extras.getInt("Drivers Profile"));
        }

    }
}