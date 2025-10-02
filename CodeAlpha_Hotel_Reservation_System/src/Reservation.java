import java.time.LocalDate;

public class Reservation {

    private String guestName ;
    private Room room;
    private LocalDate checkin;
    private LocalDate checkout;
    private double pricePaid;

    public Reservation(String guestName,Room room,LocalDate checkin, LocalDate checkout) {
        this.guestName = guestName;
        this.checkout = checkout;
        this.room = room;
        this.checkin = checkin;
        this.pricePaid = room.getPrice();
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public double getPricePaid() {
        return pricePaid;
    }

    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public void setCheckin(LocalDate checkin) {
        this.checkin = checkin;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Reservation of " +
                "Guest: '" + guestName + '\'' +
                ",having room number=" + room.getRoomNumber() +
                ", from date : " + checkin +
                ", to date : =" + checkout +
                ", and the price paid is: " + pricePaid ;
    }

}
