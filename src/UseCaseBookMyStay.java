package BookMyStay;

import java.util.*;
class Reservation {
    String guestName;
    String roomType;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}
class InventoryService {
    private HashMap<String, Integer> inventory;
    public InventoryService() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }
    public int getAvailability(String type) {
        return inventory.get(type);
    }
    public void decrement(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}
class BookingService {
    private InventoryService inventory;
    private HashMap<String, Set<String>> allocatedRooms;
    private HashMap<String, Integer> counters;
    public BookingService(InventoryService inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
        counters = new HashMap<>();
        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
        counters.put("Single", 1);
        counters.put("Double", 1);
        counters.put("Suite", 1);
    }
    public void processBookings(Queue<Reservation> queue) {
        while (!queue.isEmpty()) {
            Reservation r = queue.poll();
            if (inventory.getAvailability(r.roomType) > 0) {
                int id = counters.get(r.roomType);
                String roomId = r.roomType + "-" + id;
                Set<String> roomSet = allocatedRooms.get(r.roomType);
                if (!roomSet.contains(roomId)) {
                    roomSet.add(roomId);
                    counters.put(r.roomType, id + 1);
                    inventory.decrement(r.roomType);
                    System.out.println(
                            "Booking confirmed for Guest: " +
                                    r.guestName +
                                    ", Room ID: " +
                                    roomId
                    );
                }
            }
        }
    }
}
public class UseCaseBookMyStay {
    public static void main(String[] args) {
        System.out.println("Room Allocation Processing");
        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Abhi", "Single"));
        queue.add(new Reservation("Subha", "Single"));
        queue.add(new Reservation("Vanmathi", "Suite"));
        InventoryService inventory = new InventoryService();
        BookingService bookingService = new BookingService(inventory);
        bookingService.processBookings(queue);
    }
}