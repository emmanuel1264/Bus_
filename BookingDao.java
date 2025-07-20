package com.bus.dao;

import com.bus.models.Booking;
import com.bus.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {

    // ✅ Make a new booking
    public void makeBooking(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, schedule_id, seat_number) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getScheduleId());
            stmt.setInt(3, booking.getSeatNumber());

            stmt.executeUpdate();
            System.out.println("✅ Booking successful!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Get bookings by user
    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("schedule_id"),
                        rs.getInt("seat_number")
                );
                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }

    // ✅ Count total bookings for a schedule (used to check capacity)
    public int countBookingsForSchedule(int scheduleId) {
        String sql = "SELECT COUNT(*) AS total FROM bookings WHERE schedule_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
