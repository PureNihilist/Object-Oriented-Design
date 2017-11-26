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
public class RoomTest {
    
    Room room1;
    Room room2;
    Room room3;
    Room room4;
    Room room5;
    
    public RoomTest() {
        room1 = new Room("1", 4, 3);
        room2 = new Room("2", 5, 5);
        room3 = new Room("3", 3, 2);
        room4 = new Room("4", 2, 4);
        room5 = new Room("5", 1, 1);
    }

    /**
     * Test of getCapacity method, of class Room.
     */
    @Test
    public void testGetCapacity() {
        System.out.println("getCapacity");
        assertEquals(4, room1.getCapacity());
        assertEquals(5, room2.getCapacity());
        assertEquals(3, room3.getCapacity());
        assertEquals(2, room4.getCapacity());
        assertEquals(1, room5.getCapacity());
    }

    /**
     * Test of getName method, of class Room.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        assertEquals("1", room1.getName());
        assertEquals("2", room2.getName());
        assertEquals("3", room3.getName());
        assertEquals("4", room4.getName());
        assertEquals("5", room5.getName());
    }

    /**
     * Test of getQuality method, of class Room.
     */
    @Test
    public void testGetQuality() {
        System.out.println("getQuality");
        assertEquals(3, room1.getQuality());
        assertEquals(5, room2.getQuality());
        assertEquals(2, room3.getQuality());
        assertEquals(4, room4.getQuality());
        assertEquals(1, room5.getQuality());
    }

    /**
     * Test of getPrice method, of class Room.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        assertEquals(12, room1.getPrice(), 0);
        assertEquals(25, room2.getPrice(), 0);
        assertEquals(6, room3.getPrice(), 0);
        assertEquals(8, room4.getPrice(), 0);
        assertEquals(1, room5.getPrice(), 0);
    }
    
}
