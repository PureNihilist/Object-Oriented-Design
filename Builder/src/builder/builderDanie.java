/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

/**
 *
 * @author PC
 */
public abstract class builderDanie {
    protected Danie d;
    public abstract void nazwa();
    public abstract void opis();
   
    public void createNewProduct() {
        d = new Danie();
    }
    
    public Danie getDanie() {
        return d;
    }
}
