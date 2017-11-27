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
    
    public ClientTest() {
        person = new Person("Stanisław", "Olejniczak", 55, 62032923453L);
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
        assertEquals(0, pensioner.getDiscount(), 0.3);
        assertEquals(0, invalid.getDiscount(), 0.55);
        assertEquals(0, student.getDiscount(), 0.4);
        assertEquals(0, learner.getDiscount(), 0.47);
        assertEquals(0, companyagent.getDiscount(), 0.35);        
    }
    
    
    /**
     * Test of upgradeDiscount method, of class Client.
     */
    @Test
    public void testUpgradeDiscount() {
        System.out.println("upgradeDiscount");
        person.upgradeDiscount();
        person.upgradeDiscount();
        assertEquals(0.1, person.getDiscount(), 0.001);
        pensioner.upgradeDiscount();
        assertEquals(0.35, pensioner.getDiscount(), 0.001);
        invalid.upgradeDiscount();
        assertEquals(0.6, invalid.getDiscount(), 0.001); 
        student.upgradeDiscount();
        student.upgradeDiscount();
        assertEquals(0.5, student.getDiscount(), 0.001);
        learner.upgradeDiscount();
        assertEquals(0.52, learner.getDiscount(), 0.001);
        companyagent.upgradeDiscount();
        assertEquals(0.4, companyagent.getDiscount(), 0.001);        
    }
    

    /**
     * Test of getId method, of class Client.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        assertEquals(0, person.getId());
        assertEquals(3, pensioner.getId());
        assertEquals(4, invalid.getId());
        assertEquals(2, student.getId());
        assertEquals(1, learner.getId());
        assertEquals(5, companyagent.getId());
    }

    /**
     * Test of clone method, of class Client.
     */
    @Test
    public void testClone() throws Exception {
        System.out.println("clone");
        Client person1 = person.clone();
        assertEquals(person1.getName(), person.getName());
        assertEquals(person1.getSurname(), person.getSurname());
        assertEquals(person1.getAge(), person.getAge());
        assertEquals(person1.getPESEL(), person.getPESEL());
        assertEquals(person1.getId(), person.getId());
        Client pensioner1 = pensioner.clone();
        assertEquals(pensioner1.getName(), pensioner.getName());
        assertEquals(pensioner1.getSurname(), pensioner.getSurname());
        assertEquals(pensioner1.getAge(), pensioner.getAge());
        assertEquals(pensioner1.getPESEL(), pensioner.getPESEL());
        assertEquals(pensioner1.getId(), pensioner.getId());
        Client invalid1 = invalid.clone();
        assertEquals(invalid1.getName(), invalid.getName());
        assertEquals(invalid1.getSurname(), invalid.getSurname());
        assertEquals(invalid1.getAge(), invalid.getAge());
        assertEquals(invalid1.getPESEL(), invalid.getPESEL());
        assertEquals(invalid1.getId(), invalid.getId());
        Client student1 = student.clone();
        assertEquals(student1.getName(), student.getName());
        assertEquals(student1.getSurname(), student.getSurname());
        assertEquals(student1.getAge(), student.getAge());
        assertEquals(student1.getPESEL(), student.getPESEL());
        assertEquals(student1.getId(), student.getId());
        Client learner1 = learner.clone();
        assertEquals(learner1.getName(), learner.getName());
        assertEquals(learner1.getSurname(), learner.getSurname());
        assertEquals(learner1.getAge(), learner.getAge());
        assertEquals(learner1.getPESEL(), learner.getPESEL());
        assertEquals(learner1.getId(), learner.getId());
        Client companyagent1 = companyagent.clone();
        assertEquals(companyagent1.getName(), companyagent.getName());
        assertEquals(companyagent1.getSurname(), companyagent.getSurname());
        assertEquals(companyagent1.getAge(), companyagent.getAge());
        assertEquals(companyagent1.getPESEL(), companyagent.getPESEL());
        assertEquals(companyagent1.getId(), companyagent.getId());
    }

}
