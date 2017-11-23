package hotel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu{
    public static void main(String[] args) throws Exception{
        System.out.println("Witamy w systemie obsługi hotelu recepcja!");
        
        Scanner scanner = new Scanner(System.in);
       
        HotelAdministrator admin = new HotelAdministrator();
        Reader reader = Reader.getInstance();
        Writer writer = Writer.getInstance();
        admin.loadRooms(reader);
        admin.loadReservations(reader);
        while(true) {
            System.out.println("MENU");
            System.out.println("1. Wyświetl wszystkie pokoje.");
            System.out.println("2. Wyszukaj rezerwację.");
            System.out.println("3. Wyświetl wszystkie rezerwacje.");
            System.out.println("4. Wyświetl wszystkich gości hotelowych.");
            System.out.println("5. Wyświetl wolne pokoje.");
            System.out.println("6. Dodaj nowego klienta.");
            System.out.println("7. Dodaj nowy pokój.");
            System.out.println("8. Dodaj nową rezerwację.");
            System.out.println("9. Usuń pokój.");
            System.out.println("10. Usuń rezerwację.");
            System.out.println("11. Usuń klienta.");
            System.out.println("12. Zakończ działanie systemu.");
            int choice = scanner.nextInt();           
            switch (choice) {
                case 1:        
                    admin.getRooms().forEach((r) -> {
                        System.out.println("Nazwa pokoju:"+r.getName()+",pojemność:"+ r.getCapacity() + ",poziom komfortu:" + r.getQuality() + ",cena:" + r.getPrice());
                     });
                    break;
                case 2:
                    //zrobic
                    break;
                case 3:
                    admin.getReservations().forEach((r) -> {
                        Client client = r.getClient();
                        PeriodControl period = r.getPeriodControl();
                        System.out.println("Id rezerwacji:"+r.getId() + ",imię:" +client.getName() + ",nazwisko:" + client.getSurname() + ",wiek:" + client.getAge() + ",numer PESEL:" + client.getPESEL() + ",typ:" + client.getClass().getSimpleName() + ",zniżka bazowa:"+client.discount);
                        System.out.println("Rezerwacja na okres od:"+period.begin + " do " + period.end + " ");
                        r.getRoomsInfo().forEach((roomInfo) -> {
                            System.out.println("Nazwa pokoju:"+roomInfo.getName()+",pojemność:"+ roomInfo.getCapacity() + ",poziom komfortu:" + roomInfo.getQuality() + ",cena:" + roomInfo.getPrice());
                        });
                    });
                    break;
                case 5: //wyswietla wolne pokoje
                    System.out.println("Wolne pokoje");
                  //  PeriodControl control = new PeriodControl();
                  //  admin.findFreeRooms(periodcontrol, room_list);
                    
                    
                    break;
                case 6://dodawanie klienta
                    break;
                case 7: //dodawanie nowego pokoju
                    int roomCounter = admin.getRooms().size();
                    System.out.println("Dodawanie pokoju. ");
                    System.out.println("Aktualnie hotel posiada "+roomCounter +" zapisanych pokoi.");
                    System.out.println("Podaj nazwę pokoju:"); 
                    String roomName = scanner.next();
                    System.out.println("Podaj pojemność pokoju:");
                    int roomCapacity = Integer.valueOf(scanner.next());
                    System.out.println("Podaj poziom komfortu pokoju:");
                    int roomQuality = Integer.valueOf(scanner.next());   
                    admin.addRoom(roomName, roomCapacity, roomQuality );
                    break;
                case 8: //tworzenie rezerwacji
                    System.out.println("Dodawanie rezerwacji.");
                    System.out.println("Podaj numer PESEL klienta");
                    long clientID = Long.valueOf(scanner.next());
                    Client client = admin.searchForClient(clientID);
                    System.out.println("Podaj termin rezerwacji.");
                    System.out.println("Od kiedy? Podaj datę w formacie YYYY-MM-DD");
                    String dateFrom = scanner.next();
                    LocalDate from = LocalDate.parse(dateFrom);
                    System.out.println("Do kiedy? Podaj datę w formacie YYYY-MM-DD");
                    String dateTo = scanner.next();
                    LocalDate to = LocalDate.parse(dateTo);
                    PeriodControl period = new PeriodControl(from,to);
                    System.out.println("Podaj ilość pokoi.");
                    int roomAmount = Integer.valueOf(scanner.next());
                    List<Room> room_list = new ArrayList<>();
                    for(int i = 0; i < roomAmount; i++){
                        System.out.println("Podaj pojemność pokoju:");
                        int capacity = Integer.valueOf(scanner.next());
                        System.out.println("Podaj poziom komfortu pokoju:");
                        int quality = Integer.valueOf(scanner.next());
                        Room requestedRoom = new Room(capacity,quality);
                        room_list.add(requestedRoom);
                    }
                    long ID = admin.getReservations().size();
                    ReservationInstance request = new ReservationInstance(++ID,client,period,room_list);
                    admin.makeReservation(request);
                    break;                       
                case 9: 
                    System.out.println("Usuwanie pokoju.");
                    System.out.println("Podaj nazwę pokoju do usunięcia.");
                    String room_name = scanner.next();
                    admin.deleteRoom(room_name);
                    break;
                case 10:
                    System.out.println("Usuwanie rezerwacji.");
                    System.out.println("Podaj id rezerwacji do usunięcia.");
                    String reservation_name = scanner.next(); 
                    admin.deleteReservation(); //Usuwanie po id? czy innych rzeczach ?? 
                    break;
                case 11://usun klienta
                    break;
                case 12: //exit
                    // moze pytanie czy zapisac zmiany ? 
                    admin.saveRooms(writer);
                    admin.saveReservations(writer);
                    System.exit(0);
                default:
                    System.err.println("Podano złą komendę");
                    break;
            }
        }
    }
    
}