package hotel;

/**
 * Interface Reservation, declaring methods to implement in other class
 * @author Hubert Banaś and Mateusz Galas
 */
public interface Reservation {
    public long getId();
    public Client getClient();
    String isConfirmed();
}
