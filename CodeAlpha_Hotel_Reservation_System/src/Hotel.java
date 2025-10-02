
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hotel {
    public List<Room> getRooms() {
        return rooms;
    }

    private final List<Room> rooms;
    private final List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public  Hotel(){
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();

        rooms.add(new Room(101, RoomType.STANDARD, 2500));
        rooms.add(new Room(102, RoomType.STANDARD, 2500));
        rooms.add(new Room(103, RoomType.STANDARD, 2500));
        rooms.add(new Room(104, RoomType.STANDARD, 2500));
        rooms.add(new Room(105, RoomType.STANDARD, 2500));
        rooms.add(new Room(106, RoomType.STANDARD, 2500));
        rooms.add(new Room(107, RoomType.STANDARD, 2500));
        rooms.add(new Room(108, RoomType.STANDARD, 2500));
        rooms.add(new Room(109, RoomType.STANDARD, 2500));
        rooms.add(new Room(110, RoomType.STANDARD, 2500));
        rooms.add(new Room(111, RoomType.STANDARD, 2500));
        rooms.add(new Room(112, RoomType.STANDARD, 2500));
        rooms.add(new Room(113, RoomType.STANDARD, 2500));
        rooms.add(new Room(114, RoomType.STANDARD, 2500));
        rooms.add(new Room(115, RoomType.STANDARD, 2500));
        rooms.add(new Room(116, RoomType.STANDARD, 2500));
        rooms.add(new Room(117, RoomType.STANDARD, 2500));
        rooms.add(new Room(118, RoomType.STANDARD, 2500));
        rooms.add(new Room(119, RoomType.STANDARD, 2500));
        rooms.add(new Room(120, RoomType.STANDARD, 2500));
        rooms.add(new Room(121, RoomType.DELUXE, 5000));
        rooms.add(new Room(122, RoomType.DELUXE, 5000));
        rooms.add(new Room(123, RoomType.DELUXE, 5000));
        rooms.add(new Room(124, RoomType.DELUXE, 5000));
        rooms.add(new Room(125, RoomType.DELUXE, 5000));
        rooms.add(new Room(126, RoomType.DELUXE, 5000));
        rooms.add(new Room(127, RoomType.DELUXE, 5000));
        rooms.add(new Room(128, RoomType.DELUXE, 5000));
        rooms.add(new Room(129, RoomType.DELUXE, 5000));
        rooms.add(new Room(130, RoomType.DELUXE, 5000));
        rooms.add(new Room(131, RoomType.DELUXE, 5000));
        rooms.add(new Room(132, RoomType.DELUXE, 5000));
        rooms.add(new Room(133, RoomType.DELUXE, 5000));
        rooms.add(new Room(134, RoomType.DELUXE, 5000));
        rooms.add(new Room(135, RoomType.DELUXE, 5000));
        rooms.add(new Room(136, RoomType.DELUXE, 5000));
        rooms.add(new Room(137, RoomType.DELUXE, 5000));
        rooms.add(new Room(138, RoomType.DELUXE, 5000));
        rooms.add(new Room(139, RoomType.DELUXE, 5000));
        rooms.add(new Room(140, RoomType.DELUXE, 5000));
        rooms.add(new Room(141, RoomType.SUITE, 9000));
        rooms.add(new Room(142, RoomType.SUITE, 9000));
        rooms.add(new Room(143, RoomType.SUITE, 9000));
        rooms.add(new Room(144, RoomType.SUITE, 9000));
        rooms.add(new Room(145, RoomType.SUITE, 9000));
        rooms.add(new Room(146, RoomType.SUITE, 9000));
        rooms.add(new Room(147, RoomType.SUITE, 9000));
        rooms.add(new Room(148, RoomType.SUITE, 9000));
        rooms.add(new Room(149, RoomType.SUITE, 9000));
        rooms.add(new Room(150, RoomType.SUITE, 9000));
        rooms.add(new Room(151, RoomType.SUITE, 9000));
        rooms.add(new Room(152, RoomType.SUITE, 9000));
        rooms.add(new Room(153, RoomType.SUITE, 9000));
        rooms.add(new Room(154, RoomType.SUITE, 9000));
        rooms.add(new Room(155, RoomType.SUITE, 9000));
        rooms.add(new Room(156, RoomType.SUITE, 9000));
        rooms.add(new Room(157, RoomType.SUITE, 9000));
        rooms.add(new Room(158, RoomType.SUITE, 9000));
        rooms.add(new Room(159, RoomType.SUITE, 9000));
        rooms.add(new Room(160, RoomType.SUITE, 9000));
    }
    private boolean isRoomAvailable(Room room, LocalDate from, LocalDate to){
        for (Reservation reservation:reservations){
            if ( reservation.getRoom().equals(room)){
                if (!(to.isBefore(reservation.getCheckin())|| from.isAfter(reservation.getCheckout()))){
                    return false;
                }
            }
        }
        return true;
    }

    public List<Room> getAvailableRooms(RoomType type, LocalDate from, LocalDate to) {
        ArrayList<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getType() == type && isRoomAvailable(room, from, to)) {
                available.add(room);
            }
        }
        return available;
    }
    public String makeReservation( String guestName, RoomType type, LocalDate from, LocalDate to){
        List<Room> listOfAvailableRooms = getAvailableRooms(type, from, to);
        if (listOfAvailableRooms.isEmpty()){
            System.out.println("The room is not available.");
        }
            Room selectingTheRoom = listOfAvailableRooms.get(0);
        Reservation reservation = new Reservation(guestName, selectingTheRoom, from, to);
        rooms.remove(selectingTheRoom);
        reservations.add(reservation);

        return reservation.getGuestName()+" have successfully booked the room no "+selectingTheRoom.getRoomNumber()+" of Type "+selectingTheRoom.getType()+" on the price of "+selectingTheRoom.getPrice();
    }
    public String cancelReservation(String guestName, int roomNumber) {
        Iterator<Reservation> iterator = reservations.iterator();
        while (iterator.hasNext()) {
            Reservation reservation = iterator.next();
            if (reservation.getGuestName().equals(guestName) &&
                    reservation.getRoom().getRoomNumber() == roomNumber) {

                iterator.remove();
                FileManager.saveReservation(reservations);

                return "The room number " + roomNumber + " reserved by " + guestName + " has been successfully removed.";
            }
        }
        return "The room number " + roomNumber + " reserved by " + guestName + " was not found.";
    }






}
