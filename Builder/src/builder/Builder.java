package builder;

/**
 *
 * @author Mateusz Galas
 */
public class Builder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Waiter kelner = new Waiter();
        dishBuilder danieObiadowe = new Dinner();
        danieObiadowe.createNewProduct();
        kelner.setDanieBuilder(danieObiadowe);
        System.out.println(danieObiadowe.getDanie());
        danieObiadowe.name();
        danieObiadowe.describe();
        
        dishBuilder sniadanie = new Breakfast();
        sniadanie.createNewProduct();
        kelner.setDanieBuilder(sniadanie);
        System.out.println(sniadanie.getDanie());
        sniadanie.name();
        sniadanie.describe();
        
        dishBuilder kolacja = new Supper();
        kolacja.createNewProduct();
        kelner.setDanieBuilder(kolacja);
        System.out.println(kolacja.getDanie());
        kolacja.name();
        kolacja.describe();
        
        // kolacja, obiad, sniadanie rozszerzaja builderDanie
        // builder danie to abstrakcyjna klasa pokazujaca metody ktore potem przekazuje
        // danie to zwykla klasa przechowujaca informacje o obiekcie + sety/gety
        // dyrektor robi buildera i go ustawia 
        // kelnerowi moge przypisac danie obiadowe tworzone przez builder implementowane przez konkretne danie
        
    }
    
}
