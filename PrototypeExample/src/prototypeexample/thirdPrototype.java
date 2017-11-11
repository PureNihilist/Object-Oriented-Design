/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypeexample;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Mateusz Galas
 */
public class thirdPrototype implements Prototype{
    String name = "Jakub";
    String surname = "Jas";
    int money = 0;
    HashMap<Integer,Integer> nominals = new HashMap<>();

    @Override
    public Prototype clone() {
        thirdPrototype prototype = new thirdPrototype();
        prototype.money = this.getMoney();
        prototype.nominals = Prototype.copy(this.nominals);
        return prototype;
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public void collectMoney() {
        Random random = new Random();
        nominals.put(5,random.nextInt(20));
        nominals.put(10,random.nextInt(15));
        nominals.put(20,random.nextInt(10));
        nominals.put(100,random.nextInt(10));
        
        for (Map.Entry<Integer, Integer> entry : nominals.entrySet()) {
            Integer key = entry.getKey(); //nominal
            Integer value = entry.getValue(); //ilosc banknotow
            money += key * value;
        }
    }

    @Override
    public HashMap<Integer, Integer> getNominals() {
        return nominals;
    }
}
