/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

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
public class ClientTest {

    Person person;
    Student student;
    Pensioner pensioner;
    Invalid invalid;
    CompanyAgent companyagent;
    Learner learner;
    LoyalClient loyalclient;
    
    public ClientTest() {
        person = new Person("Stanisław", "Olejniczak", 55, 62032923453L);
        loyalclient = new LoyalClient("Dorota", "Wellmann", 38, 89042330459L);
        pensioner = new Pensioner("Mateusz", "Rozmaryn", 76, 41041467534L);
        invalid = new Invalid("Małgorzata", "Guz", 47, 70081939122L);
        student = new Student("Hubert", "Banaś", 23, 94072112075L);
        learner = new Learner("Anna", "Mójka", 13, 4031390789L);
        companyagent = new CompanyAgent("Karolina", "Mazur", 32, 85071287432L);
    }

    /**
     * Test of getName method, of class Client.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals("Stanisław", person.getName());
        assertEquals("Dorota", loyalclient.getName());
        assertEquals("Mateusz", pensioner.getName());
        assertEquals("Małgorzata", invalid.getName());
        assertEquals("Hubert", student.getName());
        assertEquals("Anna", learner.getName());
        assertEquals("Karolina", companyagent.getName());
    }

    /**
     * Test of getSurname method, of class Client.
     */
    @Test
    public void testGetSurname() {
        System.out.println("getSurname");
        assertEquals("Olejniczak", person.getSurname());
        assertEquals("Wellmann", loyalclient.getSurname());
        assertEquals("Rozmaryn", pensioner.getSurname());
        assertEquals("Guz", invalid.getSurname());
        assertEquals("Banaś", student.getSurname());
        assertEquals("Mójka", learner.getSurname());
        assertEquals("Mazur", companyagent.getSurname());
    }

    /**
     * Test of getAge method, of class Client.
     */
    @Test
    public void testGetAge() {
        System.out.println("getAge");
        assertEquals(55, person.getAge());
        assertEquals(38, loyalclient.getAge());
        assertEquals(76, pensioner.getAge());
        assertEquals(47, invalid.getAge());
        assertEquals(23, student.getAge());
        assertEquals(13, learner.getAge());
        assertEquals(32, companyagent.getAge());
    }

    /**
     * Test of getPESEL method, of class Client.
     */
    @Test
    public void testGetPESEL() {
        System.out.println("getPESEL");
        assertEquals(62032923453L, person.getPESEL());
        assertEquals(89042330459L, loyalclient.getPESEL());
        assertEquals(41041467534L, pensioner.getPESEL());
        assertEquals(70081939122L, invalid.getPESEL());
        assertEquals(94072112075L, student.getPESEL());
        assertEquals(4031390789L, learner.getPESEL());
        assertEquals(85071287432L, companyagent.getPESEL());
    }

    /**
     * Test of getDiscount method, of class Client.
     */
    @Test
    public void testGetDiscount() {
        System.out.println("getDiscount");
        assertEquals(0, person.getDiscount(), 0);
        assertEquals(0, loyalclient.getDiscount(), 0.2);
        assertEquals(0, pensioner.getDiscount(), 0.3);
        assertEquals(0, invalid.getDiscount(), 0.55);
        assertEquals(0, student.getDiscount(), 0.4);
        assertEquals(0, learner.getDiscount(), 0.47);
        assertEquals(0, companyagent.getDiscount(), 0.35);        
    }

}
