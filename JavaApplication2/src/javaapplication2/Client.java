/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication2;

/**
 *
 * @author Mateusz Galas
 */
public class Client {
   public final int object_id;
   private static int counter_id = 0;
   private String name;
   private String surname;
   private String mail;
   
   Client(String Name, String Surname, String Mail) {
        this.object_id = counter_id++;
        this.name = Name;
        this.surname = Surname;
        this.mail = Mail;
   }
   
   void setName(String new_name) {
       this.name = new_name;
   }
   
   void setSurname(String new_surname) {
       this.surname = new_surname;
   }
   
   void setMail(String new_mail) {
       this.mail = new_mail;
   }
   
   String getName() {
       return this.name;
   }
   
   String getSurname() {
       return this.surname;
   }
   
   String getMail() {
       return this.mail;
   }
   
   int getID() {
       return this.object_id;
   }
}