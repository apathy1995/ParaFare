package com.example.logintricyfare;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyTripsFragment extends Fragment implements View.OnClickListener {

    private TabLayout tabLayout;

    FloatingActionButton fb;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference reference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    RecyclerView mMyTripsRecyclerView;
    CircleImageView imageView;
    TextView totalFareTextView;
    Button deletebtn;

    Context mContext;
    List<MyTrips> mMyTripsRoutes;
    FragmentManager fragmentManager;
    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_trips, container, false);

        tabLayout = requireActivity().findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid = user.getUid();

        mMyTripsRecyclerView = getActivity().findViewById(R.id.myTripsRecyclerView);
        mMyTripsRecyclerView.setHasFixedSize(true);
        mMyTripsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference = database.getReference("All MyTrips");

        imageView = getActivity().findViewById(R.id.location);
        fb = getActivity().findViewById(R.id.floatingActionButton);
        reference = db.collection("users").document(currentUserid);

        imageView.setOnClickListener(this);
        fb.setOnClickListener(this);

        FirebaseRecyclerOptions<MyTrips> options = new FirebaseRecyclerOptions.Builder<MyTrips>()
                .setQuery(databaseReference, MyTrips.class).build();

        FirebaseRecyclerAdapter<MyTrips, Viewholder_MyTrips> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<MyTrips, Viewholder_MyTrips>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Viewholder_MyTrips holder, int position, @NonNull MyTrips model) {

                        holder.setitem(getActivity(), model.getRoutesName(), model.getLocationIcon(), model.getNumberOfPerson()
                                , model.getRoutesPrice(), model.getFname(), model.getPhonenumber(), model.getKey()
                                , model.getTime(), model.getUsername());
                    }

                    @NonNull
                    @Override
                    public Viewholder_MyTrips onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mytrips_item, parent, false);
                        return new Viewholder_MyTrips(view);
                    }
                };

        firebaseRecyclerAdapter.startListening();
        mMyTripsRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.location) {
            // Handle the click on iv_f2
        } else if (v.getId() == R.id.floatingActionButton) {
            Intent intent = new Intent(getActivity(), MyTripsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        reference.get()
                .addOnCompleteListener((task) -> {
                    if (task.getResult().exists()) {
                        String locationIcon = task.getResult().getString("locationIcon");

                        Picasso.get().load(locationIcon).into(imageView);
                    } else {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}