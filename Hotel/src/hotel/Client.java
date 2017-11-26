package hotel;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author Hubert Banaś and Mateusz Galas
 */
abstract class Client implements Cloneable {
    protected String Name;
    protected String Surname;
    protected int age;
    protected long PESEL;
    protected double discount;
    protected String type;
    private String id;
    
    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public int getAge() {
        return age;
    }

    public long getPESEL() {
        return PESEL;
    }
    
    public double getDiscount() {
        return discount;
    }
    public String getType(){
      return type;
    }
   
    public String getId() {
      return id;
    }
   
        
    @Override
    public Object clone() throws CloneNotSupportedException {
      Object clone = null;
      
      try {
        clone = super.clone(); 
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      
      return clone;
    }
}

class Person extends Client{
    public Person(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0;
        this.type = "person";
    }
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

class LoyalClient extends Client{
    public LoyalClient(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.2;
        this.type = "loyal";
    }
    @Override
    public Client clone() throws CloneNotSupportedException {
        String _name = this.Name;
        String _surname = this.Surname;
        int _age = this.age;
        Long _pesel = this.PESEL;
        LoyalClient prototype = new LoyalClient(_name,_surname,_age,_pesel);
        return prototype;
    }
}

class Learner extends Client{
    public Learner(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        if(age <= 21) {
            this.age = age;
        }
        this.PESEL = PESEL;
        this.discount = 0.47;
        this.type = "learner";
    }
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

class Student extends Client{
    public Student(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        if(age <= 26) {
            this.age = age;
        }
        this.PESEL = PESEL;
        this.discount = 0.4;
        this.type = "student";
    }
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

class Pensioner extends Client{
    public Pensioner(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.3;
        this.type = "pensioner";
    }
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

class Invalid extends Client{
    public Invalid(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.55;
        this.type = "invalid";
    }
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

class CompanyAgent extends Client {
    public CompanyAgent(String Name, String Surname, int age, long PESEL) {
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.35;
        this.type = "agent";
    }
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

class ClientCache {
    private List<Client> clients = null;
    private List<ReservationInstance> reservations = null;
    
    public void initializeCache(List<Client> loadedList, List<ReservationInstance> loadedReservations) {
        clients = loadedList;
        reservations = loadedReservations;
    }
    
    public List<Client> getClients() {
        if(clients != null){ 
            return clients;
        } else {
            throw new NullPointerException("Lista klientów nie została poprawnie wczytana. Program zostanie zakończony.");
        }
    }
    
    public void deleteClient(long pesel){
        //usuwanie wszystkich rezerwacji na tego klienta
        reservations.forEach((ReservationInstance instance) -> {
            Client toRemove = instance.getClient();
            if (toRemove.PESEL == pesel) {
                reservations.remove(instance); 
            }
        });
        
        for(Client client : this.clients){
            if(client.getPESEL()==pesel){
                this.clients.remove(client);
                break;
            }
        }       
    }
     
    public Client searchForClient(long clientPESEL) throws Exception {
        Client c = null;
        for(Client client : clients) {
            if(client.PESEL == clientPESEL) {
                c = client;
            }
        }
        return c; //c moze byc nullem ale jest to obsluzone w uzyciu -> patrz Menu
    }
      
    public Client createClient(String name, String surname, int age, long pesel, int type) throws CloneNotSupportedException {
        
        if(clients == null) {
            throw new NullPointerException("Lista klienta nie została wczytana");
        }
        
        for(Client p : clients) {//jest taki klient
            if(p.getName().equals(name) && p.getSurname().equals(surname) && p.getAge() == age && p.getPESEL() == pesel ){
                return (Client)p.clone();
            }
        } // nie ma takiego klienta jeszcze
        Client client = null;
        switch(type) {
                  case 0 :
                      client = new Person(name,surname,age,pesel);
                      break;
                  case 1 :
                      client = new LoyalClient(name,surname,age,pesel);
                      break;
                  case 2 :
                      client = new Learner(name,surname,age,pesel);
                      break;
                  case 3 :
                      client = new Student(name,surname,age,pesel);
                      break;
                  case 4 :
                      client = new Pensioner(name,surname,age,pesel);
                      break;
                  case 5 :
                      client = new Invalid(name,surname,age,pesel);;
                      break;
                  case 6 :
                      client = new CompanyAgent(name,surname,age,pesel);
                      break;
                  default :
                      System.err.println("Podano niewłaściwy typ ulgi.");
                      break;
        }
        this.clients.add(client);
        return client;
    }
}