package hotel;

import java.time.Period;
import java.util.List;

/**
 *
 * @author Mateusz Galas
 */
public class ReservationInstance implements Reservation,ReservationInfo{
    private final long ID;
    private final Client client;
    private final PeriodControl periodControl;
    private final List <Room> roomInfo;

    public ReservationInstance(long id, Client client, PeriodControl periodControl, List<Room> info) {
        this.ID = id;
        this.client = client;
        this.periodControl = periodControl;
        this.roomInfo = info;
    }

    @Override
    public long getId() {
        return this.ID;
    }

    @Override
    public Client getClient() {
        return this.client;
    }
    
    @Override
    public PeriodControl getPeriodControl() {
        return periodControl;
    }

    @Override
    public List<Room> getRoomsInfo() {
        return this.roomInfo;
    }
    
}
