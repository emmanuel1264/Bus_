package com.bus.dao;

import com.bus.models.Schedule;
import com.bus.utils.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDao {

    // ✅ Add a new schedule
    public void addSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedules (bus_id, route_id, departure_time) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, schedule.getBusId());
            stmt.setInt(2, schedule.getRouteId());
            stmt.setTimestamp(3, Timestamp.valueOf(schedule.getDepartureTime()));

            stmt.executeUpdate();
            System.out.println("✅ Schedule added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Get all schedules
    public List<Schedule> getAllSchedules() {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM schedules";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Schedule schedule = new Schedule(
                        rs.getInt("id"),
                        rs.getInt("bus_id"),
                        rs.getInt("route_id"),
                        rs.getTimestamp("departure_time").toLocalDateTime()
                );
                schedules.add(schedule);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return schedules;
    }

    // ✅ Get Bus ID by Schedule ID (optional helper method)
    public int getBusIdBySchedule(int scheduleId) {
        String sql = "SELECT bus_id FROM schedules WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("bus_id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
