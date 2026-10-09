
package com.hotelreservation;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {

    public static void main(String[] args) {

        ReservationDAO dao = new ReservationDAO();

        try (Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("\nHOTEL MANAGEMENT SYSTEM");
                System.out.println("1. Reserve a room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");

                int choice;

                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                try {
                    switch (choice) {

                        case 1: {
                            System.out.print("Enter guest name: ");
                            String name = scanner.nextLine().trim();

                            System.out.print("Enter room number: ");
                            int room = Integer.parseInt(
                                    scanner.nextLine());

                            System.out.print("Enter contact number: ");
                            String contact = scanner.nextLine().trim();

                            if (name.isEmpty() || contact.isEmpty()
                                    || room <= 0) {
                                System.out.println(
                                        "Enter valid reservation details.");
                                break;
                            }

                            if (dao.reserveRoom(name, room, contact)) {
                                System.out.println(
                                        "Reservation successful!");
                            } else {
                                System.out.println(
                                        "Reservation failed.");
                            }
                            break;
                        }

                        case 2: {
                            List<Reservation> reservations =
                                    dao.viewReservations();

                            if (reservations.isEmpty()) {
                                System.out.println(
                                        "No reservations found.");
                                break;
                            }

                            System.out.println("\nCURRENT RESERVATIONS");

                            for (Reservation r : reservations) {
                                System.out.println(
                                        "ID: " + r.getReservationId()
                                                + " | Guest: " + r.getGuestName()
                                                + " | Room: " + r.getRoomNumber()
                                                + " | Contact: "
                                                + r.getContactNumber()
                                                + " | Date: "
                                                + r.getReservationDate()
                                );
                            }
                            break;
                        }

                        case 3: {
                            System.out.print("Enter reservation ID: ");
                            int id = Integer.parseInt(
                                    scanner.nextLine());

                            System.out.print("Enter guest name: ");
                            String name = scanner.nextLine().trim();

                            Integer room = dao.getRoomNumber(id, name);

                            if (room != null) {
                                System.out.println(
                                        "Room number: " + room);
                            } else {
                                System.out.println(
                                        "Reservation not found.");
                            }
                            break;
                        }

                        case 4: {
                            System.out.print(
                                    "Enter reservation ID to update: ");
                            int id = Integer.parseInt(
                                    scanner.nextLine());

                            System.out.print("Enter new guest name: ");
                            String name = scanner.nextLine().trim();

                            System.out.print("Enter new room number: ");
                            int room = Integer.parseInt(
                                    scanner.nextLine());

                            System.out.print("Enter new contact number: ");
                            String contact = scanner.nextLine().trim();

                            if (name.isEmpty() || contact.isEmpty()
                                    || room <= 0) {
                                System.out.println(
                                        "Enter valid reservation details.");
                                break;
                            }

                            if (dao.updateReservation(
                                    id, name, room, contact)) {
                                System.out.println(
                                        "Reservation updated successfully!");
                            } else {
                                System.out.println(
                                        "Reservation not found.");
                            }
                            break;
                        }

                        case 5: {
                            System.out.print(
                                    "Enter reservation ID to delete: ");
                            int id = Integer.parseInt(
                                    scanner.nextLine());

                            if (dao.deleteReservation(id)) {
                                System.out.println(
                                        "Reservation deleted successfully!");
                            } else {
                                System.out.println(
                                        "Reservation not found.");
                            }
                            break;
                        }

                        case 0:
                            System.out.println(
                                    "Thank you for using the Hotel Reservation System!");
                            return;

                        default:
                            System.out.println(
                                    "Invalid choice. Try again.");
                    }

                } catch (SQLException e) {
                    System.out.println(
                            "Database error: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println(
                            "Please enter a valid number.");
                }
            }
        }
    }
}
