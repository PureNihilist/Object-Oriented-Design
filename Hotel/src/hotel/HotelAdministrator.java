/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateusz Galas
 */
public class HotelAdministrator implements Hotel{
    
    private List<Room> rooms; //list of rooms
    private List<ReservationInstance> reservations;//list of reservations
    private Reader reader;

    @Override
    public void loadRooms(Reader reader) {
        reader = Reader.getInstance(); //get singleton instance of reader
        this.rooms = reader.readRoomsCSV("Rooms.csv");
    }

    @Override
    public void saveRooms(Writer writer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addRoom(String name, int nOfBeds) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteRoom(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void loadReservations(Reader reader) {
        reader = Reader.getInstance();
        this.reservations = reader.readReservationCSV("Reservations.csv");
    }

    @Override
    public List<ReservationInfo> findFreeRooms(Period period, List<Integer> rooms) {
        loadReservations(reader);
        List<ReservationInfo> free_rooms = new ArrayList<>();
        for(ReservationInstance r : reservations) {
            //check if our loaded reservations have free rooms and create free room list
        }
        return free_rooms;
    }
    
    @Override
    public boolean makeReservation(Client client, ReservationInfo request) {
        Period period = request.getPeriod(); //request ma okres na ktory ktos chce zamowic pokoje
        List<RoomInfo> rooms = request.getRoomsInfo(); //request ma w sobie liste pokoi
        List<Integer> request_list = new ArrayList();
        for(RoomInfo info : rooms) { //przegladam informacje z requesta
            int roomCapacity = info.getCapacity();
            request_list.add(roomCapacity);
        }
        List<ReservationInfo> free_rooms = findFreeRooms(period,request_list); //check if this request is possible (for this period and this room list)
        if(!free_rooms.isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    
}
