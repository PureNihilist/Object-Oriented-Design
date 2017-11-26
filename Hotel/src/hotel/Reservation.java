package hotel;

/**
 *
 * @author Mateusz Galas
 */
public interface Reservation {
    public long getId();
    public Client getClient();
    String isConfirmed();
}
