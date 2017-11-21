package hotel;
import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {
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
            System.out.println("2. Wyświetl wszystkie rezerwacje.");
            System.out.println("3. Dodaj nowy pokój.");
            System.out.println("4. Dodaj nową rezerwację.");
            System.out.println("5. Usuń pokój.");
            System.out.println("6. Usuń rezerwację.");
            System.out.println("7. Wyszukaj wolne pokoje.");
            System.out.println("8. Zakończ działanie systemu.");
            int choice = scanner.nextInt();           
            switch (choice) {
                case 1:        
                    admin.getRooms().forEach((r) -> {
                        System.out.println(r.getName() + " " + r.getCapacity() + " " + r.getQuality() + " " + r.getPrice() );
                     });
                    break;
                case 2:
                    admin.getReservations().forEach((r) -> {
                        Client client = r.getClient();
                        PeriodControl period = r.getPeriodControl();
                        System.out.println(client.getName() + " " + client.getSurname() + " " + client.getAge() + " " + client.getPESEL());
                    });
                    break;
                case 3:
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
                case 4:
                    //add reservation
                    break;
                        
                case 5: //remove room
                    break;
                case 8: //exit
                    admin.saveRooms(writer);
                    admin.saveReservations(writer);
                    System.exit(0);
            }
        }
    }
}