package decorator;

/**
 *
 * @author Mateusz Galas
 */
public class Color extends CarDecorator{
    String color;
    
    protected Color(Car car, String color) {
        super(car);
        this.color = color;
        this.price = car.price + 100;
        this.rebate = this.price * 3/100;
        this.desc = car.desc +" color "+this.color;
    }
    
    @Override
    public String describe() {
        return this.desc;
    }
    
    protected void setColor(String new_type) {
        this.color = new_type;
    }
    
    public String getColor() {
        return this.color;
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
