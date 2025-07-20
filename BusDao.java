package com.bus.dao;

import com.bus.models.Bus;
import com.bus.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusDao {

    // ✅ Add a new bus
    public void addBus(Bus bus) {
        String sql = "INSERT INTO buses (bus_number, capacity) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bus.getBusNumber());
            stmt.setInt(2, bus.getCapacity());

            stmt.executeUpdate();
            System.out.println("✅ Bus added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Get all buses
    public List<Bus> getAllBuses() {
        List<Bus> buses = new ArrayList<>();
        String sql = "SELECT * FROM buses";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Bus bus = new Bus(
                        rs.getInt("id"),
                        rs.getString("bus_number"),
                        rs.getInt("capacity")
                );
                buses.add(bus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return buses;
    }

    // ✅ Delete bus by ID
    public void deleteBus(int busId) {
        String sql = "DELETE FROM buses WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, busId);
            stmt.executeUpdate();
            System.out.println("✅ Bus deleted successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Get capacity for a given bus
    public int getCapacityByBusId(int busId) {
        String sql = "SELECT capacity FROM buses WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, busId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("capacity");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // default if not found
    }
}
