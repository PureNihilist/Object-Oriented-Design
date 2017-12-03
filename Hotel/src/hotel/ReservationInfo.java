package hotel;

import java.util.List;

/**
 * Interface ReservationInfo, declaring methods to implement in other class
 * @author Hubert Banaś and Mateusz Galas
 */
public interface ReservationInfo {
    PeriodControl getPeriodControl();
    List<Room> getRoomsInfo(); 
}
