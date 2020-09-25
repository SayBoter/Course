package com.demo.types;

public class Room {
    private int roomNumber;
    private String phoneNumber;
    private int roomType;
    private int floor;
    private String description;

    public Room(int roomNumber, String phoneNumber, int room_type, int floor, String description) {
        this.roomNumber = roomNumber;
        this.phoneNumber = phoneNumber;
        this.roomType = room_type;
        this.floor = floor;
        this.description = description;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
