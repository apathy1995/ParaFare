package com.example.logintricyfare;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyTripsActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference AllMyTrips, UserMyTrips;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    MyTrips myTrips;
    String fname, email, username, phonenumber, locationIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid = user.getUid();

        editText = findViewById(R.id.add_routes);
        editText = findViewById(R.id.add_numberofperson);
        editText = findViewById(R.id.add_price);
        button = findViewById(R.id.savetrips_btn);
        documentReference = db.collection("users").document(currentUserid);

        AllMyTrips = database.getReference("All MyTrips");
        UserMyTrips = database.getReference("User MyTrips").child(currentUserid);

        myTrips = new MyTrips();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String routesName = editText.getText().toString();
                String numberOfPerson = editText.getText().toString();
                String routesPrice = editText.getText().toString();

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