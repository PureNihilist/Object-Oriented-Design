package builder;

/**
 *
 * @author Mateusz Galas
 */
public class Dinner extends dishBuilder{
    // nazwa i opis metodu
    @Override
    public void name() {
        System.out.println("Dinner");
    }

    @Override
    public void describe() {
        System.out.println("Example dinner");
    }
}
