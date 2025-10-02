public enum RoomType {

    STANDARD, DELUXE, SUITE;

    public static RoomType optionNumberOfRoomType(int choice){
        return switch (choice) {
            case 1 -> STANDARD;
            case 2 -> DELUXE;
            case 3 -> SUITE;
            default -> throw new IllegalArgumentException();
        };
    }

}

