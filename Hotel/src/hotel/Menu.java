package hotel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class Menu, containing console menu interface for user defined in list of clients and for administrator
 * @author Hubert Banaś and Mateusz Galas
 */

public class Menu{

    /**
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        
        Scanner scanner = new Scanner(System.in);
        /*Ładowanie wszystkich danych*/
        HotelAdministrator admin = new HotelAdministrator();
        Reader reader = Reader.getInstance();
        Writer writer = Writer.getInstance();
        admin.loadRooms(reader);//pokoje w liście rooms
        admin.loadReservations(reader); //rezerwacje w liscie reservations tu sa ladowani klienci :)
        admin.loadSeasons(reader); //ladowanie sezonow
  
        ClientCache cache = ClientCache.getInstance();
        admin.upgradeLoayalClients(); //zwiekszanie znizek dla stalych klientow za kazde 5 rezerwacji 5% w dol
        
        while(true){
        System.out.println("Witamy w systemie obsługi hotelu recepcja!");
        System.out.println("Wybierz zakres usług.");
        System.out.println("Wprowadź 1 jeśli chcesz przejść do panelu klienta.");
        System.out.println("Wprowadź 2 jeśli chcesz przejść do panelu administratora.");
        try {
            String menuChoice = scanner.next();
            switch (menuChoice) {
                case "1": //panel klienta
                    System.out.println("Podaj twój numer PESEL.");
                    Long pesel = Long.valueOf(scanner.next());
                    List<Client> client_list = cache.getClients();
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
                                String choice = scanner.next();
                                switch(choice) {
                                    case "1"://wszystkie pokoje
                                        admin.getRooms().forEach((r) -> {
                                              System.out.println("Nazwa pokoju:"+r.getName()+",pojemność:"+ r.getCapacity() + ",poziom komfortu:" + r.getQuality() + "/5,cena bazowa:" + r.getPrice());
                                          });
                                        break;
                                    case "2"://wyswietla rezerwacje dla zalogowanego klienta
                                        admin.getReservations().forEach((reservation) -> {
                                            Client client = reservation.getClient();
                                            if(client.getPESEL() == pesel) {
                                                PeriodControl period = reservation.getPeriodControl();
                                                String confirmed = reservation.isConfirmed();
                                                double seasonDiscount = admin.getPriceifSeason(period);
                                                System.out.println("Id rezerwacji:"+reservation.getId()+"potwierdzona: "+confirmed+",imię:"+loggedClientName+",nazwisko:"+loggedClientSurname+",wiek:"+client.getAge()+",numer PESEL:" + client.getPESEL() + ",typ:" + client.getClass().getSimpleName() + ",zniżka:"+client.getDiscount());
                                                System.out.println("Rezerwacja na okres od:"+period.getBegin() + " do " + period.getEnd() + " ");
                                                reservation.getRoomsInfo().forEach((roomInfo) -> {
                                                    System.out.println("Pokój "+roomInfo.getName()+", "+ roomInfo.getCapacity() + "-osobowy, o poziomie komfortu:" + roomInfo.getQuality() + "/5,cena: " + roomInfo.getPrice());
                                                });
                                                System.out.println("Cena za całość rezerwacji ze zniżką: "+reservation.countPrice()*client.getDiscount()*seasonDiscount);
                                            }
                                          });
                                        break;
                                    case "3"://wyswietla wolne pokoje ale... aktualnie tzn minimalnie od dzis :)
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
                                                double seasonPrice = admin.getPriceifSeason(todaysFreeRooms);
                                                if(seasonPrice != 1) {
                                                    System.out.println("Z powodu wydarzenia cena bazowa zmienia się o "+seasonPrice*100+"%");
                                                }
                                                freeRooms.forEach((Room room) -> {
                                                    System.out.println("Pokój "+room.getName()+", "+room.getCapacity()+"-osobowy, o poziomie komfortu: "+room.getQuality()+"/5" + ",cena: " +room.getPrice()*seasonPrice);
                                                });
                                            } else {
                                                System.err.println("W tym przedziale czasowym nie mamy żadnego pokoju do zaoferowania.");
                                            }
                                        } else {
                                            System.err.println("Ta data już mineła. Prosze podać przedział od dnia dzisiejszego("+todayString+") lub później.");
                                            System.out.flush();
                                        }
                                        break;
                                    case "4"://dodaje nowa rezerwacje przez klienta
                                        System.out.println("Zamawianie rezerwacji.");
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
                                            System.out.println("Podaj pojemność pokoju w skali 1-5:");
                                            int roomCapacity = Integer.valueOf(scanner.next());
                                            if(roomCapacity < 0 || roomCapacity > 5) {
                                                System.err.println("Podano pojemność spoza zakresu skali!");
                                                break;
                                            }
                                            System.out.println("Podaj poziom komfortu pokoju w skali 1-5:");
                                            int roomQuality = Integer.valueOf(scanner.next());
                                            if(roomQuality < 0 || roomQuality > 5) {
                                                System.err.println("Podano poziom komfortu spoza zakresu skali!");
                                                break;
                                            }
                                            Room requestedRoom = new Room(roomCapacity,roomQuality);
                                            room_list.add(requestedRoom);
                                        }
                                        long ID = admin.getReservations().size();
                                        ReservationInstance request = new ReservationInstance(++ID,c,period,room_list,false);
                                        admin.makeReservation(request);
                                        break;
                                    case "5"://exit
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
                case "2": //panel admina
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
                            System.out.println("6. Wyświetl aktualne sezony promocyjne.");
                            System.out.println("7. Potwierdzanie rezerwacji klienckich");
                            System.out.println("8. Dodaj nowego klienta.");
                            System.out.println("9. Dodaj nowy pokój.");
                            System.out.println("10. Dodaj nową czasową promocję (wydarzenie okolicznościowe).");
                            System.out.println("11. Dodaj nową rezerwację.");
                            System.out.println("12. Usuń pokój.");
                            System.out.println("13. Usuń rezerwację.");
                            System.out.println("14. Usuń klienta.");
                            System.out.println("15. Zakończ działanie systemu.");
                            String choice = scanner.next();
                            switch (choice) {
                                case "1": //wyswietl wszystkie pokoje
                                    admin.getRooms().forEach((r) -> {
                                        System.out.println("Pokój "+r.getName()+", "+ r.getCapacity() + "-osobowy, o poziomie komfortu:" + r.getQuality() + "/5, cena bazowa:" + r.getPrice());                                                    
                                    });
                                    break;
                                case "2"://wyszukaj rezerwacje po numerze pesel klienta
                                    System.out.println("Wyszukiwanie Rezerwacji.");
                                    System.out.println("Podaj numer pesel klienta.");
                                    admin.findReservation(Long.valueOf(scanner.next()));
                                    break;
                                case "3"://wszystkie rezerwacje
                                    admin.getReservations().forEach((r) -> {
                                        Client client = r.getClient();
                                        PeriodControl period = r.getPeriodControl();
                                        double seasonDiscount = admin.getPriceifSeason(period);
                                        String confirmed = r.isConfirmed();
                                        System.out.println("Id rezerwacji:"+r.getId()+", czy jest potwierdzona: "+confirmed + ", imię:" +client.getName() + ", nazwisko:" + client.getSurname() + ", wiek:" + client.getAge() + ", numer PESEL:" + client.getPESEL() + ", grupa zniżkowa:" + client.getClass().getSimpleName() + ", zniżka klienta:"+client.discount);
                                        System.out.println("Rezerwacja na okres od:"+period.getBegin() + " do " + period.getEnd() + " ");
                                        if(seasonDiscount != 1) {
                                            System.out.println("Z powodu wydarzenia cena bazowa zmienia się o "+seasonDiscount*100+"%");
                                        }
                                        r.getRoomsInfo().forEach((roomInfo) -> {
                                            System.out.println("Pokój "+roomInfo.getName()+", "+ roomInfo.getCapacity() + "-osobowy, o poziomie komfortu:" + roomInfo.getQuality() + "/5,cena: " + roomInfo.getPrice()*seasonDiscount);                         
                                        });
                                        System.out.println("Cena za całość rezerwacji ze zniżką: "+r.countPrice()*client.getDiscount()*seasonDiscount);
                                    });
                                    break;
                                case "4": //wyswietl wszystkich gosci hotelowych!
                                    List<Client> actual_client_list = cache.getClients();
                                    actual_client_list.forEach((client) -> {
                                        System.out.println("Imię:" +client.getName() + ", nazwisko:" + client.getSurname() + ", wiek:" + client.getAge() + ", numer PESEL:" + client.getPESEL() + ", grupa zniżkowa:" + client.getClass().getSimpleName() + ", zniżka klienta:"+client.discount);
                                    });
                                    break;
                                case "5": //wyswietla wolne pokoje
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
                                        double seasonPrice = admin.getPriceifSeason(freeRoomPeriod);
                                        if(seasonPrice != 1) {
                                            System.out.println("Z powodu wydarzenia cena bazowa zmienia się o "+seasonPrice*100+"%");
                                        }
                                        freeRooms.forEach((Room r) -> {
                                            System.out.println("Pokój "+r.getName()+", "+r.getCapacity()+"-osobowy, o poziomie komfortu:"+r.getQuality()+"/5, cena: "+ r.getPrice()*seasonPrice);
                                        });
                                    } else {
                                        System.err.println("W tym przedziale czasowym nie mamy żadnego pokoju do zaoferowania.");
                                    }
                                    break;
                                case "6"://aktualne sezony promocyjne
                                    ArrayList<PeriodControl> actual_seasons = admin.getSeasons();
                                    for(PeriodControl control : actual_seasons) {
                                        double value = control.getRabate();
                                        System.out.println("Od: "+control.getBegin().toString() + " do " + control.getEnd().toString() + ",zniżka: "+value);
                                    }
                                    break;
                                case "7"://potwierdzanie rezerwacji klienckich
                                    System.out.println("Potwierdanie rezerwacji klienckich");
                                    System.out.println("Aktualnie niepotwierdzone rejestracje: ");
                                    List<ReservationInstance> actual_reservations = admin.getReservations();
                                    List<ReservationInstance> unconfirmed = new ArrayList();
                                    for(ReservationInstance r : actual_reservations){
                                        if(r.isConfirmed().equals("NIE")){
                                            Client client = r.getClient();
                                            PeriodControl period = r.getPeriodControl();
                                            System.out.println("Id rezerwacji:"+r.getId()+", imię:" +client.getName() + ", nazwisko:" + client.getSurname() + ", wiek:" + client.getAge() + ",numer PESEL:" + client.getPESEL() + ",typ:" + client.getClass().getSimpleName() + ",zniżka:"+client.discount);
                                            System.out.println("Rezerwacja na okres od:"+period.getBegin() + " do " + period.getEnd() + " ");
                                            double seasonPrice = admin.getPriceifSeason(period);
                                            if(seasonPrice != 1) {
                                                    System.out.println("Z powodu wydarzenia cena bazowa zmienia się o "+seasonPrice*100+"%");
                                            }
                                            r.getRoomsInfo().forEach((roomInfo) -> {
                                                System.out.println("Pokój "+roomInfo.getName()+", "+ roomInfo.getCapacity() + "-osobowy, o poziomie komfortu" + roomInfo.getQuality() + "/5, cena:" + roomInfo.getPrice()*seasonPrice);
                                            });
                                            System.out.println("Cena za całość rezerwacji ze zniżką: "+r.countPrice()*client.getDiscount()*seasonPrice);
                                            unconfirmed.add(r);
                                        }
                                    }
                                    if(!unconfirmed.isEmpty()) {
                                        System.out.println("Proszę podać ID rezerwacji, którą potwierdzić.");
                                        long Id = Long.valueOf(scanner.next());
                                        for(ReservationInstance r : unconfirmed) {
                                            if(r.getId() == Id){
                                                r.setConfirmed();
                                                break;
                                            }
                                        }
                                        System.err.println("Wprowadzono złe id rezerwacji");
                                        break;
                                    } else {
                                        System.out.println("Brak niepotwierdzonych rezerwacji w systemie.");
                                        break;
                                    }
                                case "8"://dodawanie klienta
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
                                    cache.createClient(newclientName, newclientSurname, newclientAge,peselNumber,discountChoice);
                                    break;
                                case "9": //dodawanie nowego pokoju
                                    int roomCounter = admin.getRooms().size();
                                    System.out.println("Dodawanie pokoju.");
                                    System.out.println("Aktualnie hotel posiada "+roomCounter +" zapisanych pokoi.");
                                    System.out.println("Podaj nazwę pokoju:");
                                    String roomName = scanner.next();
                                    System.out.println("Podaj pojemność pokoju w skali 1-5:");
                                    int roomCapacity = Integer.valueOf(scanner.next());
                                    if(roomCapacity < 0 || roomCapacity > 5) {
                                        System.err.println("Podano pojemność spoza zakresu skali!");
                                        break;
                                    }
                                    System.out.println("Podaj poziom komfortu pokoju w skali 1-5:");
                                    int roomQuality = Integer.valueOf(scanner.next());
                                    if(roomQuality < 0 || roomQuality > 5) {
                                        System.err.println("Podano poziom komfortu spoza zakresu skali!");
                                        break;
                                    }
                                    admin.addRoom(roomName, roomCapacity, roomQuality );
                                    break;
                                case "10": //dodaj nowy event okolicznosciowy
                                    System.out.println("Dodawanie promocji okolicznościowej.");
                                    System.out.println("Od kiedy? Podaj datę w formacie YYYY-MM-DD");
                                    String eventdateFrom = scanner.next();
                                    LocalDate eventfrom = LocalDate.parse(eventdateFrom);
                                    System.out.println("Do kiedy? Podaj datę w formacie YYYY-MM-DD");
                                    String eventdateTo = scanner.next();
                                    LocalDate eventto = LocalDate.parse(eventdateTo);
                                    PeriodControl event = new PeriodControl(eventfrom,eventto);
                                    System.out.println("Ile procent ceny bazowej ma wynosić pokój w czasie tego wydarzenia? Podaj liczbę całkowitą.");
                                    double discount = scanner.nextInt()*0.01;
                                    admin.addEvent(event,discount);
                                    break;
                                case "11": //tworzenie rezerwacji
                                    System.out.println("Dodawanie rezerwacji.");
                                    System.out.println("Podaj numer PESEL klienta");
                                    long clientID = Long.valueOf(scanner.next());
                                    Client client = cache.searchForClient(clientID);
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
                                        System.out.println("Podaj pojemność pokoju w skali 1-5:");
                                        int capacity = Integer.valueOf(scanner.next());
                                        if(capacity < 0 || capacity > 5) {
                                            System.err.println("Podano pojemność spoza zakresu skali!");
                                            break;
                                        }
                                        System.out.println("Podaj poziom komfortu pokoju w skali 1-5:");
                                        int quality = Integer.valueOf(scanner.next());
                                        if(quality < 0 || quality > 5) {
                                            System.err.println("Podano poziom komfortu spoza zakresu skali!");
                                            break;
                                        }
                                        Room requestedRoom = new Room(capacity,quality);
                                        room_list.add(requestedRoom);
                                    }
                                    long ID = admin.getReservations().size();
                                    ReservationInstance request = new ReservationInstance(++ID,client,period,room_list,true);
                                    admin.makeReservation(request);
                                    break;
                                case "12": //usuwanie pokoju
                                    System.out.println("Usuwanie pokoju.");
                                    System.out.println("Podaj nazwę pokoju do usunięcia.");
                                    admin.deleteRoom(scanner.next());
                                    break;
                                case "13"://usuwanie rezerwacji
                                    System.out.println("Usuwanie rezerwacji.");
                                    System.out.println("Podaj id rezerwacji do usunięcia.");
                                    admin.deleteReservation(Long.valueOf(scanner.next()));
                                    break;
                                case "14"://usuwanie klienta
                                    System.out.println("Usuwanie klienta.");
                                    System.out.println("Podaj Pesel klienta do usunięcia.");
                                    admin.deleteClient(Long.valueOf(scanner.next()));
                                    break;
                                case "15": //exit
                                    admin.saveRooms(writer);
                                    admin.saveReservations(writer);
                                    admin.saveSeasons(writer);
                                    System.exit(0);
                                default:
                                    System.err.println("Podano złą komendę.");
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
            } catch(Exception ex){
                System.err.println("Wprowadzono niewłaściwe dane");
                System.out.println("Wprowadź enter jeśli chcesz wrócić do wyboru menu.");
                System.in.read();
            }
        }//koniec glownego while'a        
    }//koniec maina
}//koniec klasy Menu
    
