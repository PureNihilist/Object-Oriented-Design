package Hotel;
import java.util.Scanner;

public class Menu{
    public static void main(String[] args) {
        System.out.println("Witamy w systemie obsługi hotelu recepcja!");
        System.out.println();
        System.out.println("1. Wyświetl wszystkie rezerwacje.");
        System.out.println("2. Dodaj nową rezerwację.");
        System.out.println("3. Wyszukaj wolne pokoje.");
        System.out.println("4. Zakończ działanie systemu.");
        Scanner reader = new Scanner(System.in);
        int choice = reader.nextInt();
        switch (choice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                System.exit(0);
        }
    }
}