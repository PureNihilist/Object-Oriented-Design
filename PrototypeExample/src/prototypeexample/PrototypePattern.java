/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypeexample;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class PrototypePattern {
    public static void main(String [] args) {
        if(args.length > 0) {
            initializePrototype();
            List<Prototype> prototypes = new ArrayList<>();
           
            for(String name_surname : args) {
                String name = name_surname.split("_")[0];
                String surname = name_surname.split("_")[1];
                Prototype prototype = prototypeModule.createPrototype(name,surname);
                if(prototype != null) {
                    prototypes.add(prototype);
                }
            }
            for(Prototype p : prototypes) {
                p.collectMoney();
                System.out.println(p.getName() + " " + p.getSurname() + " collected " + p.getMoney() + " money ");
            }
            
        } else {
            System.out.println("Run again with arguments");
        }
    }
    
    
    
    public static void initializePrototype() {
        prototypeModule.addPrototype(new firstPrototype());
        prototypeModule.addPrototype(new secondPrototype());
        prototypeModule.addPrototype(new thirdPrototype());
    }
}
