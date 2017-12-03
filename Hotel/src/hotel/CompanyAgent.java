/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 * CompanyAgent class, used for clients who are an agents of company
 * @author Hubert Banaś and Mateusz Galas
 */
public class CompanyAgent extends Client {
    public CompanyAgent(String Name, String Surname, int age, long PESEL) {
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.35;
        this.id = 5;
    }
        /**
     * Clone method
     * @return cloned object of CompanyAgent
     * @throws CloneNotSupportedException 
     */
    @Override
    public Client clone() throws CloneNotSupportedException {
        String _name = this.Name;
        String _surname = this.Surname;
        int _age = this.age;
        Long _pesel = this.PESEL;
        CompanyAgent prototype = new CompanyAgent(_name,_surname,_age,_pesel);
        return prototype;
    }
}
