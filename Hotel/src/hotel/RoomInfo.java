package hotel;

/**
 *
 * @author Mateusz Galas
 * nazwa pokoju, pojemność, jakość oraz cena
 */
public interface RoomInfo {
    public int getCapacity();

    public String getName();

    public int getQuality();

    public double getPrice();
}
