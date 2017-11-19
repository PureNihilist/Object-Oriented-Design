/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private final Period period;
    private final List <RoomInfo> roomInfo;

    public ReservationInstance(long id, Client client, Period period, List<RoomInfo> info) {
        this.ID = id;
        this.client = client;
        this.period = period;
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
    public Period getPeriod() {
        return this.period;
    }

    @Override
    public List<RoomInfo> getRoomsInfo() {
        return this.roomInfo;
    }
    
}
