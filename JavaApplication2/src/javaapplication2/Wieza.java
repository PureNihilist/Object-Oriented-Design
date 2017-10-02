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
public class Wieza extends Produkt{
    
    private String Obslugiwane_formaty_audio;
    private String Odtwarzanie_plyt;
    private String Typ_kolumn;
    
    public void setObslugiwane_formaty_audio(String Obslugiwane_formaty_audio123){
        this.Obslugiwane_formaty_audio = Obslugiwane_formaty_audio123;        
    }
    
    public String getObslugiwane_formaty_audio(){
    return this.Obslugiwane_formaty_audio;
    }
    
    public void setOdtwarzanie_plyt(String Odtwarzanie_plyt123){
        this.Odtwarzanie_plyt = Odtwarzanie_plyt123;
    }
    
    public String getOdtwarzanie_plyt(){
        return this.Odtwarzanie_plyt;
    }
 
    public void setTyp_kolumn(String Typ_kolumn123){
        this.Typ_kolumn = Typ_kolumn123;
    }
    
    public String getTyp_kolumn(){
        return this.Typ_kolumn;
    }
    
}
