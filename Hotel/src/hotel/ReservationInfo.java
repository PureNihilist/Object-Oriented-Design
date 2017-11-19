package hotel;

import java.util.List;

/**
 *
 * @author Mateusz Galas
 */
public class ReservationInfo {
    private PeriodInterface period;
    private List<Room> Rooms;

    public ReservationInfo(PeriodInterface period, List<Room> rooms){
        this.period = period;
        this.Rooms = rooms;
    }

    public void setPeriod(PeriodInterface period) {
        this.period = period;
    }

    public void setRooms(List<Room> rooms) {
        Rooms = rooms;
    }

    public List<Room> getRooms() {
        return Rooms;
    }

    public PeriodInterface getPeriod() {
        return period;
    }
}
