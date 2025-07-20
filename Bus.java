package com.bus.models;

public class Bus {
    private int id;
    private String busNumber;
    private int capacity;

    // Constructor without ID (for inserting new bus)
    public Bus(String busNumber, int capacity) {
        this.busNumber = busNumber;
        this.capacity = capacity;
    }

    // Constructor with ID (for reading from database)
    public Bus(int id, String busNumber, int capacity) {
        this.id = id;
        this.busNumber = busNumber;
        this.capacity = capacity;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
