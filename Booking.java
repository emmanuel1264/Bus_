package com.bus.models;

public class Booking {
    private int id;
    private int userId;
    private int scheduleId;
    private int seatNumber;

    // Constructor without ID (for new bookings)
    public Booking(int userId, int scheduleId, int seatNumber) {
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.seatNumber = seatNumber;
    }

    // Constructor with ID (for reading from DB)
    public Booking(int id, int userId, int scheduleId, int seatNumber) {
        this.id = id;
        this.userId = userId;
        this.scheduleId = scheduleId;
        this.seatNumber = seatNumber;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}
