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
public interface Prototype {
    
    Prototype clone();
    int getMoney();
    String getName();
    String getSurname();
    void collectMoney();
    
}