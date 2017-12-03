/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 * Pensioner class, used for clients on retirement
 * @author Hubert Banaś and Mateusz Galas
 */
public class Pensioner extends Client{
    public Pensioner(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.6;
        this.id = 3;
    }
    /**
     * Clone method
     * @return cloned object of Pensioner
     * @throws CloneNotSupportedException 
     */
    @Override
    public Client clone() throws CloneNotSupportedException {
        String _name = this.Name;
        String _surname = this.Surname;
        int _age = this.age;
        Long _pesel = this.PESEL;
        Pensioner prototype = new Pensioner(_name,_surname,_age,_pesel);
        return prototype;
    }
}
