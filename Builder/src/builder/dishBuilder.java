/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

/**
 *
 * @author PC
 */
public abstract class dishBuilder {
    protected Dish d;
    public abstract void name();
    public abstract void describe();
   
    public void createNewProduct() {
        d = new Dish();
    }
    
    public Dish getDanie() {
        return d;
    }
}
