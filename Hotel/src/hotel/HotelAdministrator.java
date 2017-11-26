package hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Mateusz Galas
 */
public class HotelAdministrator implements Hotel{
    private List<Room> rooms = null; //list of rooms
    private List<ReservationInstance> reservations = null;//list of reservations
    private Reader reader;
    private Writer writer;
    
    @Override
    public void loadRooms(Reader reader) {
        this.reader = Reader.getInstance(); //get singleton instance of reader
        this.rooms = this.reader.readRoomsCSV("Rooms.csv");
    }
    
    public List<Room> getRooms() {
        if(rooms != null){ 
            return rooms;
        } else {
            throw new NullPointerException("Lista pokoi nie została poprawnie wczytana. Program zostanie zakończony.");
        }
    }
    
    public List<ReservationInstance> getReservations() {
        if(reservations != null){ 
            return reservations;
        } else {
            throw new NullPointerException("Lista rezerwacji nie została poprawnie wczytana. Program zostanie zakończony.");
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
                System.err.println("Istnieje już pokój o tej nazwie.");
                return;
            }
        }
        Room room = new Room(name,nOfBeds,Quality);
        rooms.add(room);
    }
    @Override
    public void findReservation(long Pesel){
        List<ReservationInstance> list_reservations = new ArrayList<>();
        this.reservations.forEach((r) -> {
            if(r.getClient().getPESEL()==Pesel){
                list_reservations.add(r);
            }
        });
        if(list_reservations.isEmpty()){
            System.out.println("Nie ma w naszym systemie rezerwacji na tego klienta.");
        }
        else{
            System.out.println("Lista rezerwacji:");
            int count = 1;
            for(ReservationInstance res : list_reservations){
                System.out.println("Rezerwacja potwierdzona: "+res.isConfirmed());
                System.out.print(count+". "+res.getPeriodControl().getBegin()+"-"+res.getPeriodControl().getEnd()+" Pokoje: ");
                res.getRoomsInfo().forEach((Room r) -> {
                    System.out.print("Nazwa "+r.getName()+", Wielkość "+r.getCapacity()+"-osobowy, jakość "+r.getQuality()+" ");
                });
                System.out.println("");
                count++;
            }
        }
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
        System.err.println("Nie ma pokoju o tej nazwie.");
    }
    
    public void deleteClient(long pesel){
        //usuwanie wszystkich rezerwacji na tego klienta
        reservations.forEach((ReservationInstance instance) -> {
            Client toRemove = instance.getClient();
            if (toRemove.PESEL == pesel) {
                reservations.remove(instance); 
            }
        });
        
        ClientCache instance = ClientCache.getInstance();
        List<Client> clients = instance.getClients();
        for(Client client : clients){
            if(client.getPESEL()==pesel){
                clients.remove(client);
                break;
            }
        }
        instance.UpdateClients(clients);
    }
     

    public void loadReservations(Reader reader) {
        this.reader = Reader.getInstance();
        this.reservations = this.reader.readReservationCSV("Reservations.csv");
    }
    
    @Override
    public void saveReservations(Writer writer) {
        this.writer = Writer.getInstance();
        this.writer.writeReservationsCSV("Reservations.csv", this.reservations);
    }

    @Override
    public List<Room> findFreeRooms(PeriodControl periodcontrol, List<Room> requested_rooms) {
        List<Room> free_rooms = new ArrayList<>();
        rooms.forEach((Room registered_room) -> {
            requested_rooms.stream().filter((requested_room) -> (requested_room.getCapacity() == registered_room.getCapacity() && requested_room.getQuality() == registered_room.getQuality())).filter((_item) -> (!free_rooms.contains(registered_room))).forEachOrdered((_item) -> {
                free_rooms.add(registered_room); //lepiej dodać registered_room poniewaz on ma przypisany ID, a nie null'a:)
            });
        });
        LocalDate requestedBegin = periodcontrol.getBegin();
        LocalDate requestedEnd = periodcontrol.getEnd();
        
        reservations.forEach((ReservationInstance reservation) -> {
            LocalDate reservationBegin = reservation.getPeriodControl().getBegin();
            LocalDate reservationEnd = reservation.getPeriodControl().getEnd();
            //ten if sprawdza czy okresy na siebie zachodza/czy maja czesci wspolne
            if (PeriodControl.isOverLaped(requestedBegin, requestedEnd, reservationBegin, reservationEnd)) {
                //dla okresow ktore sie pokrywaja sprawdzic czy pokoje sa zajete
                List<Room> reservation_rooms = reservation.getRoomsInfo(); // tutaj sa pokoje zarejestrowane w tym okresie w tej rejestracji
                reservation_rooms.stream().filter((reserved_room) -> (free_rooms.contains(reserved_room))).forEachOrdered(free_rooms::remove //usuwam te co sa zajete, uwaga nawet jak jeden dzien sie pokrywa to tez zajete!
                );
            }
        });
        return free_rooms;
    }
    @Override
    public boolean makeReservation(ReservationInstance request) {
        PeriodControl period = request.getPeriodControl(); //request ma okres na ktory ktos chce zamowic pokoje
        List<Room> requested_rooms = request.getRoomsInfo(); //request ma w sobie liste pokoi
        List<Room> free_rooms = findFreeRooms(period,requested_rooms); //check if this request is possible (for this period and this room list)
        if(!free_rooms.isEmpty()) {
            //System.out.println("free roms size: " +free_rooms.size() + "  requested size: " +requsted_rooms.size());  
            System.out.println("Obecnie w tym przedziale czasowym mamy do zaoferowania:");
            Scanner scanner = new Scanner(System.in);
            free_rooms.forEach((Room r) -> {
                System.out.println("Pokój "+r.getName()+", "+r.getCapacity()+"-osobowy, o poziomie komfortu: "+r.getQuality());
            });
            System.out.println("Proszę podać nazwę/nazwy pokoju/oi do rejestracji z listy dostępnych, oddzielone przecinkiem.");
            String userAnswer = scanner.next();
            String [] roomNames = userAnswer.split(",");
            if(roomNames.length > requested_rooms.size()) {
                System.err.println("Podano za dużo nazw pokoi. Zostanie zapisane pierwsze "+requested_rooms.size() + " pokoi");
            } else if (roomNames.length == 0 ){
                System.err.println("Nie podano żadnej nazwy pokoju albo była ona nieprawidłowa.");
                return false;
            }
            List<Room> room_list = new ArrayList<>();
            for(int i = 0 ; i < requested_rooms.size() ; i++){
                for(Room room : free_rooms) {
                    if(room.getName().equals(roomNames[i])) {
                       System.out.println("Zapisano pokój "+room.getName());
                       room_list.add(room);
                    } 
                }
            }
            boolean confirmation = request.isConfirmed;
            ReservationInstance correct_request = new ReservationInstance(request.getId(),request.getClient(),period,room_list,confirmation); 
            reservations.add(correct_request);
            return true;
        } else {
            System.err.println("Przepraszamy, w tym przedziale czasowym nie ma żadnych wolnych pokoi, które państwo sobie zażyczyli.");
            return false;
        }
    }
    /*
    Operatory funkcyjne działaja tak jak normalne for each'e
    */
    @Override
    public void deleteReservation(long ID){
        reservations.stream().filter((instance) -> (instance.getId() == ID)).forEachOrdered((ReservationInstance instance) -> {
            reservations.remove(instance);
        });
    }
}
