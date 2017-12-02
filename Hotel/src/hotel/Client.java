package hotel;

import java.util.List;

/**
 *  Client class, using pattern prototype for clients of our system
 * @author Hubert Banaś and Mateusz Galas
 * 
 */
abstract class Client implements Cloneable {
    protected String Name;
    protected String Surname;
    protected int age;
    protected long PESEL;
    protected double discount;
    protected int id; //typ klienta od 0 do 5

    /**
     * getName method
     * @return Client Name
     */
    
    public String getName() {
        return Name;
    }

    /**
     * getSurname method
     * @return Client Surname
     */
    public String getSurname() {
        return Surname;
    }
    /**
     * getAge method
     * @return Client Age
     */
    public int getAge() {
        return age;
    }
    /**
     * getPESEL method
     * @return Client PESEL
     */
    public long getPESEL() {
        return PESEL;
    }
    /**
     * getDiscount method
     * @return Client discount
     */
    public double getDiscount() {
        return discount;
    }
    /**
     * getId method
     * @return Client ID
     */
    public int getId(){
      return id;
    }
    
    /**
     * upgradeDiscount method, to increase client discount
     */
    public void upgradeDiscount(){
        this.discount = this.getDiscount()+0.05;
    }
        
    /**
     * Clone method
     * @return cloned object of client
     * @throws CloneNotSupportedException 
     */
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
/**
 * Person class, used for normal clients without any discounts and less than five reservations in our system
 */
class Person extends Client{
    public Person(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0;
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
/**
 * Learner class, used for clients with age lower or equal 21
 */
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
/**
 * Student class, used for clients with age lower or equal 26
 */
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
/**
 * Pensioner class, used for clients on retirement
 */
class Pensioner extends Client{
    public Pensioner(String Name, String Surname, int age, long PESEL){
        super();
        this.Name = Name;
        this.Surname = Surname;
        this.age = age;
        this.PESEL = PESEL;
        this.discount = 0.3;
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
/**
 * Invalid class, used for clients physically or mentally disabled
 */
class Invalid extends Client{
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
/**
 * CompanyAgent class, used for clients who are an agents of company
 */
class CompanyAgent extends Client {
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
/**
 * ClientCache class, building prototypes depending on class ID
 */
class ClientCache {
    
    private List<Client> clients = null;
    
    /**
     * Instance of class ClientCache
     */
    public volatile static ClientCache cache = null;
    
    /**
     *Constructor, which prevents from creating another instance of class clientcache.
     * @throws RuntimeException
     */
    private ClientCache() {
        if(cache != null) { 
            throw new RuntimeException("Cannot create instance of singleton. Use getInstance().");
        }
    }
    
    /**
     * Singleton method to get instance of class.
     * @return Instance of class clientcache.
     */
    public static ClientCache getInstance() {
        ClientCache clientCache = ClientCache.cache;
        if(clientCache == null) {
            synchronized(ClientCache.class) {
                clientCache = ClientCache.cache;
                if(clientCache == null) {
                    ClientCache.cache = clientCache = new ClientCache();
                }
            }
        }
        return clientCache;
    }
    /**
     * Initializes list of clients
     * @param clients 
     */
    public void initilizeClientCache(List<Client> clients) {
        this.clients = clients;
    }
    
    /**
     * getClients method
     * @return list of Client objects 
     */
    public List<Client> getClients() {
        if(clients != null){ 
            return clients;
        } else {
            throw new NullPointerException("Lista klientów nie została poprawnie wczytana. Program zostanie zakończony.");
        }
    }
    /**
     * Updates list of clients
     * @param clients 
     */
    public void UpdateClients(List<Client> clients ) {
        this.clients = clients; 
    }
    
    /**
     * Finds client in list
     * @param clientPESEL
     * @return client object
     * @throws Exception 
     */
    public Client searchForClient(long clientPESEL) throws Exception {
        Client c = null;
        for(Client client : clients) {
            if(client.PESEL == clientPESEL) {
                c = client;
            }
        }
        return c; //c moze byc nullem ale jest to obsluzone w uzyciu -> patrz Menu
    }
    /**
     * Creates new client, and then adds to list
     * @param name
     * @param surname
     * @param age
     * @param pesel
     * @param type
     * @return client object
     * @throws CloneNotSupportedException 
     */ 
    public Client createClient(String name, String surname, int age, long pesel, int type) throws CloneNotSupportedException {
        if(clients != null) {
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
                        client = new Learner(name,surname,age,pesel);
                        break;
                    case 2 :
                        client = new Student(name,surname,age,pesel);
                        break;
                    case 3 :
                        client = new Pensioner(name,surname,age,pesel);
                        break;
                    case 4 :
                        client = new Invalid(name,surname,age,pesel);;
                        break;
                    case 5 :
                        client = new CompanyAgent(name,surname,age,pesel);
                        break;
                    default :
                        System.err.println("Podano niewłaściwy typ ulgi.");
                        break;
            }
            this.clients.add(client);
            return client;
        } else {
            throw new NullPointerException("Lista klienta nie została wczytana");
        }
    }
}