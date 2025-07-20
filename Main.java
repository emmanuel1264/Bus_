package com.bus;

import com.bus.dao.BusDao;
import com.bus.dao.UserDao;
import com.bus.models.Bus;
import com.bus.models.User;
import com.bus.dao.ScheduleDao;
import com.bus.models.Schedule;
import com.bus.dao.BookingDao;
import com.bus.models.Booking;


import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserDao userDao = new UserDao();
        BusDao busDao = new BusDao();
        ScheduleDao scheduleDao = new ScheduleDao();
        BookingDao bookingDao = new BookingDao();
        User currentUser = null;



        boolean loggedIn = false;

        // ===== AUTHENTICATION MENU =====
        while (!loggedIn) {
            System.out.println("===== USER AUTHENTICATION =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("4. Exit");
            System.out.println("5. Add Schedule");
            System.out.println("6. View All Schedules");
            System.out.println("7. Make Booking");
            System.out.println("8. View My Bookings");

            System.out.print("Choose option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String loginUser = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPass = scanner.nextLine();
                    User user = userDao.loginUser(loginUser, loginPass);
                    if (user != null) {
                        System.out.println("Login successful!");
                        currentUser = user;
                        loggedIn = true;
                    } else {
                        System.out.println("Invalid credentials. Try again.");
                    }
                    break;

                case 2:
                    System.out.print("Choose username: ");
                    String regUser = scanner.nextLine();
                    System.out.print("Choose password: ");
                    String regPass = scanner.nextLine();
                    userDao.registerUser(new User(regUser, regPass));
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }

        // ===== BUS MANAGEMENT MENU =====
        while (true) {
            System.out.println("\n===== BUS MANAGEMENT MENU =====");
            System.out.println("1. Add Bus");
            System.out.println("2. View All Buses");
            System.out.println("3. Delete Bus");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Bus Number: ");
                    String busNumber = scanner.nextLine();
                    System.out.print("Enter Capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine();
                    Bus newBus = new Bus(busNumber, capacity);
                    busDao.addBus(newBus);
                    break;

                case 2:
                    List<Bus> buses = busDao.getAllBuses();
                    System.out.println("\nAll Buses:");
                    for (Bus bus : buses) {
                        System.out.println("ID: " + bus.getId() + ", Number: " + bus.getBusNumber() + ", Capacity: " + bus.getCapacity());
                    }
                    break;

                case 3:
                    System.out.print("Enter Bus ID to delete: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    busDao.deleteBus(deleteId);
                    break;

                case 4:
                    System.out.println("Exiting... Goodbye!");
                    return;

                case 5:
                    System.out.print("Enter Bus ID: ");
                    int busId = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    // Check if bus exists
                    if (busDao.getCapacityByBusId(busId) == 0) {
                        System.out.println("❌ Bus ID not found. Please add the bus first.");
                        break;
                    }

                    System.out.print("Enter Origin: ");
                    String origin = scanner.nextLine();

                    System.out.print("Enter Destination: ");
                    String destination = scanner.nextLine();

                    System.out.print("Enter Departure Time (yyyy-MM-dd HH:mm): ");
                    String departureStr = scanner.nextLine();

                    try {
                        LocalDateTime departureTime = LocalDateTime.parse(departureStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        Schedule schedule = new Schedule(busId, origin, destination, departureTime);
                        scheduleDao.addSchedule(schedule);
                    } catch (Exception e) {
                        System.out.println("❌ Invalid date format. Please use yyyy-MM-dd HH:mm.");
                    }
                    break;


                case 6:
                    List<Schedule> schedules = scheduleDao.getAllSchedules();

                    if (schedules.isEmpty()) {
                        System.out.println("⚠️  No schedules available.");
                    } else {
                        System.out.println("\n🚌 Available Schedules:");
                        System.out.println("ID | Bus ID | From ➡️  To | Departure Time");
                        for (Schedule s : schedules) {
                            System.out.printf("%2d | %6d | %s ➡️ %s | %s\n",
                                    s.getId(),
                                    s.getBusId(),
                                    s.getOrigin(),
                                    s.getDestination(),
                                    s.getDepartureTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                            );
                        }
                    }
                    break;


                case 7:
                    System.out.print("Enter Schedule ID: ");
                    int scheduleId = scanner.nextInt();
                    System.out.print("Enter Seat Number: ");
                    int seatNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    Booking booking = new Booking(currentUser.getId(), scheduleId, seatNumber);
                    bookingDao.makeBooking(booking);
                    break;

                case 8:
                    List<Booking> myBookings = bookingDao.getBookingsByUser(currentUser.getId());
                    System.out.println("\nMy Bookings:");
                    for (Booking b : myBookings) {
                        System.out.println("Booking ID: " + b.getId() +
                                ", Schedule ID: " + b.getScheduleId() +
                                ", Seat Number: " + b.getSeatNumber());
                    }
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
