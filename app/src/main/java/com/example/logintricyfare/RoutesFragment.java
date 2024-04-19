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

import com.example.logintricyfare.R;
import com.example.logintricyfare.RecyclerViewAdapter;
import com.example.logintricyfare.Routes;

import java.util.ArrayList;
import java.util.List;

public class RoutesFragment extends Fragment {
    
    Context mContext;
    List<Routes> mListRoutes;
    RecyclerView mRecyclerView;
    RecyclerViewAdapter mRecyclerAdapter;
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
        rootView = inflater.inflate(R.layout.fragment_routes, container, false);

        mRecyclerView = rootView.findViewById(R.id.routesRecyclerView);
        mListRoutes = new ArrayList<>();

        fragmentManager = getActivity().getSupportFragmentManager();

        mListRoutes.add(new Routes("Bolacan - Turo Hangga", "1km - 1 person", "₱20.00",R.drawable.location_icon));
        mListRoutes.add(new Routes("Bolacan - Turo Hangga", "1km - 2 person", "₱30.00",R.drawable.location_icon));
        mListRoutes.add(new Routes("Bolacan - Turo Hangga", "1km - 3 person", "₱45.00",R.drawable.location_icon));
        mListRoutes.add(new Routes("Bolacan - Tollgate", "1.5km - 1 person", "₱30.00",R.drawable.location_icon));
        mListRoutes.add(new Routes("Bolacan - Tollgate", "1.5km - 2 person", "₱40.00",R.drawable.location_icon));
        mListRoutes.add(new Routes("Bolacan - Tollgate", "1.5km - 3 person", "₱45.00",R.drawable.location_icon));
        mListRoutes.add(new Routes("Bolacan - Bocaue Crossing", "2.5km - 1 person", "₱60.00",R.drawable.location_icon));
        mListRoutes.add(new Routes("Bolacan - Bocaue Crossing", "2.5km - 2 person", "₱70.00",R.drawable.location_icon));
        mListRoutes.add(new Routes("Bolacan - Bocaue Crossing", "2.5km - 3 person", "₱75.00",R.drawable.location_icon));
        mListRoutes.add(new Routes("Bolacan - JIL School", "3.5km - 1 person", "₱60.00",R.drawable.location_icon));

        mRecyclerAdapter = new RecyclerViewAdapter(getContext(),mListRoutes);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mRecyclerView.setAdapter(mRecyclerAdapter);

        return rootView;
    }
}
