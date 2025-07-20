package com.bus.models;

import java.time.LocalDateTime;

public class Schedule {
    private int id;
    private int busId;
    private String origin;
    private String destination;
    private LocalDateTime departureTime;

    // Constructor without ID
    public Schedule(int busId, String origin, String destination, LocalDateTime departureTime) {
        this.busId = busId;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    // Constructor with ID
    public Schedule(int id, int busId, String origin, String destination, LocalDateTime departureTime) {
        this.id = id;
        this.busId = busId;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public int getBusId() {
        return busId;
    }

    public void setBusId(int busId) {
        this.busId = busId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
}
