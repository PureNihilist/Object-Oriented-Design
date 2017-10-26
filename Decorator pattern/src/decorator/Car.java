package decorator;

/**
 *
 * @author Mateusz Galas
 */
abstract class Car {
    protected int price = 500;
    protected String desc = "Car serial number: "+ String.valueOf(this.hashCode());
    protected int rebate = 0;

    protected String describe() {
        return desc;
    }
    
    protected int getPrice() {
        return price;
    }
    
    protected int getRebate() {
        return rebate;
    }
}
