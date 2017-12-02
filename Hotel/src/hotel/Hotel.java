package hotel;

import java.util.List;

/**
 * Interface Hotel, declaring methods to implement in other class
 * @author Hubert Banaś and Mateusz Galas
 */
public interface Hotel {
    void loadRooms(Reader reader);
    void saveRooms(Writer writer);
    void saveReservations(Writer writer);
    void saveSeasons(Writer writer);
    void deleteReservation(long reservationId);
    void addRoom(String name, int nOfBeds, int Quality);
    void deleteRoom(String name);
    List<Room> findFreeRooms(PeriodControl periodcontrol, List<Room> rooms);
    boolean makeReservation(ReservationInstance request);
    void findReservation(long Pesel);
}