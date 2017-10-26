package decorator;

/**
 *
 * @author Mateusz Galas
 */
public class Engine extends CarDecorator{

    String engineType;
    
    protected Engine(Car car, String engineType) {
        super(car);
        this.engineType = engineType;
        this.price = car.price + 400;
        this.rebate = this.price * 3/100;
        this.desc = car.desc + " engine type "+this.engineType;
    }
    
    @Override
    public String describe() {
        return this.desc;
    }
    
    protected void setEngineType(String new_type) {
        this.engineType = new_type;
    }
    
    public String getEngineType() {
        return this.engineType;
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
