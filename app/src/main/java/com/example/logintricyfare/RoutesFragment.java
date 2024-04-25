package com.example.logintricyfare;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logintricyfare.R;
import com.example.logintricyfare.RecyclerViewAdapter;
import com.example.logintricyfare.Routes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RoutesFragment extends Fragment {
    
    Context mContext;
    private List<Routes> routesInfoList;
    private FirebaseFirestore fStore;
    private Routes model;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerAdapter;
    FragmentManager fragmentManager;
    View rootView;

    public RoutesFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_routes, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.routesRecyclerView);
        fStore = FirebaseFirestore.getInstance();
        routesInfoList = new ArrayList<>();

        GetRoutesInfo();

        return rootView;
    }

    private void GetRoutesInfo() {

        fStore.collection("routes").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()) {
                    ArrayList<DocumentSnapshot> list = (ArrayList<DocumentSnapshot>) queryDocumentSnapshots.getDocuments();
                    for (DocumentSnapshot d : list) {
                        model = d.toObject(Routes.class);

                        routesInfoList.add(model);

                        Log.d("Firestore", "Routes info retrieved: " + model.getRoutesName());

                        mRecyclerAdapter = new RecyclerViewAdapter(routesInfoList, getContext());
                        mRecyclerView.setAdapter(mRecyclerAdapter);

                        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        mRecyclerView.setLayoutManager(layoutManager);
                    }
                } else {
                    Log.d("Firestore", "No routes found.");
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
