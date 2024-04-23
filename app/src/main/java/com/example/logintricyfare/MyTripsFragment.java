package com.example.logintricyfare;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MyTripsFragment extends Fragment {

    private TabLayout tabLayout;

    Context mContext;
    List<MyTrips> mMyTripsRoutes;
    RecyclerView mMyTripsRecyclerView;
    MyTripsRecyclerViewAdapter mMyTripsRecyclerAdapter;
    FragmentManager fragmentManager;
    View rootView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_my_trips, container, false);

        tabLayout = requireActivity().findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);

        mMyTripsRecyclerView = rootView.findViewById(R.id.myTripsRecyclerView);
        mMyTripsRoutes = new ArrayList<>();

        fragmentManager = getActivity().getSupportFragmentManager();

        mMyTripsRoutes.add(new MyTrips("Bolacan - Turo Hangga", "1km - 1 person", "Wednesday, June 26, 2024","₱20.00", R.drawable.location_icon));
        mMyTripsRoutes.add(new MyTrips("Bolacan - Turo Hangga", "1km - 2 person", "Wednesday, June 26, 2024","₱30.00",R.drawable.location_icon));
        mMyTripsRoutes.add(new MyTrips("Bolacan - Turo Hangga", "1km - 3 person", "Wednesday, June 26, 2024","₱45.00", R.drawable.location_icon));
        mMyTripsRoutes.add(new MyTrips("Bolacan - Tollgate", "1.5km - 1 person", "Wednesday, June 26, 2024","₱30.00", R.drawable.location_icon));
        mMyTripsRoutes.add(new MyTrips("Bolacan - Tollgate", "1.5km - 2 person", "Wednesday, June 26, 2024","₱40.00", R.drawable.location_icon));
        mMyTripsRoutes.add(new MyTrips("Bolacan - Tollgate", "1.5km - 3 person", "Wednesday, June 26, 2024","₱45.00", R.drawable.location_icon));
        mMyTripsRoutes.add(new MyTrips("Bolacan - Bocaue Crossing", "2.5km - 1 person", "Wednesday, June 26, 2024","₱60.00", R.drawable.location_icon));
        mMyTripsRoutes.add(new MyTrips("Bolacan - Bocaue Crossing", "2.5km - 2 person", "Wednesday, June 26, 2024","₱70.00", R.drawable.location_icon));
        mMyTripsRoutes.add(new MyTrips("Bolacan - Bocaue Crossing", "2.5km - 3 person", "Wednesday, June 26, 2024","₱75.00", R.drawable.location_icon));
        mMyTripsRoutes.add(new MyTrips("Bolacan - JIL School", "3.5km - 1 person", "Wednesday, June 26, 2024","₱60.00", R.drawable.location_icon));

        mMyTripsRecyclerAdapter = new MyTripsRecyclerViewAdapter(getContext(),mMyTripsRoutes);
        mMyTripsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mMyTripsRecyclerView.setAdapter(mMyTripsRecyclerAdapter);

        return rootView;
    }
}
