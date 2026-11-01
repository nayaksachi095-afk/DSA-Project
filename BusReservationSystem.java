import java.util.*;

class Bus {
    int busNumber;
    String route;
    int totalSeats;
    int bookedSeats;

    Bus(int busNumber, String route, int totalSeats) {
        this.busNumber = busNumber;
        this.route = route;
        this.totalSeats = totalSeats;
        bookedSeats = 0;
    }

    int availableSeats() {
        return totalSeats - bookedSeats;
    }

    void display() {
        System.out.println(busNumber + "  " + route + "  "
                + totalSeats + "  " + availableSeats());
    }
}

class Booking {
    int bookingId;
    String name;
    int busNumber;

    Booking(int bookingId, String name, int busNumber) {
        this.bookingId = bookingId;
        this.name = name;
        this.busNumber = busNumber;
    }
}

public class BusReservationSystem {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Bus> buses = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    static Queue<String> waitingList = new LinkedList<>();

    static int bookingId = 1001;

    // Add buses
    static void addBuses() {
        buses.add(new Bus(101, "Bhilai-Raipur", 3));
        buses.add(new Bus(102, "Bhilai-Durg", 2));
        buses.add(new Bus(103, "Raipur-Nagpur", 4));
        buses.add(new Bus(104, "Durg-Bilaspur", 3));
    }

    // Display buses
    static void viewBuses() {
        System.out.println("\nBus No.  Route  Total Seats  Available Seats");

        for (Bus b : buses) {
            b.display();
        }
    }

    // Find bus using Linear Search
    static Bus findBus(int number) {
        for (Bus b : buses) {
            if (b.busNumber == number) {
                return b;
            }
        }
        return null;
    }

    // Book ticket
    static void bookTicket() {
        sc.nextLine();

        System.out.print("Enter passenger name: ");
        String name = sc.nextLine();

        viewBuses();

        System.out.print("Enter bus number: ");
        int number = sc.nextInt();

        Bus b = findBus(number);

        if (b == null) {
            System.out.println("Bus not found!");
        }
        else if (b.availableSeats() > 0) {
            bookings.add(new Booking(bookingId, name, number));
            b.bookedSeats++;

            System.out.println("Ticket booked successfully!");
            System.out.println("Your Booking ID: " + bookingId);

            bookingId++;
        }
        else {
            waitingList.add(name + " - Bus " + number);
            System.out.println("Bus is full!");
            System.out.println("Added to waiting list.");
        }
    }

    // Display bookings
    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        System.out.println("\nBooking ID  Passenger  Bus Number");

        for (Booking b : bookings) {
            System.out.println(b.bookingId + "  "
                    + b.name + "  " + b.busNumber);
        }
    }

    // Search booking
    static void searchBooking() {
        System.out.print("Enter booking ID: ");
        int id = sc.nextInt();

        for (Booking b : bookings) {
            if (b.bookingId == id) {
                System.out.println("Booking Found!");
                System.out.println("Name: " + b.name);
                System.out.println("Bus Number: " + b.busNumber);
                return;
            }
        }

        System.out.println("Booking not found!");
    }

    // Cancel booking
    static void cancelTicket() {
        System.out.print("Enter booking ID: ");
        int id = sc.nextInt();

        for (int i = 0; i < bookings.size(); i++) {
            Booking b = bookings.get(i);

            if (b.bookingId == id) {
                Bus bus = findBus(b.busNumber);

                if (bus != null) {
                    bus.bookedSeats--;
                }

                bookings.remove(i);

                System.out.println("Ticket cancelled successfully!");
                return;
            }
        }

        System.out.println("Booking not found!");
    }

    // Display waiting list
    static void viewWaitingList() {
        if (waitingList.isEmpty()) {
            System.out.println("Waiting list is empty.");
            return;
        }

        System.out.println("\nWaiting List:");

        for (String name : waitingList) {
            System.out.println(name);
        }
    }

    // Bubble Sort
    static void sortBuses() {
        for (int i = 0; i < buses.size() - 1; i++) {
            for (int j = 0; j < buses.size() - i - 1; j++) {

                if (buses.get(j).availableSeats()
                        > buses.get(j + 1).availableSeats()) {

                    Bus temp = buses.get(j);
                    buses.set(j, buses.get(j + 1));
                    buses.set(j + 1, temp);
                }
            }
        }

        System.out.println("Buses sorted successfully!");
        viewBuses();
    }

    // Main method
    public static void main(String[] args) {

        addBuses();

        int choice;

        do {
            System.out.println("\n===== BUS RESERVATION SYSTEM =====");
            System.out.println("1. View Buses");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Bookings");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. Search Booking");
            System.out.println("6. Sort Buses");
            System.out.println("7. View Waiting List");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    viewBuses();
                    break;

                case 2:
                    bookTicket();
                    break;

                case 3:
                    viewBookings();
                    break;

                case 4:
                    cancelTicket();
                    break;

                case 5:
                    searchBooking();
                    break;

                case 6:
                    sortBuses();
                    break;

                case 7:
                    viewWaitingList();
                    break;

                case 8:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 8);

        sc.close();
    }
}

