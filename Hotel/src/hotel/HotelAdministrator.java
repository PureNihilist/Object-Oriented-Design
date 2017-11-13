/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.util.List;

/**
 *
 * @author nihilist
 */
public class HotelAdministrator implements Hotel{
    
    private List<Room> rooms; //list of rooms

    @Override
    public void loadRooms(Reader reader) {
        reader = Reader.getInstance(); //get singleton instance of reader
        rooms = reader.readRoomsCSV("Rooms.csv");
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

    @Override
    public List<ReservationInfo> findFreeRooms(Period period, List<Integer> rooms) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean makeReservation(Client client, ReservationInfo request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
