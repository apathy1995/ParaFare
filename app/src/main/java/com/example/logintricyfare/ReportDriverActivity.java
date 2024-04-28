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

public class ReportDriverActivity extends AppCompatActivity {

    EditText editReportDriverName, editReportBodyNumber, editReportIssue;
    Button button;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference AllReportDriver, UserReportDriver;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    ReportDriver reportDriver;
    String fname, email, username, phonenumber, imgreportdriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_driver);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid = user.getUid();

        editReportDriverName = findViewById(R.id.add_drivername);
        editReportBodyNumber = findViewById(R.id.add_bodynumber);
        editReportIssue = findViewById(R.id.add_reportIssue);
        button = findViewById(R.id.submit_btn);
        documentReference = db.collection("users").document(currentUserid);

        AllReportDriver = database.getReference("All ReportDriver");
        UserReportDriver = database.getReference("User ReportDriver").child(currentUserid);

        reportDriver = new ReportDriver();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reportdriver_name = editReportDriverName.getText().toString();
                String report_bodynumber = editReportBodyNumber.getText().toString();
                String report_issue = editReportIssue.getText().toString();

                Calendar cdate = Calendar.getInstance();
                SimpleDateFormat currentdate = new SimpleDateFormat("dd-MMMM-yyyy");
                final String savedate = currentdate.format(cdate.getTime());

                Calendar ctime = Calendar.getInstance();
                SimpleDateFormat currenttime = new SimpleDateFormat("HH:mm:ss");
                final String savetime = currenttime.format(ctime.getTime());

                String rdtime = savedate +":"+ savetime;

                if (!TextUtils.isEmpty(reportdriver_name) && !TextUtils.isEmpty(report_bodynumber) && !TextUtils.isEmpty(report_issue)){

                    reportDriver.setReportDriverName(reportdriver_name);
                    reportDriver.setReportBodyNumber(report_bodynumber);
                    reportDriver.setReportDriverIssue(report_issue);
                    reportDriver.setFname(fname);
                    reportDriver.setEmail(email);
                    reportDriver.setUsername(username);
                    reportDriver.setPhonenumber(phonenumber);
                    reportDriver.setImgreportdriver(imgreportdriver);
                    reportDriver.setRdtime(rdtime);

                    String id = UserReportDriver.push().getKey();
                    UserReportDriver.child(id).setValue(reportDriver);

                    String child = AllReportDriver.push().getKey();
                    reportDriver.setKey(id);
                    AllReportDriver.child(child).setValue(reportDriver);

                    Toast.makeText(ReportDriverActivity.this,"Saved", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ReportDriverActivity.this,"Please answer", Toast.LENGTH_SHORT).show();
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
                imgreportdriver = task.getResult().getString("imgreportdriver");
            }else{
                Toast.makeText(ReportDriverActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}