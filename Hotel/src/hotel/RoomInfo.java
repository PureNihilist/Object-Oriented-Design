package hotel;

/**
 *
 * @author Mateusz Galas
 * nazwa pokoju i ilość ludzi w nim zakwaterowanych
 */
public interface RoomInfo {
    public int getCapacity();
    public String getName();
    public int getFill();
    public void setFIll(int numberOfPeople);
}
