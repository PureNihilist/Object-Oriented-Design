/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 * Student class, used for clients with age lower or equal 26
 * @author Hubert Banaś and Mateusz Galas
 */
public class Student extends Client{
    public Student(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        if(age <= 26) {
            this.age = age;
        }
        this.PESEL = PESEL;
        this.discount = 0.5;
        this.id = 2;
    }
    /**
     * Clone method
     * @return cloned object of Student
     * @throws CloneNotSupportedException 
     */
    @Override
    public Client clone() throws CloneNotSupportedException {
        String _name = this.Name;
        String _surname = this.Surname;
        int _age = this.age;
        Long _pesel = this.PESEL;
        Student prototype = new Student(_name,_surname,_age,_pesel);
        return prototype;
    }
}
