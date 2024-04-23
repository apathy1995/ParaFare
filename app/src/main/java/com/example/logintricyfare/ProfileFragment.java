package com.example.logintricyfare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


public class ProfileFragment extends Fragment {

    private TabLayout tabLayout;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        tabLayout = requireActivity().findViewById(R.id.tab_layout);
        tabLayout.setVisibility(View.GONE);

        return rootView;
    }
}
