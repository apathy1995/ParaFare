package com.example.logintricyfare;

public class DriversInformation {

    private String fullname, email, bodynumber, phonenumber, image;

    public DriversInformation() {
    }

    public DriversInformation(String fullname, String username, String email, String bodynumber, String phonenumber, String password, String image) {
        this.fullname = fullname;
        this.email = email;
        this.bodynumber = bodynumber;
        this.phonenumber = phonenumber;
        this.image = image;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBodynumber() {
        return bodynumber;
    }

    public void setBodynumber(String bodynumber) {
        this.bodynumber = bodynumber;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


