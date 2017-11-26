/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
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
public class ReservationInstanceTest {
    
    ReservationInstance reservation1;
    ReservationInstance reservation2;
    ReservationInstance reservation3;
    ReservationInstance reservation4;
    ReservationInstance reservation5;
    
    
    public ReservationInstanceTest() throws Exception{
        List<Room> rooms1 = new ArrayList<>();
        rooms1.add(new Room("1", 3, 4));
        rooms1.add(new Room("2", 2, 5));
        rooms1.add(new Room("3", 4, 4));
        List<Room> rooms2 = new ArrayList<>();
        rooms2.add(new Room("1", 3, 4));
        rooms2.add(new Room("3", 4, 4));
        List<Room> rooms3 = new ArrayList<>();
        rooms3.add(new Room("2", 2, 5));
        rooms3.add(new Room("4", 5, 3));
        reservation1 = new ReservationInstance(1L, new Student("Mateusz", "Stachak", 23, 93112987635L), new PeriodControl(LocalDate.of(2017, 12, 3), LocalDate.of(2017, 12, 8)), rooms1);
        reservation2 = new ReservationInstance(2L, new Pensioner("Marcin", "Stachurski", 74, 43012181234L), new PeriodControl(LocalDate.of(2018, 2, 3), LocalDate.of(2018, 2, 8)), rooms2);
        reservation3 = new ReservationInstance(3L, new Invalid("Gryzelda", "Guziec", 44, 73040153819L), new PeriodControl(LocalDate.of(2018, 1, 13), LocalDate.of(2018, 1, 19)), rooms1);
        reservation4 = new ReservationInstance(4L, new Student("Hubert", "Banaś", 23, 94072112075L), new PeriodControl(LocalDate.of(2018, 5, 1), LocalDate.of(2018, 5, 4)), rooms3);
        reservation5 = new ReservationInstance(5L, new Person("Hubert", "Urbański", 51, 66032319403L), new PeriodControl(LocalDate.of(2018, 7, 23), LocalDate.of(2018, 7, 28)), rooms2);
    }

    /**
     * Test of getId method, of class ReservationInstance.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        assertEquals(1L, reservation1.getId());
        assertEquals(2L, reservation2.getId());
        assertEquals(3L, reservation3.getId());
        assertEquals(4L, reservation4.getId());
        assertEquals(5L, reservation5.getId());
    }

    /**
     * Test of getClient method, of class ReservationInstance.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        assertEquals(reservation1.getClient().getDiscount(), reservation4.getClient().getDiscount(), 0.4);
        assertEquals(0, reservation2.getClient().getDiscount(), 0.3);
        assertEquals(0, reservation3.getClient().getDiscount(), 0.55);
        assertEquals(0, reservation5.getClient().getDiscount(), 0);
        assertEquals("Mateusz", reservation1.getClient().getName());
        assertEquals("Marcin", reservation2.getClient().getName());
        assertEquals("Gryzelda", reservation3.getClient().getName());
        assertEquals(reservation5.getClient().getName(), reservation4.getClient().getName());
        assertEquals("Stachak", reservation1.getClient().getSurname());
        assertEquals("Stachurski", reservation2.getClient().getSurname());
        assertEquals("Guziec", reservation3.getClient().getSurname());
        assertEquals("Banaś", reservation4.getClient().getSurname());
        assertEquals("Urbański", reservation5.getClient().getSurname());
        assertEquals(reservation1.getClient().getAge(), reservation4.getClient().getAge());
        assertEquals(74, reservation2.getClient().getAge());
        assertEquals(44, reservation3.getClient().getAge());
        assertEquals(51, reservation5.getClient().getAge());
        assertEquals(93112987635L, reservation1.getClient().getPESEL());
        assertEquals(43012181234L, reservation2.getClient().getPESEL());
        assertEquals(73040153819L, reservation3.getClient().getPESEL());
        assertEquals(94072112075L, reservation4.getClient().getPESEL());
        assertEquals(66032319403L, reservation5.getClient().getPESEL());
    }

    /**
     * Test of getPeriodControl method, of class ReservationInstance.
     */
    @Test
    public void testGetPeriodControl() {
        System.out.println("getPeriodControl");
        assertEquals(Period.between(LocalDate.of(2017, 12, 3), LocalDate.of(2017, 12, 8)), reservation1.getPeriodControl().getPeriod());
        assertEquals(Period.between(LocalDate.of(2018, 2, 3), LocalDate.of(2018, 2, 8)), reservation2.getPeriodControl().getPeriod());
        assertEquals(Period.between(LocalDate.of(2018, 1, 13), LocalDate.of(2018, 1, 19)), reservation3.getPeriodControl().getPeriod());
        assertEquals(Period.between(LocalDate.of(2018, 5, 1), LocalDate.of(2018, 5, 4)), reservation4.getPeriodControl().getPeriod());
        assertEquals(Period.between(LocalDate.of(2018, 7, 23), LocalDate.of(2018, 7, 28)), reservation5.getPeriodControl().getPeriod());
    }

    /**
     * Test of getRoomsInfo method, of class ReservationInstance.
     */
    @Test
    public void testGetRoomsInfo() {
        System.out.println("getRoomsInfo");
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("2", 2, 5));
        rooms.add(new Room("4", 5, 3));
        assertEquals(reservation1.getRoomsInfo(), reservation3.getRoomsInfo());
        assertEquals(reservation2.getRoomsInfo(), reservation5.getRoomsInfo());
        assertEquals(rooms.get(0).getName(), reservation4.getRoomsInfo().get(0).getName());
        assertEquals(rooms.get(0).getCapacity(), reservation4.getRoomsInfo().get(0).getCapacity());
        assertEquals(rooms.get(0).getQuality(), reservation4.getRoomsInfo().get(0).getQuality());
        assertEquals(rooms.get(0).getPrice(), reservation4.getRoomsInfo().get(0).getPrice(), 0);
        assertEquals(rooms.get(1).getName(), reservation4.getRoomsInfo().get(1).getName());
        assertEquals(rooms.get(1).getCapacity(), reservation4.getRoomsInfo().get(1).getCapacity());
        assertEquals(rooms.get(1).getQuality(), reservation4.getRoomsInfo().get(1).getQuality());
        assertEquals(rooms.get(1).getPrice(), reservation4.getRoomsInfo().get(1).getPrice(), 0);
    }
    
}
