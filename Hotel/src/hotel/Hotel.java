package hotel;

import java.time.Period;
import java.util.List;

/**
 *
 * @author Mateusz Galas
 */
interface Hotel {
    void loadRooms(Reader reader);
    void saveRooms(Writer writer);
    void saveReservations(Writer writer);
    void addRoom(String name, int nOfBeds, int Quality);
    void deleteRoom(String name);
    //rooms jest listą liczb określających ile osób chcemy zakwaterować w pokoju
    //np.: { 1, 2} oznacza, że potrzebujemy pokoju dla jednej osoby i drugiego pokoju dla dwu osób. 
    List<Room> findFreeRooms(PeriodControl periodcontrol, List<Room> rooms);
    boolean makeReservation(ReservationInstance request); 
}