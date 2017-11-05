/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

/**
 *
 * @author Mateusz Galas
 */
public class Waiter {
    private dishBuilder waiter;
    
    public void setDanieBuilder(dishBuilder dB) {
        waiter = dB;
    }
     
    public void constructorDanie(){
        waiter.describe();
        waiter.name();
    }
}
