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
class TestApplication {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client client1 = new Client("jan","kowalski","jan.kowalski@gmail.com");
        Client client2 = new Client("andrzej","malinowski","andrzej.malinowski@gmail.com");
        Client client3 = new Client("zbigniew","nowak","zbigniew.nowak@gmail.com");

        String aa = client1.getName();
        int id1 = client1.getID();
        int id2 = client2.getID();
        int id3 = client3.getID();
        Shop myshop;
        myshop.getInstance();
    
        System.out.println(aa);
        System.out.println(id1 + " " + id2 + " " + id3);

    
    }
    
}