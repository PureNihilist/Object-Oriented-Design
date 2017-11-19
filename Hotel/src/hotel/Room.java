package hotel;

/**
 *
 * @author Mateusz Galas
 */
public class Room implements RoomInfo {
    private final String name;
    private final int capacity;
    private final int quality;
    private final double price;
    public Room(String roomName, int roomCapacity, int roomQuality){
        this.name = roomName;
        this.capacity = roomCapacity;
        this.quality = roomQuality;
        this.price = this.quality * this.capacity;
    }
    @Override
    public int getCapacity() {
        return capacity;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public int getQuality() {
        return quality; 
    }
    @Override
    public double getPrice() {
        return price; 
    }
}
