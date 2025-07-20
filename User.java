package com.bus.models;

public class User {
    private int id;
    private String username;
    private String password;

    // Constructor for login/registration (no ID yet)
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Constructor with ID (used when loading from DB)
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Setters (optional – keep if user data may be updated)
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
