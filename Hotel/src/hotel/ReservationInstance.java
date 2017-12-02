package hotel;

import java.time.Period;
import java.util.List;

/**
 * Class ReservationInstance, which represents reservation. Implements methods from interface Reservation and ReservationInfo.
 * @author Hubert Banaś and Mateusz Galas
 */
public class ReservationInstance implements Reservation,ReservationInfo{
    private final long ID;
    private final Client client;
    private final PeriodControl periodControl;
    private final List <Room> roomInfo;
    boolean isConfirmed;

    /**
     * Constructor of class ReservationInstance
     * @param id
     * @param client
     * @param periodControl
     * @param info
     * @param confirmed
     */
    public ReservationInstance(long id, Client client, PeriodControl periodControl, List<Room> info, boolean confirmed) {
        this.ID = id;
        this.client = client;
        this.periodControl = periodControl;
        this.roomInfo = info;
        this.isConfirmed = confirmed;
    }

    /**
     * getId method
     * @return reservation ID
     */
    @Override
    public long getId() {
        return this.ID;
    }

    /**
     * getClient method
     * @return reservation client
     */
    @Override
    public Client getClient() {
        return this.client;
    }
    
    /**
     * method to confirm reservation 
     */
    public void setConfirmed() {
        this.isConfirmed = true;
    }
    
    /**
     * checks if reservation is confirmed
     * @return string "tak" if reservation is confirmed, else returns string "nie"
     */
    @Override
    public String isConfirmed() {
        if(this.isConfirmed){
            return "TAK";
        } else {
            return "NIE";
        }
    }
    
    /**
     * method to count price depending of rooms listed in reservation
     * @return whole price
     */
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
    
    /**
     * getPeriodControl method
     * @return reservation periodcontrol
     */
    @Override
    public PeriodControl getPeriodControl() {
        return periodControl;
    }

    /**
     * getRoomsInfo method
     * @return list of rooms for this reservation
     */
    @Override
    public List<Room> getRoomsInfo() {
        return this.roomInfo;
    }
    
}
