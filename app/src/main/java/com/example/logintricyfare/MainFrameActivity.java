package com.example.logintricyfare;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainFrameActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    ImageButton buttonDrawerToggle;
    NavigationView navigationView;

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    MyViewpagerAdapter myViewpagerAdapter;

    FrameLayout frameLayout;
    ValueEventListener eventListener;


    List<Routes> mListRoutes;
    List<DriversInformation> mListDrivers;
    List<MyTrips> mMyTripsRoutes;
    RecyclerViewAdapter mRecyclerAdapter;
    DriversRecyclerViewAdapter mDriversRecyclerAdapter;
    RecyclerView mRecyclerView;
    RecyclerView mDriversRecyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;
    RecyclerView mMyTripsRecyclerView;
    RecyclerView mReportDriverRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.wrapper, new DriversInfoFragment())
                .commit();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.wrapper, new RoutesFragment())
                .commit();


        // Add MyTripsFragment to activity
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mytrips_fragment, new MyTripsFragment())
                .commit();


        mRecyclerView = findViewById(R.id.routesRecyclerView);


        mDriversRecyclerView = findViewById(R.id.driversRecyclerView);


        mMyTripsRecyclerView = findViewById(R.id.myTripsRecyclerView);


        mReportDriverRecyclerView = findViewById(R.id.reportDriverRecyclerView);


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
                    getSupportFragmentManager().beginTransaction().replace(R.id.mytrips_fragment, new MyTripsFragment()).commit();
                }
                if (itemId == R.id.nav_reportdriver) {
                    Toast.makeText(MainFrameActivity.this, "Report Driver Clicked", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ReportDriverFragment()).commit();
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
