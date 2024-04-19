package com.example.logintricyfare;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Routes {

    private String routesName;
    private String routesDistance;
    private String routesPrice;

    private int locationIcon;

    public String getRoutesName() {
        return routesName;
    }

    public void setRoutesName(String routesName) {
        this.routesName = routesName;
    }

    public String getRoutesDistance() {
        return routesDistance;
    }

    public void setRoutesDistance(String routesDistance) {
        this.routesDistance = routesDistance;
    }

    public String getRoutesPrice() {
        return routesPrice;
    }

    public void setRoutesPrice(String routesPrice) {
        this.routesPrice = routesPrice;
    }

    public int getLocationIcon() {
        return locationIcon;
    }

    public void setLocationIcon(int locationIcon) {
        this.locationIcon = locationIcon;
    }

    public Routes(String routesName, String routesDistance, String routesPrice, int locationIcon) {
        this.routesName = routesName;
        this.routesDistance = routesDistance;
        this.routesPrice = routesPrice;
        this.locationIcon = locationIcon;
    }
}

