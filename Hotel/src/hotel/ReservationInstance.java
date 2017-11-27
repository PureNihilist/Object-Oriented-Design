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
    boolean isConfirmed;

    public ReservationInstance(long id, Client client, PeriodControl periodControl, List<Room> info, boolean confirmed) {
        this.ID = id;
        this.client = client;
        this.periodControl = periodControl;
        this.roomInfo = info;
        this.isConfirmed = confirmed;
    }

    @Override
    public long getId() {
        return this.ID;
    }

    @Override
    public Client getClient() {
        return this.client;
    }
    
    public void setConfirmed() {
        this.isConfirmed = true;
    }
    
    @Override
    public String isConfirmed() {
        if(this.isConfirmed){
            return "TAK";
        } else {
            return "NIE";
        }
    }
    
    public double countPrice(){
        if(roomInfo != null) {
            double fullPrice = 0;
            for(Room r : roomInfo) {
                fullPrice += r.getPrice();
            }
            return fullPrice;
        } else {
            System.err.println("Nie da się wyliczyć ceny rezerwacji, ponieważ lista pokoi tej rezerwacji nie jest zainicjowana.");
            return 0;
        }
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
