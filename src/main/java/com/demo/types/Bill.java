package com.demo.types;

public class Bill {
    private String arrival;
    private int idClient;
    private double price;

    public Bill(String arrival, int id_client, double price) {
        this.arrival = arrival;
        this.idClient = id_client;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
