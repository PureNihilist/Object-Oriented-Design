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
    void deleteReservation(long reservationId);
    void addRoom(String name, int nOfBeds, int Quality);
    void deleteRoom(String name);
  //  void loadClients(Reader reader);
    List<Room> findFreeRooms(PeriodControl periodcontrol, List<Room> rooms);
    boolean makeReservation(ReservationInstance request);
    void findReservation(long Pesel);
}