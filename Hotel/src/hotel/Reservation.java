package hotel;

/**
 *
 * @author Hubert Banaś and Mateusz Galas
 */
public interface Reservation {
    public long getId();
    public Client getClient();
    String isConfirmed();
}
