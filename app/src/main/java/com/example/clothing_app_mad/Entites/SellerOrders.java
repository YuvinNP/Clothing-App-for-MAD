package com.example.clothing_app_mad.Entites;

public class SellerOrders {

    private String State, address, city, date, name, phone, time;
    //private String TotalAmount;


    public SellerOrders() {
    }

    public SellerOrders(String state, String address, String city, String date, String name, String phone, String time) {
        State = state;
        this.address = address;
        this.city = city;
        this.date = date;
        this.name = name;
        this.phone = phone;
        this.time = time;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
