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
            driversName.setText(extras.getString("name"));
            driversEmail.setText(extras.getString("email"));
            driversBodyNumber.setText(extras.getString("bodynumber"));
            driversProfile.setImageResource(extras.getInt("image"));
        }

    }
}