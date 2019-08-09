package com.friends.springbootjson.entity;

public class Apartment {
    private String roommate;
    private String payRentMan;

    public Apartment() {
    }

    public Apartment(String roommate, String payRentMan) {
        this.roommate = roommate;
        this.payRentMan = payRentMan;
    }

    public String getRoommate() {
        return roommate;
    }

    public void setRoommate(String roommate) {
        this.roommate = roommate;
    }

    public String getPayRentMan() {
        return payRentMan;
    }

    public void setPayRentMan(String payRentMan) {
        this.payRentMan = payRentMan;
    }
}
