/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.FixMethodOrder;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;
/**
 *
 * @author Hubert Banaś and Mateusz Galas
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelAdministratorTest {
    
    HotelAdministrator hoteladmin;
    Reader reader;
    ClientCache clientcache;
    
    /**
     *
     */
    public HotelAdministratorTest() {
        hoteladmin = new HotelAdministrator();
        reader = Reader.getInstance();
        clientcache = ClientCache.getInstance();
    }


    /**
     * Test of loadRooms and getRooms method, of class HotelAdministrator.
     */
    @Test
    public void test1LoadAndGetRooms() {
        System.out.println("loadRooms");
        hoteladmin.loadRooms(reader);
        List<Room> lista = hoteladmin.getRooms();
        assertEquals("1", lista.get(0).getName());
        assertEquals(1, lista.get(0).getCapacity());
        assertEquals(5, lista.get(0).getQuality());
        assertEquals(5, lista.get(0).getPrice(), 0);
        assertEquals("2", lista.get(1).getName());
        assertEquals(1, lista.get(1).getCapacity());
        assertEquals(4, lista.get(1).getQuality());
        assertEquals(4, lista.get(1).getPrice(), 0);
        assertEquals("3", lista.get(2).getName());
        assertEquals(3, lista.get(2).getCapacity());
        assertEquals(5, lista.get(2).getQuality());
        assertEquals(15, lista.get(2).getPrice(), 0);
        assertEquals("4", lista.get(3).getName());
        assertEquals(2, lista.get(3).getCapacity());
        assertEquals(4, lista.get(3).getQuality());
        assertEquals(8, lista.get(3).getPrice(), 0);
        assertEquals("5", lista.get(4).getName());
        assertEquals(2, lista.get(4).getCapacity());
        assertEquals(4, lista.get(4).getQuality());
        assertEquals(8, lista.get(4).getPrice(), 0);
    }

        /**
     * Test of loadReservations and getReservationsmethod, of class HotelAdministrator.
     */
    @Test
    public void test2LoadAndGetReservations() {
        System.out.println("loadReservations");
        hoteladmin.loadReservations(reader);
        List<ReservationInstance> lista = hoteladmin.getReservations();
        assertEquals(1L, lista.get(0).getId());
        assertEquals(2L, lista.get(1).getId());
        assertEquals(3L, lista.get(2).getId());
        assertEquals("Hubert", lista.get(0).getClient().getName());
        assertEquals("Adam", lista.get(1).getClient().getName());
        assertEquals("Robert", lista.get(2).getClient().getName());
        assertEquals("Banaś", lista.get(0).getClient().getSurname());
        assertEquals("Kowalski", lista.get(1).getClient().getSurname());
        assertEquals("Wanad", lista.get(2).getClient().getSurname());
        assertEquals(23, lista.get(0).getClient().getAge());
        assertEquals(45, lista.get(1).getClient().getAge());
        assertEquals(46, lista.get(2).getClient().getAge());
        assertEquals(94072112075L, lista.get(0).getClient().getPESEL());
        assertEquals(72342363879L, lista.get(1).getClient().getPESEL());
        assertEquals(71010234256L, lista.get(2).getClient().getPESEL());
        assertThat(lista.get(0).getClient(), instanceOf(Student.class));
        assertThat(lista.get(1).getClient(), instanceOf(Person.class));
        assertEquals(Period.between(LocalDate.of(2018, 5, 23), LocalDate.of(2018, 5, 29)), lista.get(0).getPeriodControl().getPeriod());
        assertEquals(Period.between(LocalDate.of(2018, 6, 13), LocalDate.of(2018, 6, 15)), lista.get(1).getPeriodControl().getPeriod());
        assertEquals(Period.between(LocalDate.of(2018, 5, 23), LocalDate.of(2018, 5, 29)), lista.get(2).getPeriodControl().getPeriod());
        assertEquals("3", lista.get(0).getRoomsInfo().get(0).getName());
        assertEquals(3, lista.get(0).getRoomsInfo().get(0).getCapacity());
        assertEquals(5, lista.get(0).getRoomsInfo().get(0).getQuality());
        assertEquals(15, lista.get(0).getRoomsInfo().get(0).getPrice(), 0);
        assertEquals("8", lista.get(1).getRoomsInfo().get(0).getName());
        assertEquals(3, lista.get(1).getRoomsInfo().get(0).getCapacity());
        assertEquals(3, lista.get(1).getRoomsInfo().get(0).getQuality());
        assertEquals(9, lista.get(1).getRoomsInfo().get(0).getPrice(), 0);
        assertEquals("9", lista.get(2).getRoomsInfo().get(0).getName());
        assertEquals(2, lista.get(2).getRoomsInfo().get(0).getCapacity());
        assertEquals(5, lista.get(2).getRoomsInfo().get(0).getQuality());
        assertEquals(10, lista.get(2).getRoomsInfo().get(0).getPrice(), 0);
    }
    
    /**
     * Test of loadSeasons and getSeasonsmethod, of class HotelAdministrator.
     * @throws java.lang.Exception
     */
    @Test
    public void test3LoadAndGetSeasons() throws Exception {
        System.out.println("loadSeasons");
        hoteladmin.loadSeasons(reader);
        List<Double> lista_doubli = new ArrayList<>();
        List<PeriodControl> lista_periodow = new ArrayList<>();
        ArrayList<PeriodControl> lista = hoteladmin.getSeasons();
       
        for(PeriodControl control : lista) {
            lista_periodow.add(control);
            lista_doubli.add(control.getRabate());
        }
        assertEquals(1.2, lista_doubli.get(0), 0);
        assertEquals(1.15, lista_doubli.get(1), 0);
        assertEquals(1.2, lista_doubli.get(2), 0);
        assertEquals(1.15, lista_doubli.get(3), 0);
        assertEquals(1.2, lista_doubli.get(4), 0);
        assertEquals(1.15, lista_doubli.get(5), 0);
        assertEquals(1.2, lista_doubli.get(6), 0);
        assertEquals(1.15, lista_doubli.get(7), 0);
       
        assertEquals(Period.between(LocalDate.of(2017, 12, 20), LocalDate.of(2018, 1, 3)), lista_periodow.get(0).getPeriod());
        assertEquals(Period.between(LocalDate.of(2021, 7, 1), LocalDate.of(2021, 9, 1)), lista_periodow.get(1).getPeriod());
    }
    
    /**
     * Test of addRoom method, of class HotelAdministrator.
     */
    @Test
    public void test4AddRoom() {
        System.out.println("addRoom");
        hoteladmin.loadRooms(reader);
        hoteladmin.addRoom("150", 3, 4);
        assertEquals("150", hoteladmin.getRooms().get(12).getName());
        assertEquals(3, hoteladmin.getRooms().get(12).getCapacity());
        assertEquals(4, hoteladmin.getRooms().get(12).getQuality());
        assertEquals(12, hoteladmin.getRooms().get(12).getPrice(), 0);
    }

    /**
     * Test of findReservation method, of class HotelAdministrator.
     */
    @Test
    public void test5FindReservation() {
        System.out.println("findReservation");
        hoteladmin.loadReservations(reader);
        hoteladmin.findReservation(94072112075L);
        System.out.println("Lista Rezerwacji na górze nie powinna być pusta");
        hoteladmin.findReservation(64072314532L);
        System.out.println("Lista Rezerwacji na górze powinna być pusta");
    }

    /**
     * Test of deleteRoom method, of class HotelAdministrator.
     */
    @Test
    public void test6DeleteRoom() {
        System.out.println("deleteRoom");
        hoteladmin.loadRooms(reader);
        hoteladmin.deleteRoom("2");
        assertEquals("3", hoteladmin.getRooms().get(1).getName());
        assertEquals(3, hoteladmin.getRooms().get(1).getCapacity());
        assertEquals(5, hoteladmin.getRooms().get(1).getQuality());
        assertEquals(15, hoteladmin.getRooms().get(1).getPrice(), 0);
    }

    /**
     * Test of deleteClient method, of class HotelAdministrator.
     */
    @Test
    public void test7DeleteClient() {
        System.out.println("deleteClient");
        hoteladmin.loadReservations(reader);
        hoteladmin.deleteClient(94072112075L);
        assertNotEquals(94072112075L, hoteladmin.getReservations().get(0).getClient().getPESEL());
    }

    /**
     * Test of findFreeRooms method, of class HotelAdministrator.
     * @throws java.lang.Exception
     */
    @Test
    public void test8FindFreeRooms() throws Exception {
        System.out.println("findFreeRooms");
        hoteladmin.loadReservations(reader);
        hoteladmin.loadRooms(reader);
        List<Room> requested_rooms = new ArrayList<>();
        Room e = new Room(2, 4);
        requested_rooms.add(e);
        List<Room> free_rooms = hoteladmin.findFreeRooms(new PeriodControl(LocalDate.of(2020, 3, 4), LocalDate.of(2020, 3, 7)), requested_rooms);
        List<Room> rooms = new ArrayList<>();
        Room e1 = new Room("4", 2, 4);
        Room e2 = new Room("5", 2, 4);
        rooms.add(e1);
        rooms.add(e2);
        assertEquals(free_rooms.get(0).getName(), rooms.get(0).getName());
        assertEquals(free_rooms.get(0).getCapacity(), rooms.get(0).getCapacity());
        assertEquals(free_rooms.get(0).getQuality(), rooms.get(0).getQuality());
        assertEquals(free_rooms.get(0).getPrice(), rooms.get(0).getPrice(), 0);
        assertEquals(free_rooms.get(1).getName(), rooms.get(1).getName());
        assertEquals(free_rooms.get(1).getCapacity(), rooms.get(1).getCapacity());
        assertEquals(free_rooms.get(1).getQuality(), rooms.get(1).getQuality());
        assertEquals(free_rooms.get(1).getPrice(), rooms.get(1).getPrice(), 0);
    }

    /**
     * Test of deleteReservation method, of class HotelAdministrator.
     */
    @Test
    public void test9DeleteReservation() {
        System.out.println("deleteReservation");
        hoteladmin.loadReservations(reader);
        hoteladmin.deleteReservation(1L);
        assertNotEquals(1L, hoteladmin.getReservations().get(0).getId());
    }
    
}
