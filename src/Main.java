import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);
        String guestName;
        String guestNameForCancellation;
        int roomNumber;
        int roomType;
        String checkin;
        String checkout;
        LocalDate checkInDate = null;
        LocalDate checkOutate = null;
        RoomType roomTypeFromEnum = null;

        FileManager.loadReservation(hotel.getReservations(), hotel.getRooms());

        boolean looping = true;

        while (looping){
            System.out.println("~~~~~~~~WELCOME TO PEARL HOTEL~~~~~~~");
            System.out.println("Please enter the option number from the below menu: ");
            System.out.println("1.Reserving a new room \t\t 2.Cancelling the reservation \t\t 3.View Reservations( Admin only) \t\t 4.Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter your name: ");
                    guestName = scanner.next();
                    scanner.nextLine();

                    System.out.println("Enter your check in date in format(YYYY-MM-DD): ");
                    checkin = scanner.next();
                    try {
                        checkInDate = LocalDate.parse(checkin);
                    } catch (DateTimeParseException ex) {
                        System.out.println("Invalid Date enter. Please enter the date in this format (YYYY-MM-DD)");
                    }

                    System.out.println("Enter your check out date in format(YYYY-MM-DD): ");
                    checkout = scanner.next();
                    try {
                        checkOutate = LocalDate.parse(checkout);
                    } catch (DateTimeParseException ex) {
                        System.out.println("Invalid Date entered. Please enter the date in this format (YYYY-MM-DD)");
                    }

                    System.out.println("Enter the room Type (Enter only the number associated with room type): \n1.Standard \t2.Deluxe \t3.Suite");
                    roomType = scanner.nextInt();
                    try {
                        roomTypeFromEnum = RoomType.optionNumberOfRoomType(roomType);
                    } catch (Exception ex) {
                        System.out.println("Invalid room type entered. Please enter complete room type.");
                    }

                    System.out.println(hotel.makeReservation(guestName, roomTypeFromEnum, checkInDate, checkOutate));

                    break;


                case 2:

                    System.out.println("Enter your name: ");
                    guestNameForCancellation = scanner.next();
                    scanner.nextLine();

                    System.out.println("Enter your Room Number: ");
                    roomNumber = scanner.nextInt();
                    System.out.println(hotel.cancelReservation(guestNameForCancellation, roomNumber));
                    break;
                case 3:
                    System.out.println("Enter admin password: ");
                    String password = scanner.next();
                    if(password.equals("admin123")) {
                        System.out.println("~~~~~~~CURRENT RESERVATIONS~~~~~~~~");
                        if (hotel.getReservations().isEmpty()) {
                            System.out.println("No active reservations.");
                        } else {
                            for (Reservation reservation : hotel.getReservations()) {
                                System.out.println(reservation);
                            }
                        }
                    } else {
                        System.out.println("Access denied. Wrong password!");
                    }
                    break;
                case 4:
                    FileManager.saveReservation(hotel.getReservations());
                    System.out.println("Thank you for visiting Pearl Hotel.");
                    looping = false;

            }
        }
        scanner.close();
    }

}
