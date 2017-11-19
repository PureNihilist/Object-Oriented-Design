package hotel;

/**
 *
 * @author Hubert Banaś and Mateusz Galas
 */
abstract class Client {
    public Client(){}
    public String Name;
    public String Surname;
    public int age;
    public int PESEL;
    public double discount;

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public int getAge() {
        return age;
    }

    public int getPESEL() {
        return PESEL;
    }

    public double getDiscount() {
        return discount;
    }
}

class Person extends Client{
    public Person(String Name, String Surname, int age, int PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0;
    }
}

class LoyalClient extends Client{
    public LoyalClient(String Name, String Surname, int age, int PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.2;
    }
}

class Learner extends Client{
    public Learner(String Name, String Surname, int age, int PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        if(age <= 21) {
            this.age = age;
        }
        this.PESEL = PESEL;
        this.discount = 0.47;
    }
}

class Student extends Client{
    public Student(String Name, String Surname, int age, int PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        if(age <= 26) {
            this.age = age;
        }
        this.PESEL = PESEL;
        this.discount = 0.4;
    }
}

class Pensioner extends Client{
    public Pensioner(String Name, String Surname, int age, int PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.3;
    }
}

class Invalid extends Client{
    public Invalid(String Name, String Surname, int age, int PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.55;
    }
}

class CompanyAgent extends Client {
    public CompanyAgent(String Name, String Surname, int age, int PESEL) {
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.35;
    }
}

