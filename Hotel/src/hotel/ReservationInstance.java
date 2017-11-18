/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.util.List;

/**
 *
 * @author Mateusz Galas
 */
public class ReservationInstance implements Reservation {

    private final int ID;
    private final Client client;
    private final ReservationInfo reservationInfo;

    //tutaj proponuje dwa sposoby tworzenia obiektu - mozesz wypowiedziec sie jbc na fb

    public ReservationInstance(int id, Client client, ReservationInfo reservationInfo) {
        this.ID = id;
        this.client = client;
        this.reservationInfo = reservationInfo;
    }

    public ReservationInstance(int id, Client client, Period period, List<Room> rooms) {
        this.ID = id;
        this.client = client;
        this.reservationInfo = new ReservationInfo(period, rooms);
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
    public ReservationInfo getInfo() {
        return this.reservationInfo;
    }
    
}
