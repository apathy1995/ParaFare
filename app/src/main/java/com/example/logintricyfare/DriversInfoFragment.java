package com.example.logintricyfare;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.logintricyfare.DriversInformation;
import com.example.logintricyfare.DriversRecyclerViewAdapter;
import com.example.logintricyfare.R;
import com.example.logintricyfare.RecyclerViewAdapter;
import com.example.logintricyfare.Routes;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DriversInfoFragment extends Fragment {

    View rootView;
    DatabaseReference dReference;
    FirebaseAuth mAuth;
    private String currentdriversID;
    private FirebaseFirestore firestore;
    private DriversRecyclerViewAdapter mDriversRecyclerAdapter;
    private RecyclerView mDriversRecyclerView;
    private DriversInformation model;
    private ArrayList<DriversInformation> driversInfoList;

    public DriversInfoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_drivers_info, container, false);

        mDriversRecyclerView = (RecyclerView) rootView.findViewById(R.id.driversRecyclerView);
        firestore = FirebaseFirestore.getInstance();
        driversInfoList = new ArrayList<>();


        GetDriversInfo();


        return rootView;
    }

    private void GetDriversInfo() {

        firestore.collection("drivers").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    ArrayList<DocumentSnapshot> list = (ArrayList<DocumentSnapshot>) queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        model = d.toObject(DriversInformation.class);

                        driversInfoList.add(model);

                        Log.d("Firestore", "Driver info retrieved: " + model.getFullname());

                        mDriversRecyclerAdapter = new DriversRecyclerViewAdapter(driversInfoList, getContext());
                        mDriversRecyclerView.setAdapter(mDriversRecyclerAdapter);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        mDriversRecyclerView.setLayoutManager(layoutManager);

                    }
                }else {
                    Log.d("Firestore", "No drivers found.");
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Firestore", "Error fetching drivers: " + e.getMessage());
            }
        });
    }
}