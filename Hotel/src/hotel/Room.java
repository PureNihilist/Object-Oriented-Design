package hotel;

/**
 * Class ReservationInstance, which represents room. Implements methods from interface RoomInfo.
 * @author Hubert Banaś and Mateusz Galas
 */
public class Room implements RoomInfo {
    private String name = null;
    private final int capacity;
    private final int quality;
    private double price; //price nie może być final bo przecież ma się zmieniać jak jest zniżka
    
    /**
     * Constructor of class
     * @param roomCapacity
     * @param roomQuality
     */
    public Room(int roomCapacity, int roomQuality) {
       this.capacity = roomCapacity;
       this.quality = roomQuality; 
    }

    /**
     * Second Constructor of class
     * @param roomName
     * @param roomCapacity
     * @param roomQuality
     */
    public Room(String roomName, int roomCapacity, int roomQuality){
        this.name = roomName;
        this.capacity = roomCapacity;
        this.quality = roomQuality;
        this.price = this.quality * this.capacity;
    }
    
    /**
     * getCapacity method
     * @return room capacity
     */
    @Override
    public int getCapacity() {
        return capacity;
    }

    /**
     * getName method
     * @return room name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * getQuality method
     * @return room quality
     */
    @Override
    public int getQuality() {
        return quality; 
    }

    /**
     * getPrice method
     * @return rooms base price
     */
    @Override
    public double getPrice() {
        return price; 
    }
}
