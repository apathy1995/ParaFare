package com.example.logintricyfare;

public class DriversInformation {

    private String driversName;
    private int driversProfile;
    private String driversEmail;
    private String driversBodyNumber;

    public String getDriversName() {
        return driversName;
    }

    public void setDriversName(String driversName) {
        this.driversName = driversName;
    }


    public String getDriversEmail() {
        return driversEmail;
    }

    public void setDriversEmail(String driversEmail) {
        this.driversEmail = driversEmail;
    }

    public String getDriversBodyNumber() {
        return driversBodyNumber;
    }

    public void setDriversBodyNumber(String driversBodyNumber) {
        this.driversBodyNumber = driversBodyNumber;
    }

    public int getDriversProfile() {
        return driversProfile;
    }

    public void setDriversProfile(int driversProfile) {
        this.driversProfile = driversProfile;
    }

    public DriversInformation(String driversName, int driversProfile, String driversEmail, String driversBodyNumber) {
        this.driversName = driversName;
        this.driversProfile = driversProfile;
        this.driversEmail = driversEmail;
        this.driversBodyNumber = driversBodyNumber;

    }
}

