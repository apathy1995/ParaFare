package com.example.logintricyfare;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    View rootview;
    ViewPager2 viewPager2;
    TabLayout tabLayout;

    public HomeFragment(){

    }

    public static HomeFragment getInstance(){
        return new HomeFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.activity_main_frame, container, false);

        tabLayout = rootview.findViewById(R.id.tab_layout);
        viewPager2 = rootview.findViewById(R.id.view_pager);





        return rootview;
    }
}



