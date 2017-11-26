package hotel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu{
    public static void main(String[] args) throws Exception{
        
        Scanner scanner = new Scanner(System.in);
        /*Ładowanie wszystkich danych*/
        HotelAdministrator admin = new HotelAdministrator();
        Reader reader = Reader.getInstance();
        Writer writer = Writer.getInstance();
        admin.loadRooms(reader);//pokoje w liście rooms
        admin.loadReservations(reader); //rezerwacje w liscie reservations
        admin.loadClients(reader); //klienci w liście clients
        while(true){
        System.out.println("Witamy w systemie obsługi hotelu recepcja!");
        System.out.println("Wybierz zakres usług.");
        System.out.println("Wprowadź 1 jeśli chcesz przejść do panelu klienta.");
        System.out.println("Wprowadź 2 jeśli chcesz przejść do panelu administratora.");
        int menuChoice = Integer.valueOf(scanner.next());
            switch (menuChoice) {
                case 1: //panel klienta
                    System.out.println("Podaj twój numer PESEL.");
                    Long pesel = Long.valueOf(scanner.next());
                    List<Client> client_list = admin.getClientControler().getClients();
                    for(Client c: client_list){
                        if(c.getPESEL() == pesel){ //logowanie
                            while(true){
                                String loggedClientName = c.getName();
                                String loggedClientSurname = c.getSurname();
                                System.out.println("Witaj "+loggedClientName +" "+loggedClientSurname+"!");
                                System.out.println("PANEL KLIENTA");
                                System.out.println("1. Wyświetl wszystkie pokoje.");
                                System.out.println("2. Wyświetl wszystkie twoje rezerwacje.");
                                System.out.println("3. Wyświetl aktualnie wolne pokoje.");
                                System.out.println("4. Zamów nową rezerwację.");
                                System.out.println("5. Zakończ działanie systemu.");
                                int choice = scanner.nextInt();
                                switch(choice) {
                                    case 1://wszystkie pokoje
                                        admin.getRooms().forEach((r) -> {
                                              System.out.println("Nazwa pokoju:"+r.getName()+",pojemność:"+ r.getCapacity() + ",poziom komfortu:" + r.getQuality() + ",cena:" + r.getPrice());
                                          });
                                        break;
                                    case 2://wyswietla rezerwacje dla zalogowanego klienta
                                        admin.getReservations().forEach((reservation) -> {
                                            Client client = reservation.getClient();
                                            if(client.getPESEL() == pesel) {
                                                PeriodControl period = reservation.getPeriodControl();
                                                System.out.println("Id rezerwacji:"+reservation.getId()+",imię:"+loggedClientName+",nazwisko:"+loggedClientSurname+",wiek:"+client.getAge()+",numer PESEL:" + client.getPESEL() + ",typ:" + client.getClass().getSimpleName() + ",zniżka bazowa:"+client.getDiscount());
                                                System.out.println("Rezerwacja na okres od:"+period.getBegin() + " do " + period.getEnd() + " ");
                                                reservation.getRoomsInfo().forEach((roomInfo) -> {
                                                    System.out.println("Nazwa pokoju:"+roomInfo.getName()+",pojemność:"+ roomInfo.getCapacity() + ",poziom komfortu:" + roomInfo.getQuality() + ",cena:" + roomInfo.getPrice());
                                                });
                                            }
                                          });
                                        break;
                                    case 3://wyswietla wolne pokoje ale... aktualnie tzn minimalnie od dzis :)
                                        System.out.println("Wyświetlanie aktualnie wolnych pokoi/");
                                        LocalDate today = LocalDate.now();
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
                                        String todayString = today.format(formatter);
                                        System.out.println("Dzisiaj mamy "+todayString);
                                        System.out.println("Od kiedy? Podaj datę w formacie YYYY-MM-DD");
                                        String dateFromStr = scanner.next();
                                        LocalDate localDateFrom = LocalDate.parse(dateFromStr);
                                        if(localDateFrom.isAfter(today) || localDateFrom.isEqual(today)) {
                                            System.out.println("Do kiedy? Podaj datę w formacie YYYY-MM-DD");
                                            String dateToStr = scanner.next();
                                            LocalDate localDateTo = LocalDate.parse(dateToStr);
                                            PeriodControl todaysFreeRooms = new PeriodControl(localDateFrom,localDateTo);
                                            List<Room> freeRooms = admin.findFreeRooms(todaysFreeRooms, admin.getRooms());
                                            if(!freeRooms.isEmpty()) {
                                                System.out.println("Obecnie w tym przedziale czasowym mamy do zaoferowania:");
                                                freeRooms.forEach((Room room) -> {
                                                    System.out.println("Pokój "+room.getName()+", "+room.getCapacity()+"-osobowy, o poziomie komfortu: "+room.getQuality());
                                                });
                                            } else {
                                                System.err.println("W tym przedziale czasowym nie mamy żadnego pokoju do zaoferowania.");
                                            }
                                        } else {
                                            System.err.println("Ta data już mineła. Prosze podać przedział od dnia dzisiejszego("+todayString+") lub później.");
                                            System.out.flush();
                                        }
                                        break;
                                    case 4://dodaje nowa rezerwacje przez klienta
                                        System.out.println("Zamawianie rezerwacji.");
                                        System.out.println("Podaj numer PESEL klienta");
                                        Client client = admin.getClientControler().searchForClient(Long.valueOf(scanner.next()));
                                        if(client == null) {
                                            System.err.println("Klient o podanym numerze PESEL nie istnieje!");
                                            break;
                                        } 
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
                                    case 5://exit
                                        admin.saveReservations(writer); // teraz pytanie czy klient'a rezerwacje dodawac od razu do systemu ? czy czekac na akceptacje recepcji 
                                        System.exit(0);
                                    default://zla komenda
                                        System.err.println("Podano złą komendę.");
                                        System.out.println("Wprowadź enter jeśli chcesz wrócić do twoje panelu klienta.");
                                        System.in.read();   
                                        break;
                                }//koniec switcha                                       
                            }//koniec while'a
                        } //koniec if'a           
                    }//koniec for'a
                    System.err.println("Nie znaleziono klienta o tym numerze pesel w systemie.");
                    System.out.println("Wprowadź enter jeśli chcesz wrócić do wyboru menu.");
                    System.in.read();
                    break;        
                //koniec panelu klienta
                case 2: //panel admina
                    System.out.println("Podaj hasło do panelu administatora.");
                    String password = scanner.next();
                    if(password.equals("ogorek")) {
                        while(true) {
                            System.out.println("PANEL ADMINISTATORA");
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
                                    System.out.println("Wyszukiwanie Rezerwacji.");
                                    System.out.println("Podaj numer pesel klienta.");
                                    admin.findReservation(Long.valueOf(scanner.next()));
                                    break;
                                case 3:
                                    admin.getReservations().forEach((r) -> {
                                        Client client = r.getClient();
                                        PeriodControl period = r.getPeriodControl();
                                        System.out.println("Id rezerwacji:"+r.getId() + ",imię:" +client.getName() + ",nazwisko:" + client.getSurname() + ",wiek:" + client.getAge() + ",numer PESEL:" + client.getPESEL() + ",typ:" + client.getClass().getSimpleName() + ",zniżka bazowa:"+client.discount);
                                        System.out.println("Rezerwacja na okres od:"+period.getBegin() + " do " + period.getEnd() + " ");
                                        r.getRoomsInfo().forEach((roomInfo) -> {
                                            System.out.println("Nazwa pokoju:"+roomInfo.getName()+",pojemność:"+ roomInfo.getCapacity() + ",poziom komfortu:" + roomInfo.getQuality() + ",cena:" + roomInfo.getPrice());
                                        });
                                    });
                                    break;
                                case 5: //wyswietla wolne pokoje
                                    System.out.println("Wyświetlanie wolnych pokoi");
                                    System.out.println("Od kiedy? Podaj datę w formacie YYYY-MM-DD");
                                    String dateFromStr = scanner.next();
                                    LocalDate localDateFrom = LocalDate.parse(dateFromStr);
                                    System.out.println("Do kiedy? Podaj datę w formacie YYYY-MM-DD");
                                    String dateToStr = scanner.next();
                                    LocalDate localDateTo = LocalDate.parse(dateToStr);
                                    PeriodControl freeRoomPeriod = new PeriodControl(localDateFrom,localDateTo);
                                    List<Room> freeRooms = admin.findFreeRooms(freeRoomPeriod, admin.getRooms());
                                    if(!freeRooms.isEmpty()) {
                                        System.out.println("Obecnie w tym przedziale czasowym mamy do zaoferowania:");
                                        freeRooms.forEach((Room r) -> {
                                            System.out.println("Pokój "+r.getName()+", "+r.getCapacity()+"-osobowy, o poziomie komfortu: "+r.getQuality());
                                        });
                                    } else {
                                        System.err.println("W tym przedziale czasowym nie mamy żadnego pokoju do zaoferowania.");
                                    }
                                    break;
                                case 6://dodawanie klienta
                                    System.out.println("Dodawanie nowego klienta.");
                                    System.out.println("Podaj imię.");
                                    String newclientName = scanner.next();
                                    System.out.println("Podaj nazwisko.");
                                    String newclientSurname = scanner.next();
                                    System.out.println("Podaj wiek.");
                                    int newclientAge = Integer.valueOf(scanner.next());
                                    System.out.println("Podaj numer PESEL.");
                                    long peselNumber = Long.valueOf(scanner.next());
                                    System.out.println("Czy podana osoba korzysta z podanych ulg? ");
                                    int discountChoice = Integer.valueOf(scanner.next());
                                    System.out.println("Wpisać 0 jeśli osoba nie korzysta z ulg.");
                                    System.out.println("Ulga 1 uczeń");
                                    System.out.println("Ulga 2 student");
                                    System.out.println("Ulga 3 emeryt");
                                    System.out.println("Ulga 4 inwalida");
                                    System.out.println("Ulga 5 dla firm");                                     
                                    admin.getClientControler().createClient(newclientName, newclientSurname, newclientAge,peselNumber,discountChoice);
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
                                    Client client = admin.getClientControler().searchForClient(clientID);
                                    if(client == null) {
                                        System.err.println("Klient o podanym numerze PESEL nie istnieje!");
                                        break;
                                    } 
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
                                    admin.deleteRoom(scanner.next());
                                    break;
                                case 10:
                                    System.out.println("Usuwanie rezerwacji.");
                                    System.out.println("Podaj id rezerwacji do usunięcia.");
                                    admin.deleteReservation(Long.valueOf(scanner.next()));
                                    break;
                                case 11:
                                    System.out.println("Usuwanie klienta.");
                                    System.out.println("Podaj Pesel klienta do usunięcia.");
                                    admin.getClientControler().deleteClient(Long.valueOf(scanner.next()));
                                    break;
                                case 12: //exit
                                    admin.saveRooms(writer);
                                    admin.saveReservations(writer);
                                    System.exit(0);
                                default:
                                    System.err.println("Podano złą komendę.");
                                    System.out.println("Wprowadź enter jeśli chcesz wrócić do wyboru menu.");
                                    System.in.read();
                                    break;
                            }//koniec switcha
                            System.out.println("Wprowadź enter jeśli chcesz przejść do menu.");
                            System.in.read();
                        }//koniec while(true) ->panelu admina
                    } else { //zle haslo
                        System.err.println("Wpisano złe hasło.");
                        System.out.println("Wprowadź enter jeśli chcesz wrócić do wyboru menu.");
                        System.in.read();
                        break;
                    }
                default: //gdy wybrano zla opcje w wyborze menu
                    System.err.println("Wprowadzono niewłaściwą opcję.");
                    System.out.println("Wprowadź enter jeśli chcesz wrócić do wyboru menu.");
                    System.in.read();
                    break;
            }//koniec glownego switcha
        }//koniec glownego while'a        
    }//koniec maina
}//koniec klasy Menu
    
