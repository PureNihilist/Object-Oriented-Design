package hotel;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author Mateusz Galas
 */
public class HotelAdministrator implements Hotel{
    private List<Room> rooms; //list of rooms
    private List<ReservationInstance> reservations;//list of reservations
    private List<Client> clients = null; // list of clients
    private Reader reader;
    private Writer writer;

    @Override
    public void loadRooms(Reader reader) {
        this.reader = Reader.getInstance(); //get singleton instance of reader
        this.rooms = this.reader.readRoomsCSV("Rooms.csv");
    }
    
    public List<Room> getRooms() {
        return rooms;
    }
    
    public List<ReservationInstance> getReservations() {
        return reservations;
    }
    
    public List<Client> getClients() throws Exception{
        if(clients != null) {
            return clients;
        } else {
            throw new Exception("Lista klientów nie została wczytana.");
        }
    }

    @Override
    public void saveRooms(Writer writer) {
        this.writer = Writer.getInstance();
        this.writer.writeRoomsCSV("Rooms.csv", this.rooms);
    }

    @Override
    public void addRoom(String name, int nOfBeds, int Quality) {
        for(Room room : rooms){
            if(room.getName().equals(name)){
                System.out.println("Istnieje już pokój o tej nazwie.");
                return;
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
                System.out.println("Pokój został usunięty.");
                return;
            }
        }
        System.out.println("Nie ma pokoju o tej nazwie.");
    }

    public void loadReservations(Reader reader) {
        this.reader = Reader.getInstance();
        this.reservations = this.reader.readReservationCSV("Reservations.csv");
    }
    
    @Override
    public void loadClients(Reader reader) {
        List<Client> client_list = new ArrayList<>();
        for(ReservationInstance instance : reservations) {
            Client client = instance.getClient();
            client_list.add(client);
        }
        this.clients = client_list;
    }
    
    public Client searchForClient(long clientPESEL) throws Exception {
        Client c = null;
        for(Client client : clients) {
            if(client.PESEL == clientPESEL) {
                c = client;
            }
        }
        if(c == null) {
            throw new Exception("Klient o podanym numerze PESEL nie istnieje!");
        } else {
            return c;
        }
    }
    
    @Override
    public void saveReservations(Writer writer) {
        this.writer = Writer.getInstance();
        this.writer.writeReservationsCSV("Reservations.csv", this.reservations);
    }

    @Override
    public List<Room> findFreeRooms(PeriodControl periodcontrol, List<Room> requested_rooms) {
        List<Room> free_rooms = new ArrayList<>();
        for(Room registered_room : rooms){
            for(Room requested_room : requested_rooms) {
                 if(requested_room.getCapacity() == registered_room.getCapacity() && requested_room.getQuality() == registered_room.getQuality()){
                     free_rooms.add(registered_room); //lepiej dodać registered_room poniewaz on ma przypisany ID, a nie null'a:)
                 }
            }
        }
        LocalDate requestedBegin = periodcontrol.getBegin();
        LocalDate requestedEnd = periodcontrol.getEnd();
        
        for(ReservationInstance reservation : reservations){
           LocalDate reservationBegin = reservation.getPeriodControl().getBegin();
           LocalDate reservationEnd = reservation.getPeriodControl().getEnd();
            //ten if sprawdza czy okresy na siebie zachodza/czy maja czesci wspolne
            if(PeriodControl.isOverLaped(requestedBegin, requestedEnd, reservationBegin, reservationEnd) ) {
            //dla okresow ktore sie pokrywaja sprawdzic czy pokoje sa zajete  
                List<Room> reservation_rooms = reservation.getRoomsInfo(); // tutaj sa pokoje zarejestrowane w tym okresie w tej rejestracji
                for(Room reserved_room : reservation_rooms) {
                    if(free_rooms.contains(reserved_room)) {
                        free_rooms.remove(reserved_room); //usuwam te co sa zajete, uwaga nawet jak jeden dzien sie pokrywa to tez zajete!
                    }
                }
            }
        }
        return free_rooms;
    }
    @Override
    public boolean makeReservation(ReservationInstance request) {
        PeriodControl period = request.getPeriodControl(); //request ma okres na ktory ktos chce zamowic pokoje
        List<Room> requsted_rooms = request.getRoomsInfo(); //request ma w sobie liste pokoi
        List<Room> free_rooms = findFreeRooms(period,requsted_rooms); //check if this request is possible (for this period and this room list)
        if(!free_rooms.isEmpty()) {
            //System.out.println("free roms size: " +free_rooms.size() + "  requested size: " +requsted_rooms.size());  
            List<Room> room_list = new ArrayList<>();
            System.out.println("Obecnie w tym przedziale czasowym mamy do zaoferowania:");
            Scanner scanner = new Scanner(System.in);
            for(Room r : free_rooms){
                System.out.println("Pokój "+r.getName()+", "+r.getCapacity()+"-osobowy, wartość jakości: "+r.getQuality());
                System.out.println("Czy zarejestrować ten pokój?. Proszę wpisać tak lub nie.");
                String userAnswer = scanner.next();
                if(userAnswer.equals("tak")){
                    room_list.add(new Room(r.getName(),r.getCapacity(),r.getQuality()));
                } else if(userAnswer.equals("nie")){
                    continue;
                } else {
                    System.err.println("Podano niewłaściwą komendę.");
                }
            }
            ReservationInstance correct_request = new ReservationInstance(request.getId(),request.getClient(),period,room_list); 
            reservations.add(correct_request);
            return true;
        } else {
            System.out.println("Przepraszamy, w tym przedziale czasowym nie ma żadnych wolnych pokoi, które państwo sobie zażyczyli.");
            return false;
        }
    }
    @Override
    public void deleteReservation(){
        
    }

    @Override
    public void addClient(Client client) {
        if(clients != null) {
            clients.add(client);
        } else {
            this.clients = new ArrayList<>();
            this.clients.add(client);
        }
    }
}
