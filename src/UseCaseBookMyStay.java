package BookMyStay;

import java.util.HashMap;
abstract class Room {
    int beds;
    int size;
    double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqft");
        System.out.println("Price per night: " + price);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 250, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 400, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 750, 5000.0);
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.get(roomType);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }
}

public class UseCaseBookMyStay {

    public static void main(String[] args) {

        System.out.println("Hotel Room Inventory Status\n");

        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        System.out.println("Single Room:");
        single.displayDetails();
        System.out.println("Available Rooms: " + inventory.getAvailability("Single") + "\n");

        System.out.println("Double Room:");
        dbl.displayDetails();
        System.out.println("Available Rooms: " + inventory.getAvailability("Double") + "\n");

        System.out.println("Suite Room:");
        suite.displayDetails();
        System.out.println("Available Rooms: " + inventory.getAvailability("Suite"));
    }
}