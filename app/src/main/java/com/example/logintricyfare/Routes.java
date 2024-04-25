package com.example.logintricyfare;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Routes {

    private String routesName, routesPrice, routesDistance, imageLoc;

    public Routes(){

    }

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

    public String getImageLoc() {
        return imageLoc;
    }

    public void setImageLoc(String imageLoc) {
        this.imageLoc = imageLoc;
    }


    public Routes(String routesName, String routesDistance, String routesPrice, String imageLoc) {
        this.routesName = routesName;
        this.routesDistance = routesDistance;
        this.routesPrice = routesPrice;
        this.imageLoc = imageLoc;
    }
}

