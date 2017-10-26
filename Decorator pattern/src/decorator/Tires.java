package decorator;

/**
 *
 * @author Mateusz Galas
 */
public class Tires extends CarDecorator{
    String tireType;
    
    protected Tires(Car car, String engineType) {
        super(car);
        this.tireType = engineType;
        this.price = car.price + 200;
        this.rebate = this.price * 3/100;
        this.desc = car.desc + " tire type "+this.tireType;
    }
    
    @Override
    public String describe() {
        return this.desc;
    }
    
    protected void setTireType(String new_type) {
        this.tireType = new_type;
    }
    
    public String getEngineType() {
        return this.tireType;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public int getRebate() {
        return this.rebate;
    }
}
