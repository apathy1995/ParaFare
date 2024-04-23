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

import com.example.logintricyfare.DriversInformation;
import com.example.logintricyfare.DriversRecyclerViewAdapter;
import com.example.logintricyfare.R;
import com.example.logintricyfare.RecyclerViewAdapter;
import com.example.logintricyfare.Routes;

import java.util.ArrayList;
import java.util.List;

public class DriversInfoFragment extends Fragment {

    Context mContext;
    List<DriversInformation> mListDrivers;
    RecyclerView mDriversRecyclerView;
    DriversRecyclerViewAdapter mDriversRecyclerAdapter;
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

        rootView = inflater.inflate(R.layout.fragment_drivers_info, container, false);

        mDriversRecyclerView = rootView.findViewById(R.id.driversRecyclerView);
        mListDrivers = new ArrayList<>();

        fragmentManager = getActivity().getSupportFragmentManager();

        mListDrivers.add(new DriversInformation("Ernesto Macalimba",R.drawable.profile_bg,"ernestomacalimba@gmail.com","1234"));
        mListDrivers.add(new DriversInformation("Renaldo Santos",R.drawable.profile_bg,"renaldosantos@gmail.com","1234"));
        mListDrivers.add(new DriversInformation("Ricardo Castro",R.drawable.profile_bg,"ricardocastro@gmail.com","1234"));
        mListDrivers.add(new DriversInformation("Albert Mendoza",R.drawable.profile_bg,"marjhonaligway@gmail.com","1234"));
        mListDrivers.add(new DriversInformation("Marjhon Aligway",R.drawable.profile_bg,"ernestomacalimba@gmail.com","1234"));
        mListDrivers.add(new DriversInformation("Manuel Panganiban",R.drawable.profile_bg,"manuelpanganiban@gmail.com","1234"));
        mListDrivers.add(new DriversInformation("Florentino Cruz",R.drawable.profile_bg,"florentinocruz@gmail.com","1234"));
        mListDrivers.add(new DriversInformation("Rufino Francisco",R.drawable.profile_bg,"rufinofrancisco@gmail.com","1234"));
        mListDrivers.add(new DriversInformation("Ariel Legaspi",R.drawable.profile_bg,"ariellegaspi@gmail.com","1234"));
        mListDrivers.add(new DriversInformation("Ciriaco Ramos Jr.",R.drawable.profile_bg,"ciriacoramos@gmail.com","1234"));

        mDriversRecyclerAdapter = new DriversRecyclerViewAdapter(getContext(),mListDrivers);
        mDriversRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mDriversRecyclerView.setAdapter(mDriversRecyclerAdapter);

        return rootView;
    }
}