/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 *
 * @author Mateusz Galas
 */
public class ReservationInstance implements Reservation {
    private final long ID;
    private final Client client;
    private final ReservationInfo reservationInfo;

    public ReservationInstance(long id, Client client, ReservationInfo reservationInfo) {
        this.ID = id;
        this.client = client;
        this.reservationInfo = reservationInfo;
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
