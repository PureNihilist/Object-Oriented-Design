/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication2;

/**
 *
 * @author student
 */
// singleton
public class Shop {
    private static Shop singleton = new Shop();
    
    private Shop(){
        
    }
    
    public static Shop getInstance() {
        return singleton;
    }
    
    protected static void method() {
        
    }
}


//@Value.Immutable(singleton==true)
/*
public class Slmm {
}
ImmutableSingletonImmutables.builder().build();



*/