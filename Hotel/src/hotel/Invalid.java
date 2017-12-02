/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;


/**
 * Invalid class, used for clients physically or mentally disabled
 * @author Hubert Banaś and Mateusz Galas
 */
public class Invalid extends Client{
    public Invalid(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.55;
        this.id = 4;
    }
        /**
     * Clone method
     * @return cloned object of Invalid
     * @throws CloneNotSupportedException 
     */
    @Override
    public Client clone() throws CloneNotSupportedException {
        String _name = this.Name;
        String _surname = this.Surname;
        int _age = this.age;
        Long _pesel = this.PESEL;
        Invalid prototype = new Invalid(_name,_surname,_age,_pesel);
        return prototype;
    }
}
