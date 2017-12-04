/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.util.List;

/**
 * ClientCache class, building prototypes depending on class ID
 */
public class ClientCache {
    
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
                break;
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
        Client client = null;
        if(clients != null) {
            for(Client p : clients) {//jest taki klient
                if(p.getName().equals(name) && p.getSurname().equals(surname) && p.getAge() == age && p.getPESEL() == pesel ){
                    client = (Client)p.clone();
                    if(type == 6){
                        client.upgradeDiscount();
                        client.setId(6);
                    }
                    return client;
                }
            } // nie ma takiego klienta jeszcze

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
