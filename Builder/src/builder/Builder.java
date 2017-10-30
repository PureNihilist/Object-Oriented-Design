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
        Dyrektor kelner = new Dyrektor();
        builderDanie danieObiadowe = new Obiad();
        danieObiadowe.createNewProduct();
        kelner.setDanieBuilder(danieObiadowe);
        System.out.println(danieObiadowe.getDanie());
        danieObiadowe.nazwa();
        danieObiadowe.opis();
        
        builderDanie sniadanie = new Sniadanie();
        sniadanie.createNewProduct();
        kelner.setDanieBuilder(sniadanie);
        System.out.println(sniadanie.getDanie());
        sniadanie.nazwa();
        sniadanie.opis();
        
         builderDanie kolacja = new Kolacja();
        kolacja.createNewProduct();
        kelner.setDanieBuilder(kolacja);
        System.out.println(kolacja.getDanie());
        kolacja.nazwa();
        kolacja.opis();
        
        // kolacja, obiad, sniadanie rozszerzaja builderDanie
        // builder danie to abstrakcyjna klasa pokazujaca metody ktore potem przekazuje
        // danie to zwykla klasa przechowujaca informacje o obiekcie + sety/gety
        // dyrektor robi buildera i go ustawia 
        // kelnerowi moge przypisac danie obiadowe tworzone przez builder implementowane przez konkretne danie
        
    }
    
}
