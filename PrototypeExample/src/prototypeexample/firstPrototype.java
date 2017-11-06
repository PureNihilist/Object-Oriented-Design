/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototypeexample;

/**
 *
 * @author PC
 */
public class firstPrototype implements Prototype{
    String name = "Mateusz";
    String surname = "Galas";
    int money = 0;

    @Override
    public Prototype clone() {
        return new firstPrototype();
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
        money +=1;
    }
    
}
