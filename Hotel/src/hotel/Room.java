package hotel;

/**
 *
 * @author Mateusz Galas
 */
public class Room implements RoomInfo {
    private final String name;
    private final int capacity;
    private int fill;
    Room(String roomName, int roomCapacity, int roomFill) {
        name = roomName;
        capacity = roomCapacity;
        fill = roomFill;
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
    public int getFill() {
        return fill;
    }
    @Override
    public void setFIll(int numberOfPeople) {
        int updatedFill = this.getFill() + numberOfPeople;
        if(updatedFill <= this.getCapacity()){
            this.fill = updatedFill; 
        }
    }
}
