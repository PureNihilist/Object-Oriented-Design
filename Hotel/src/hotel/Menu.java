package hotel;
import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {
        System.out.println("Witamy w systemie obsługi hotelu recepcja!");
        System.out.println("1. Wyświetl wszystkie pokoje.");
        System.out.println("2. Wyświetl wszystkie rezerwacje.");
        System.out.println("3. Dodaj nową rezerwację.");
        System.out.println("4. Wyszukaj wolne pokoje.");
        System.out.println("5. Zakończ działanie systemu.");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        HotelAdministrator admin = new HotelAdministrator();
        Reader reader = Reader.getInstance();
        switch (choice) {
            case 1:        
                admin.loadRooms(reader);
                admin.getRooms().forEach((r) -> {
                    System.out.println(r.getName() + " " + r.getCapacity() + " " + r.getQuality() + " " + r.getPrice() );
                 });
                break;
            case 2:
                admin.loadReservations(reader);
                admin.getReservations().forEach((r) -> {
                    Client client = r.getClient();
                    PeriodControl period = r.getPeriodControl();
                    System.out.println(client.getName() + " " + client.getSurname() + " " + client.getAge() + " " + client.getPESEL());
                });
                break;
            case 3:

                break;
            case 4:
                
                
                break;
                
            case 5:
                System.exit(0);
        }
    }
}