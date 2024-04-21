package com.example.logintricyfare;

public class MyTrips {

    private String myTripsRoutesName;
    private String myTripsRoutesDistance;
    private String myTripsRoutesCurrentDate;
    private String myTripsRoutesPrice;
    private int myTripsLocationIcon;

    public String getMyTripsRoutesName() {
        return myTripsRoutesName;
    }

    public void setMyTripsRoutesName(String myTripsRoutesName) {
        this.myTripsRoutesName = myTripsRoutesName;
    }

    public String getMyTripsRoutesDistance() {
        return myTripsRoutesDistance;
    }

    public void setMyTripsRoutesDistance(String myTripsRoutesDistance) {
        this.myTripsRoutesDistance = myTripsRoutesDistance;
    }

    public String getMyTripsRoutesCurrentDate() {
        return myTripsRoutesCurrentDate;
    }

    public void setMyTripsRoutesCurrentDate(String myTripsRoutesCurrentDate) {
        this.myTripsRoutesCurrentDate = myTripsRoutesCurrentDate;
    }

    public String getMyTripsRoutesPrice() {
        return myTripsRoutesPrice;
    }

    public void setMyTripsRoutesPrice(String myTripsRoutesPrice) {
        this.myTripsRoutesPrice = myTripsRoutesPrice;
    }

    public int getMyTripsLocationIcon() {
        return myTripsLocationIcon;
    }

    public void setMyTripsLocationIcon(int myTripsLocationIcon) {
        this.myTripsLocationIcon = myTripsLocationIcon;
    }

    public MyTrips(String myTripsRoutesName, String myTripsRoutesDistance, String myTripsRoutesCurrentDate, String myTripsRoutesPrice, int myTripsLocationIcon) {
        this.myTripsRoutesName = myTripsRoutesName;
        this.myTripsRoutesDistance = myTripsRoutesDistance;
        this.myTripsRoutesCurrentDate = myTripsRoutesCurrentDate;
        this.myTripsRoutesPrice = myTripsRoutesPrice;
        this.myTripsLocationIcon = myTripsLocationIcon;
    }
}
