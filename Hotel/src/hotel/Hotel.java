package hotel;

import java.util.List;

/**
 *
 * @author Mateusz Galas
 */
interface Hotel {
    void loadRooms(Reader reader);
    void saveRooms(Writer writer);
    void saveReservations(Writer writer);
    void deleteReservation();
    void addRoom(String name, int nOfBeds, int Quality);
    void deleteRoom(String name);
    void loadClients(Reader reader);
    void addClient(Client client);
    List<Room> findFreeRooms(PeriodControl periodcontrol, List<Room> rooms);
    boolean makeReservation(ReservationInstance request); 
}