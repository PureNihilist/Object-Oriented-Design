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
    
    public List<Client> getClients() {
        return clients;
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
                System.out.println("Room has been removed.");
                return;
            }
        }
        System.out.println("There's no room with this name");
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
    public List<Room> findFreeRooms(PeriodControl periodcontrol, List<Room> rooms) {
        List<Room> existing_rooms = new ArrayList<>();
        for(Room r : rooms) {
            for(Room ourroom : this.rooms){
                if(ourroom.getCapacity()==r.getCapacity()&&ourroom.getQuality()==r.getQuality()){
                    existing_rooms.add(ourroom);
                }
            }
        }
        for(ReservationInstance reservation : this.reservations){
            if((periodcontrol.getBegin().isAfter(reservation.getPeriodControl().getBegin())&&periodcontrol.getBegin().isBefore(reservation.getPeriodControl().getEnd()))||(periodcontrol.getEnd().isAfter(reservation.getPeriodControl().getBegin())&&periodcontrol.getEnd().isBefore(reservation.getPeriodControl().getEnd()))){
                for(Room r : existing_rooms){
                    for(Room room : reservation.getRoomsInfo()){
                        if(r.equals(room)){
                            existing_rooms.remove(r);
                        }
                    }
                }
            }
        }
        return existing_rooms;
    }
    
    @Override
    public boolean makeReservation(ReservationInstance request) {
        PeriodControl period = request.getPeriodControl(); //request ma okres na ktory ktos chce zamowic pokoje
        List<Room> rooms = request.getRoomsInfo(); //request ma w sobie liste pokoi
        List<Room> free_rooms = findFreeRooms(period,rooms); //check if this request is possible (for this period and this room list)
        //TUTAJ PRZEGLADANIE LISTY FREE ROOMS CZY COS TAM JEST
        if(!free_rooms.isEmpty()) {
            if(free_rooms.size()>=rooms.size()){
                int count = 0;
                for(Room room : rooms){
                    for(Room ourroom : free_rooms){
                        if(room.equals(ourroom)){
                            count++;
                            break;
                        }
                        else{
                            continue;
                        }
                    }
                }
                if(count==rooms.size()){
                    System.out.println("Dziękujemy, obecnie w tym przedziale czasowym mamy do zaoferowania takie pokoje:");
                for(Room r : free_rooms){
                    System.out.println("Pokój nr "+r.getName()+", "+r.getCapacity()+"-osobowy, wartość jakości: "+r.getQuality());
                }
                return true;
                }
            else{
                System.out.println("Przepraszamy, obecnie tylko takie pokoje są wolne w tym przedziale czasowym:");
                for(Room r : free_rooms){
                    System.out.println("Pokój nr "+r.getName()+", "+r.getCapacity()+"-osobowy, wartość jakości: "+r.getQuality());
                }
                return false;
            }
            }
            else{
                System.out.println("Przepraszamy, obecnie tylko takie pokoje są wolne w tym przedziale czasowym:");
                for(Room r : free_rooms){
                    System.out.println("Pokój nr "+r.getName()+", "+r.getCapacity()+"-osobowy, wartość jakości: "+r.getQuality());
                }
                return false;
            }
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
