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
    DatabaseReference reference;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    MyTrips myTrips;
    String name, url, privacy, uid;

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
                String routes_name = editText.getText().toString();
                String number_of_person = editText.getText().toString();
                String routes_price = editText.getText().toString();

                Calendar cdate = Calendar.getInstance();
                SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
                final String savedate = currentdate.format(cdate.getTime());

                Calendar ctime = Calendar.getInstance();
                SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss");
                final String savetime = currenttime.format(ctime.getTime());

                String time = savedate +":"+ savetime;

                if (!TextUtils.isEmpty(routes_name) && !TextUtils.isEmpty(number_of_person) && !TextUtils.isEmpty(routes_price)){

                    myTrips.setRoutesName(routes_name);
                    myTrips.setRoutesPrice(routes_price);
                    myTrips.setNumberOfPerson(number_of_person);
                    myTrips.setName(name);
                    myTrips.setPrivacy(privacy);
                    myTrips.setUrl(url);
                    myTrips.setUserId(uid);
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
                name= task.getResult().getString("name");
                url = task.getResult().getString("url");
                privacy = task.getResult().getString("privacy");
                uid = task.getResult().getString("uid");
            }else{
                Toast.makeText(MyTripsActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}