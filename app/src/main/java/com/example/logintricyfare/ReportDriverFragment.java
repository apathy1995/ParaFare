package com.example.logintricyfare;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReportDriverFragment extends Fragment implements View.OnClickListener{

    View rootView;
    private TabLayout tabLayout;

    FloatingActionButton fb;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference reference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    RecyclerView mReportDriverRecyclerView;
    CircleImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_report_driver, container, false);
        tabLayout = requireActivity().findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentUserid = user.getUid();

        mReportDriverRecyclerView = getActivity().findViewById(R.id.reportDriverRecyclerView);
        mReportDriverRecyclerView.setHasFixedSize(true);
        mReportDriverRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        databaseReference = database.getReference("All ReportDriver");

        imageView = getActivity().findViewById(R.id.reportdriver_);
        fb = getActivity().findViewById(R.id.floatingActionBtn);
        reference = db.collection("users").document(currentUserid);

        imageView.setOnClickListener(this);
        fb.setOnClickListener(this);

        FirebaseRecyclerOptions<ReportDriver> options = new FirebaseRecyclerOptions.Builder<ReportDriver>()
                .setQuery(databaseReference, ReportDriver.class).build();

        FirebaseRecyclerAdapter<ReportDriver, Viewholder_ReportDriver> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ReportDriver, Viewholder_ReportDriver>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Viewholder_ReportDriver holder, int position, @NonNull ReportDriver model) {

                        holder.setitem(getActivity(), model.getReportDriverName(), model.getImgreportdriver(), model.getReportDriverIssue()
                                , model.getReportBodyNumber(), model.getFname(), model.getPhonenumber(), model.getKey()
                                , model.getRdtime(), model.getUsername());
                    }

                    @NonNull
                    @Override
                    public Viewholder_ReportDriver onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reportdriver_item, parent, false);
                        return new Viewholder_ReportDriver(view);
                    }
                };

        firebaseRecyclerAdapter.startListening();
        mReportDriverRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.reportdriver_) {
            // Handle the click on iv_f2
        } else if (v.getId() == R.id.floatingActionBtn) {
            Intent intent = new Intent(getActivity(), ReportDriverActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        reference.get()
                .addOnCompleteListener((task) -> {
                    if (task.getResult().exists()){
                        String imgreportdriver = task.getResult().getString("imgreportdriver");

                        Picasso.get().load(imgreportdriver).into(imageView);
                    }else {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}