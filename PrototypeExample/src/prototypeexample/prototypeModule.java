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
public class prototypeModule {
      private static List<Prototype> prototypes = new ArrayList<>();
      
      public static void addPrototype(Prototype p ) {
          prototypes.add(p);
      }
      
      public static Prototype createPrototype(String name, String surname) {
           for(Prototype p : prototypes) {
            if(p.getName().equals(name) && p.getSurname().equals(surname) ){
                return p.clone();
            }
        }
         return null;
      }
}
