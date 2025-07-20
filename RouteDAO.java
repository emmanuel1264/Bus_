package com.bus.dao;

import com.bus.models.Route;
import com.bus.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO {

    public List<Route> getAllRoutes() {
        List<Route> routes = new ArrayList<>();
        String query = "SELECT * FROM routes";

        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                routes.add(new Route(
                        rs.getInt("id"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getDouble("fare")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routes;
    }

    public void addRoute(Route route) {
        String query = "INSERT INTO routes (source, destination, fare) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, route.getSource());
            stmt.setString(2, route.getDestination());
            stmt.setDouble(3, route.getFare());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoute(int id) {
        String query = "DELETE FROM routes WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
