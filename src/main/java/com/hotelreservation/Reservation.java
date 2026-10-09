
package com.hotelreservation;

import java.sql.Timestamp;

public class Reservation {

    private int reservationId;
    private String guestName;
    private int roomNumber;
    private String contactNumber;
    private Timestamp reservationDate;

    public Reservation() {
    }

    public Reservation(
            int reservationId,
            String guestName,
            int roomNumber,
            String contactNumber,
            Timestamp reservationDate) {

        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.contactNumber = contactNumber;
        this.reservationDate = reservationDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Timestamp getReservationDate() {
        return reservationDate;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
