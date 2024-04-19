package com.example.logintricyfare;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.logintricyfare.DriversInfoFragment;
import com.example.logintricyfare.RoutesFragment;

public class MyViewpagerAdapter extends FragmentStateAdapter {

    public MyViewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new RoutesFragment();

            case 1:
                return new DriversInfoFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

