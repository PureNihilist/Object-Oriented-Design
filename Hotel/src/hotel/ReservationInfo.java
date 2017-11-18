package hotel;

import java.util.List;

/**
 *
 * @author Mateusz Galas
 */
public class ReservationInfo {
    private Period period;
    private List<Room> Rooms;

    public ReservationInfo(Period period, List<Room> rooms){
        this.period = period;
        this.Rooms = rooms;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public void setRooms(List<Room> rooms) {
        Rooms = rooms;
    }

    public List<Room> getRooms() {
        return Rooms;
    }

    public Period getPeriod() {
        return period;
    }
}
