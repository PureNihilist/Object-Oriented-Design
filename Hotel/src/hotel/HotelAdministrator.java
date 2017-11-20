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
    private Writer writer;

    @Override
    public void loadRooms(Reader reader) {
        reader = Reader.getInstance(); //get singleton instance of reader
        this.rooms = reader.readRoomsCSV("Rooms.csv");
    }
    
    public List<Room> getRooms() {
        return rooms;
    }
    
    public List<ReservationInstance> getReservations() {
        return reservations;
    }

    @Override
    public void saveRooms(Writer writer) {
        writer = Writer.getInstance();
        writer.writeRoomsCSV("Rooms.csv", this.rooms);
    }

    @Override
    public void addRoom(String name, int nOfBeds, int Quality) {
        for(Room room : rooms){
            if(room.getName().equals(name)){
                System.out.println("There's already a room with this name");
                return;
            }
            else{
                continue;
            }
        }
        Room room = new Room(name,nOfBeds,Quality);
        rooms.add(room);
    }

    @Override
    public void deleteRoom(String name) {
        for(Room room : rooms){
            if(room.getName().equals(name)){
                rooms.remove(room);
                System.out.println("Room has been removed.");
                return;
            }
            else{
                continue;
            }
        }
        System.out.println("There's no room with this name");
    }

    public void loadReservations(Reader reader) {
        reader = Reader.getInstance();
        this.reservations = reader.readReservationCSV("Reservations.csv");
    }

    private void saveReservations(Writer writer) {
        writer = Writer.getInstance();
        writer.writeReservationsCSV("Reservations.csv", this.reservations);
    }

    @Override
    public List<Room> findFreeRooms(Period period, List<Room> rooms) {
        
        List<Room> free_rooms = new ArrayList<>();
        for(Room r : rooms) {
            //PRZEGLADNAC REZERWACJE!
            //SPRAWDZIC CZY POKOJE SA WOLNE I CZY ISTNIEJA
            //DODAC DO LISTY TE KTORE SA WOLNE
        }
        return free_rooms;
    }
    
    @Override
    public boolean makeReservation(ReservationInstance request) {
        Period period = request.getPeriodControl().getPeriod(); //request ma okres na ktory ktos chce zamowic pokoje
        List<Room> rooms = request.getRoomsInfo(); //request ma w sobie liste pokoi
        List<Room> free_rooms = findFreeRooms(period,rooms); //check if this request is possible (for this period and this room list)
        //TUTAJ PRZEGLADANIE LISTY FREE ROOMS CZY COS TAM JEST
        if(!free_rooms.isEmpty()) {
            //mamy pokoje ktore sa wolne
            //podac ILE POKOI JEST WOLNYCH 
            //spytac czy taka rezerwacja wystarcza
            return true;
        } else {
            return false;
        }
    }
    
}
