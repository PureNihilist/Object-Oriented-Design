/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 * Person class, used for normal clients without any discounts and less than five reservations in our system
 * @author Hubert Banaś and Mateusz Galas
 */
public class Person extends Client{
    public Person(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 1; //cena calkowita
        this.id = 0;
    }
    /**
     * Clone method
     * @return cloned object of Person
     * @throws CloneNotSupportedException 
     */
    @Override
    public Client clone() throws CloneNotSupportedException {
        String _name = this.Name;
        String _surname = this.Surname;
        int _age = this.age;
        Long _pesel = this.PESEL;
        Person prototype = new Person(_name,_surname,_age,_pesel);
        return prototype;
    }
}
