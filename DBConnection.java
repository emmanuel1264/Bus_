package com.bus.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/bus_management?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // change to your actual MySQL username
    private static final String PASSWORD = "your_actual_password"; // replace with your real MySQL password

    public static Connection getConnection() {
        try {
            // Ensures the JDBC driver is loaded (optional for modern JDBC, but safe)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
            return null;
        }
    }
}
