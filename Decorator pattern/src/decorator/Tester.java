/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decorator;

/**
 *
 * @author Mateusz Galas
 */
public class Tester {
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // basic price = 500
        // basic + engine += 400
        // basic + color += 100
        // tire  = += 200
        Car one = new Engine(new BasicCar(), "Diesel");
        Car two = new Tires(new BasicCar(), "Michellin");
        Car three = new Color(new BasicCar(), "Red");
        System.out.println(one.describe() + ",total price: " + one.getPrice() + ", generates rebate of:" + one.getRebate());
        System.out.println(two.describe() + ",total price: " + two.getPrice() + ", generates rebate of:" + two.getRebate());
        System.out.println(three.describe() + ",total price: " + three.getPrice() + ", generates rebate of:" + three.getRebate());
        Car four = new Color(new Engine(new BasicCar(), "Diesel"),"Red");
        System.out.println(four.describe() + ",total price: " + four.getPrice()+ ", generates rebate of: " + four.getRebate());
        Car five = new Engine(new Tires(new Color(new BasicCar(),"Black"),"Goodyear"),"V7");
        System.out.println(five.describe() + ",total price: " + five.getPrice()+ ", generates rebate of: " + five.getRebate());
        
        Client client = new Client();
        client.buyCar(one);
        System.out.println("client nr 1 buys car one, cars price: "+ one.getPrice());
        System.out.println("client nr 1 rebate for next car: " +client.getRebate() + " after buying car one" );
        
        client.buyCar(two);
        System.out.println("client nr 1 buys car two, cars price: "+ two.getPrice());
        System.out.println("client nr 1 rebate for next car: " +client.getRebate() + " after buying car two");
        
        Client client2 = new Client();
        client2.buyCar(four);
        System.out.println("client nr 2 buys car four, cars price: "+ four.getPrice());
        System.out.println("client nr 2 rebate for next car: " +client2.getRebate() + " after buying car four");
        
        client2.buyCar(three);
        System.out.println("client nr 2 buys car three, cars price: "+ three.getPrice());
        System.out.println("client nr 2 rebate for next car: " +client2.getRebate() + " after buying car three");
        
        client2.buyCar(five);
        System.out.println("client nr 2 buys car five, cars price: "+ five.getPrice());
        System.out.println("client nr 2 rebate for next car: " +client2.getRebate() + " after buying car five");
    }
}
