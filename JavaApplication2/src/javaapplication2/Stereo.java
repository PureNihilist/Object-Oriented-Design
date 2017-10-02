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
public class Stereo extends Product{
    
    private String audio_formats;
    private String disk_reader;
    private String column_type;
    
    public void setObslugiwane_formaty_audio(String Obslugiwane_formaty_audio123){
        this.audio_formats = Obslugiwane_formaty_audio123;        
    }
    
    public String getObslugiwane_formaty_audio(){
    return this.audio_formats;
    }
    
    public void setOdtwarzanie_plyt(String Odtwarzanie_plyt123){
        this.disk_reader = Odtwarzanie_plyt123;
    }
    
    public String getOdtwarzanie_plyt(){
        return this.disk_reader;
    }
 
    public void setTyp_kolumn(String Typ_kolumn123){
        this.column_type = Typ_kolumn123;
    }
    
    public String getTyp_kolumn(){
        return this.column_type;
    }
    
}
