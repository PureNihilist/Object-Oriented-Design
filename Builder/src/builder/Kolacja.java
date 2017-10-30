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
public class Kolacja extends builderDanie{

    @Override
    public void nazwa() {
        System.out.println("Kolacja");
    }

    @Override
    public void opis() {
        System.out.println("Opis kolacji");
    }
    
}
