/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.util.List;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author huber
 */
public class ClientCacheTest {
    
    ClientCache clientcache;
    
    public ClientCacheTest() {
        clientcache = ClientCache.getInstance();
        clientcache.initilizeClientCache(new ArrayList<Client>());
        Person person = new Person("Adam", "Gonciarz", 29, 88112394560L);
        clientcache.getClients().add(person);
    }


    /**
     * Test of getInstance method, of class ClientCache.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        clientcache = ClientCache.getInstance();
        assertNotNull(clientcache);
    }

    /**
     * Test of initilizeClientCache method, of class ClientCache.
     */
    @Test
    public void testInitilizeClientCache() {
        System.out.println("initilizeClientCache");
        List<Client> clients = new ArrayList<>();
        clientcache.initilizeClientCache(clients);
        assertEquals(clientcache.getClients(), clients);
    }

    /**
     * Test of getClients method, of class ClientCache.
     */
    @Test
    public void testGetClients() {
        System.out.println("getClients");
        clientcache.initilizeClientCache(new ArrayList<Client>());
        List<Client> result = clientcache.getClients();
        assertNotNull(result);
    }

    /**
     * Test of UpdateClients method, of class ClientCache.
     */
    @Test
    public void testUpdateClients() {
        System.out.println("UpdateClients");
        List<Client> clients = new ArrayList<>();
        clientcache.UpdateClients(clients);
        assertEquals(clients, clientcache.getClients());
    }

    /**
     * Test of searchForClient method, of class ClientCache.
     */
    @Test
    public void testSearchForClient() throws Exception {
        System.out.println("searchForClient");
        Person person = new Person("Adam", "Gonciarz", 29, 88112394560L);
        Client result = clientcache.searchForClient(88112394560L);
        assertEquals(person.getName(), result.getName());
        assertEquals(person.getAge(), result.getAge());
        assertEquals(person.getSurname(), result.getSurname());
        assertEquals(person.getPESEL(), result.getPESEL());
        assertEquals(person.getId(), result.getId());
    }

    /**
     * Test of createClient method, of class ClientCache.
     */
    @Test
    public void testCreateClient() throws Exception {
        System.out.println("createClient");
        String name = "Hubert";
        String surname = "Banaś";
        int age = 23;
        long pesel = 94072112075L;
        int type = 2;
        Student student = new Student("Hubert", "Banaś", 23, 94072112075L);
        Client result = clientcache.createClient(name, surname, age, pesel, type);
        assertEquals(student.getName(), result.getName());
        assertEquals(student.getAge(), result.getAge());
        assertEquals(student.getSurname(), result.getSurname());
        assertEquals(student.getPESEL(), result.getPESEL());
        assertEquals(student.getId(), result.getId());
    }
    
}
