package decorator;

import java.util.ArrayList;

/**
 *
 * @author Mateusz Galas
 */


public class Client {
    private ArrayList<Car> myCars = null;
    private int currentRebate;
    public Client() {
        currentRebate = 0;
        myCars = new ArrayList<>();
    }
    
    Car buyCar(Car newCar) {
        if(currentRebate != 0) {
            newCar.price = newCar.getPrice() - currentRebate;
            currentRebate = 0;
        }
        myCars.add(newCar);
        currentRebate += newCar.rebate;
        return newCar;
    }
    
    Car getCar(int index) {
        return myCars.get(index);
    }
    
    int getRebate() {
        return this.currentRebate;
    }
}
