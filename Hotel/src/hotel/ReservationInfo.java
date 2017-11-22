package hotel;

import java.util.Map;

/**
 *
 * @author Mateusz Galas
 */
public interface ReservationInfo {
    PeriodControl getPeriodControl();
    Map<Integer,Integer> getRoomsInfo(); 
}
