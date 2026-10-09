
package com.hotelreservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    // 1. Reserve a room
    public boolean reserveRoom(String guestName,
                               int roomNumber,
                               String contactNumber)
            throws SQLException {

        String sql = "INSERT INTO reservations " +
                "(guest_name, room_number, contact_number) " +
                "VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, guestName);
            ps.setInt(2, roomNumber);
            ps.setString(3, contactNumber);

            return ps.executeUpdate() > 0;
        }
    }

    // 2. View all reservations
    public List<Reservation> viewReservations()
            throws SQLException {

        String sql = "SELECT reservation_id, guest_name, " +
                "room_number, contact_number, reservation_date " +
                "FROM reservations";

        List<Reservation> reservations = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reservation reservation = new Reservation(
                        rs.getInt("reservation_id"),
                        rs.getString("guest_name"),
                        rs.getInt("room_number"),
                        rs.getString("contact_number"),
                        rs.getTimestamp("reservation_date")
                );

                reservations.add(reservation);
            }
        }

        return reservations;
    }

    // 3. Get room number
    public Integer getRoomNumber(int reservationId,
                                 String guestName)
            throws SQLException {

        String sql = "SELECT room_number FROM reservations " +
                "WHERE reservation_id = ? AND guest_name = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservationId);
            ps.setString(2, guestName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("room_number");
                }
            }
        }

        return null;
    }

    // 4. Update reservation
    public boolean updateReservation(int reservationId,
                                     String guestName,
                                     int roomNumber,
                                     String contactNumber)
            throws SQLException {

        String sql = "UPDATE reservations SET " +
                "guest_name = ?, room_number = ?, " +
                "contact_number = ? WHERE reservation_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, guestName);
            ps.setInt(2, roomNumber);
            ps.setString(3, contactNumber);
            ps.setInt(4, reservationId);

            return ps.executeUpdate() > 0;
        }
    }

    // 5. Delete reservation
    public boolean deleteReservation(int reservationId)
            throws SQLException {

        String sql = "DELETE FROM reservations " +
                "WHERE reservation_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, reservationId);

            return ps.executeUpdate() > 0;
        }
    }
}
