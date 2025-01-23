package com.example.datasentapi.ModalClass;

public class ModalUser {
    private String name;
    private String email;
    private String password;
    private String mobile;
    private String pincode;
    private String state;
    private String houseno;
    private String roadname;
    private String userId;

    public ModalUser()
    {

    }
    public ModalUser(String name, String email, String password, String mobile, String pincode, String state, String houseno, String roadname,String userId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.pincode = pincode;
        this.state = state;
        this.houseno = houseno;
        this.roadname = roadname;
        this.userId= userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHouseno() {
        return houseno;
    }

    public void setHouseno(String houseno) {
        this.houseno = houseno;
    }

    public String getRoadname() {
        return roadname;
    }

    public void setRoadname(String roadname) {
        this.roadname = roadname;
    }

    public String getUserId(){ return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
}
