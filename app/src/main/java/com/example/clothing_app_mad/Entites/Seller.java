package com.example.clothing_app_mad.Entites;

public class Seller {

    private String sellerID;

    private String sname;

    private String image;

    private String email;

    private int contactNo;

    private String addressLine1;

    private String addressLine2;

    private String district;

    private String password;

    public Seller(String sname, String email, int contactNo, String addressLine1, String addressLine2, String district, String password) {
        this.sname = sname;
        this.email = email;
        this.contactNo = contactNo;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.district = district;
        this.password = password;
    }

    public Seller(){

    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
