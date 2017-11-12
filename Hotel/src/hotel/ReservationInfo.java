package hotel;

import java.util.List;

/**
 *
 * @author Mateusz Galas
 */
public interface ReservationInfo {
    Period getPeriod();
    List<RoomInfo> getRoomsInfo(); 
}
