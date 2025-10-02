import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class FileManager {

    public static void saveReservation(List<Reservation> reservations) {
        try (BufferedWriter savingData = new BufferedWriter(new FileWriter("C:\\Users\\war COMPUTER\\IdeaProjects\\Hotel_Reservation_System\\src\\Database.txt"))) {
            for (Reservation reservation : reservations) {
                savingData.write(reservation.getGuestName() + "," +
                        reservation.getRoom().getRoomNumber() + "," +
                        reservation.getCheckin() + "," +
                        reservation.getCheckout() + "," +
                        reservation.getRoom().getType() + "," +
                        reservation.getPricePaid());
                savingData.newLine();

            }
        } catch (Exception exception) {
            System.out.println("IO exception found. Please enter valid data.");
        }

    }

    public static void loadReservation(List<Reservation> reservations ,List<Room> rooms) {
        try (BufferedReader loadingData = new BufferedReader(new FileReader("Database.txt"))) {
            String line;
            while ((line = loadingData.readLine()) != null) {
                String[] data = line.split(",");
                String guestName = data[0];
                int roomNumber = Integer.parseInt(data[1]);
                LocalDate checkinDate = LocalDate.parse(data[2]);
                LocalDate checkoutDate = LocalDate.parse(data[3]);
                RoomType roomType = RoomType.valueOf(data[4]);
                double pricePaid = Double.parseDouble(data[5]);

                Room existingRoom = rooms.stream()
                        .filter(r -> r.getRoomNumber() == roomNumber)
                        .findFirst()
                        .orElse(new Room(roomNumber, roomType, pricePaid));

                reservations.add(new Reservation(guestName, existingRoom, checkinDate, checkoutDate));
            }
        } catch (IOException ioException) {
            System.out.println("File was not found. Please check the location of file.");
        }
    }
}