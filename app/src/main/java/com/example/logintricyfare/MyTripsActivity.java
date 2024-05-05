package com.example.logintricyfare;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyTripsActivity extends AppCompatActivity {

    Spinner routes_spinner, distance_spinner, price_spinner ;
    TextView txtroutesName, txtroutesDistance, txtroutesPrice;
    Button button;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference AllMyTrips, UserMyTrips;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView mMyTripsRecyclerView;
    DocumentReference documentReference;
    MyTrips myTrips;
    String fname, email, username, phonenumber, locationIcon;

    String[] routes_Name = {"Select Routes", "Bolacan - Turo Hangga", "Bolacan - Tollgate", "Bolacan - Bocaue Crossing", "Bolacan - JIL"};

    String[] routes_Distance = {"Select Distance and Number of Person", "1km - 1 person", "1km - 2 person", "1km - 3 person",
            "1.5km - 1 person", "1.5km - 2 person", "1.5km - 3 person"};

    String[] routes_Price = {"Select Routes Fare", "20.00", "30.00", "45.00"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid = user.getUid();

        txtroutesName = findViewById(R.id.txtroutesName);
        txtroutesDistance = findViewById(R.id.txtroutesDistance);
        txtroutesPrice = findViewById(R.id.txtroutesPrice);

        routes_spinner = findViewById(R.id.add_routes);
        distance_spinner = findViewById(R.id.add_distance);
        price_spinner = findViewById(R.id.add_price);
        button = findViewById(R.id.savetrips_btn);
        documentReference = db.collection("users").document(currentUserid);

        ArrayAdapter<String> routesAdapter;
        routesAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, routes_Name);
        routesAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        ArrayAdapter<String> distanceAdapter;
        distanceAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, routes_Distance);
        distanceAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        ArrayAdapter<String> priceAdapter;
        priceAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, routes_Price);
        priceAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);

        routes_spinner.setAdapter(routesAdapter);
        distance_spinner.setAdapter(distanceAdapter);
        price_spinner.setAdapter(priceAdapter);

        AllMyTrips = database.getReference("All MyTrips");
        UserMyTrips = database.getReference("User MyTrips").child(currentUserid);

        myTrips = new MyTrips();


        routes_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Routes")){

                } else {
                    txtroutesName.setText(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        distance_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Distance and Number of Person")){

                } else {
                    txtroutesDistance.setText(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        price_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Routes Fare: ")){

                } else {
                    txtroutesPrice.setText(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String routesName = routes_spinner.getSelectedItem().toString();
                String numberOfPerson = distance_spinner.getSelectedItem().toString();
                String routesPrice = price_spinner.getSelectedItem().toString();

                Calendar cdate = Calendar.getInstance();
                SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
                final String savedate = currentdate.format(cdate.getTime());

                Calendar ctime = Calendar.getInstance();
                SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss");
                final String savetime = currenttime.format(ctime.getTime());

                String time = savedate +":"+ savetime;

                if (!TextUtils.isEmpty(routesName) && !TextUtils.isEmpty(numberOfPerson) && !TextUtils.isEmpty(routesPrice)){

                    myTrips.setRoutesName(routesName);
                    myTrips.setRoutesPrice(routesPrice);
                    myTrips.setNumberOfPerson(numberOfPerson);
                    myTrips.setFname(fname);
                    myTrips.setEmail(email);
                    myTrips.setUsername(username);
                    myTrips.setPhonenumber(phonenumber);
                    myTrips.setLocationIcon(locationIcon);
                    myTrips.setTime(time);

                    String id = UserMyTrips.push().getKey();
                    UserMyTrips.child(id).setValue(myTrips);

                    String child = AllMyTrips.push().getKey();
                    myTrips.setKey(id);
                    AllMyTrips.child(child).setValue(myTrips);

                    Toast.makeText(MyTripsActivity.this,"Saved", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(MyTripsActivity.this,"Please answer", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        documentReference.get().addOnCompleteListener((task) -> {

            if (task.getResult().exists()){
                fname= task.getResult().getString("fname");
                email = task.getResult().getString("email");
                phonenumber = task.getResult().getString("phonenumber");
                username = task.getResult().getString("username");
                locationIcon = task.getResult().getString("locationIcon");
            }else{
                Toast.makeText(MyTripsActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}