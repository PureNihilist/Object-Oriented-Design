/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 * Learner class, used for clients with age lower or equal 21
 * @author Hubert Banaś and Mateusz Galas
 */
public class Learner extends Client{
    public Learner(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        if(age <= 21) {
            this.age = age;
        }
        this.PESEL = PESEL;
        this.discount = 0.47;
        this.id = 1;
    }
    /**
     * Clone method
     * @return cloned object of Learner
     * @throws CloneNotSupportedException 
     */
    @Override
    public Client clone() throws CloneNotSupportedException {
        String _name = this.Name;
        String _surname = this.Surname;
        int _age = this.age;
        Long _pesel = this.PESEL;
        Learner prototype = new Learner(_name,_surname,_age,_pesel);
        return prototype;
    }
}
