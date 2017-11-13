/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 *
 * @author PC
 */
abstract class Client {
    public Client(){}
    public String Name;
    public String Surname;
    public int age;
    public long PESEL;
    public double discount;
}

class Person extends Client{
    public Person(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0;
    }
}

class LoyalClient extends Client{
    public LoyalClient(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.2;
    }
}

class Learner extends Client{
    public Learner(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.47;
    }
}

class Student extends Client{
    public Student(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.4;
    }
}

class Pensioner extends Client{
    public Pensioner(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.3;
    }
}

class Invalid extends Client{
    public Invalid(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.55;
    }
}

