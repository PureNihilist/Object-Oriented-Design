package decorator;

/**
 *
 * @author Mateusz Galas
 */
public abstract class CarDecorator extends Car {
    protected Car car;

    protected CarDecorator(Car car) {
        this.car = car;
    }
    @Override
    protected abstract String describe();
    
    @Override
    protected abstract int getPrice();
    
    @Override 
    protected abstract int getRebate();
}
