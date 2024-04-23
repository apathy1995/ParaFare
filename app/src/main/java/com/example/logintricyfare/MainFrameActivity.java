package com.example.logintricyfare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainFrameActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    MyViewpagerAdapter myViewpagerAdapter;

    FrameLayout frameLayout;


    List<Routes> mListRoutes;
    List<DriversInformation> mListDrivers;
    List<MyTrips> mMyTripsRoutes;
    RecyclerViewAdapter mRecyclerAdapter;
    DriversRecyclerViewAdapter mDriversRecyclerAdapter;
    RecyclerView mRecyclerView;
    RecyclerView mDriversRecyclerView;

    MyTripsRecyclerViewAdapter mMyTripsRecyclerAdapter;
    RecyclerView mMyTripsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);


        mRecyclerView = findViewById(R.id.routesRecyclerView);
        mRecyclerAdapter = new RecyclerViewAdapter(getApplicationContext(),mListRoutes);


        mDriversRecyclerView = findViewById(R.id.driversRecyclerView);
        mDriversRecyclerAdapter = new DriversRecyclerViewAdapter(getApplicationContext(),mListDrivers);

        mMyTripsRecyclerView = findViewById(R.id.myTripsRecyclerView);
        mMyTripsRecyclerAdapter = new MyTripsRecyclerViewAdapter(getApplicationContext(),mMyTripsRoutes);


        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);

        myViewpagerAdapter = new MyViewpagerAdapter(this);
        viewPager2.setAdapter(myViewpagerAdapter);

        frameLayout = findViewById(R.id.frameLayout);
        viewPager2.setAdapter(myViewpagerAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager2.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.GONE);

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                    case 1:
                        tabLayout.getTabAt(position).select();
                }
                super.onPageSelected(position);

            }
        });


        drawer_layout = findViewById(R.id.drawer_layout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.nav_view);

        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer_layout.open();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                frameLayout.setVisibility(View.VISIBLE);
                viewPager2.setVisibility(View.GONE);
                int itemId = item.getItemId();


                if (itemId == R.id.nav_home) {
                    Toast.makeText(MainFrameActivity.this, "Home Clicked", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment()).commit();
                }
                if (itemId == R.id.nav_profile) {
                    Toast.makeText(MainFrameActivity.this, "Profile Clicked", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ProfileFragment()).commit();
                }
                if (itemId == R.id.nav_notif) {
                    Toast.makeText(MainFrameActivity.this, "Notification Clicked", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new NotificationFragment()).commit();
                }
                if (itemId == R.id.nav_mytrips) {
                    Toast.makeText(MainFrameActivity.this, "My Trips Clicked", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MyTripsFragment()).commit();
                }
                if (itemId == R.id.nav_pendingpayment) {
                    Toast.makeText(MainFrameActivity.this, "Pending Payment Clicked", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new PendingPaymentFragment()).commit();
                }
                if (itemId == R.id.nav_home_){
                    Intent intent = new Intent(MainFrameActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
                if (itemId == R.id.nav_logout){
                    FirebaseAuth.getInstance().signOut();
                    Intent loginActivity = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(loginActivity);
                    finish();
                }
                drawer_layout.close();

                return false;
            }
        });
    }


}
