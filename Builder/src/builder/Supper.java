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
public class Supper extends dishBuilder{

    @Override
    public void name() {
        System.out.println("Supper");
    }

    @Override
    public void describe() {
        System.out.println("Example supper");
    }
    
}
