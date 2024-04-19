package com.example.logintricyfare;

public class DriversInformation {

    private String driversName;
    private int tricycleIcon;

    public String getDriversName() {
        return driversName;
    }

    public void setDriversName(String driversName) {
        this.driversName = driversName;
    }

    public int getTricycleIcon() {
        return tricycleIcon;
    }

    public void setTricycleIcon(int tricycleIcon) {
        this.tricycleIcon = tricycleIcon;
    }

    public DriversInformation(String driversName, int tricycleIcon) {
        this.driversName = driversName;
        this.tricycleIcon = tricycleIcon;
    }
}

