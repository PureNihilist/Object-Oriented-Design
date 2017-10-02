/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication1;

/**
 *
 * @author student
 */
public class Produkt {
    
    private int ID;
    private String model;
    private String marka;
    private double price;
    
    public void setID(int ID){
        this.ID = ID;
    }
    
    public int getID(){
        return this.ID;
}
    
    public void setModel(String Name){
        this.model = Name;
    }
    
    public String getModel(){
        return this.model;
    }
    
    public void setMarka(String Name){
        this.marka = Name;
    }
    
    public String getMarka(){
        return this.marka;
    }
    
    public void setPrice(double price){
        this.price = price;
    }
    
    public double getPrice(){
    return this.price;
    }
    
}
