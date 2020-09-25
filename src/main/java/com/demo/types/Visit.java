package com.demo.types;

public class Visit {
    private String arrival;
    private int idClient;
    private int room;
    private String departure;

    public Visit(String arrival, int id_client, int room, String departure) {
        this.arrival = arrival;
        this.idClient = id_client;
        this.room = room;
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }
}
