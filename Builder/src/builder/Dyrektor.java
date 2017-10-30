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
public class Dyrektor {
    private builderDanie dyrektor;
    
    public void setDanieBuilder(builderDanie drk) {
        dyrektor = drk;
    }
     
    public void constructorDanie(){
        dyrektor.opis();
        dyrektor.nazwa();
    }
}
