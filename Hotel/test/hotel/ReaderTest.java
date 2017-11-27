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
import static org.hamcrest.CoreMatchers.instanceOf;
import java.util.HashMap;

/**
 *
 * @author huber
 */
public class ReaderTest {
    
    public ReaderTest() {
    }

    /**
     * Test of getInstance method, of class Reader.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Reader result = Reader.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of readRoomsCSV method, of class Reader.
     */
    @Test
    public void testReadRoomsCSV() {
        System.out.println("readRoomsCSV");
        Reader reader = Reader.getInstance();
        List<Room> lista = reader.readRoomsCSV("RoomsTest.csv");
        assertEquals("1", lista.get(0).getName());
        assertEquals(3, lista.get(0).getCapacity());
        assertEquals(4, lista.get(0).getQuality());
        assertEquals(12, lista.get(0).getPrice(), 0);
        assertEquals("2", lista.get(1).getName());
        assertEquals(2, lista.get(1).getCapacity());
        assertEquals(2, lista.get(1).getQuality());
        assertEquals(4, lista.get(1).getPrice(), 0);
        assertEquals("3", lista.get(2).getName());
        assertEquals(1, lista.get(2).getCapacity());
        assertEquals(3, lista.get(2).getQuality());
        assertEquals(3, lista.get(2).getPrice(), 0);
        assertEquals("4", lista.get(3).getName());
        assertEquals(2, lista.get(3).getCapacity());
        assertEquals(5, lista.get(3).getQuality());
        assertEquals(10, lista.get(3).getPrice(), 0);
        assertEquals("5", lista.get(4).getName());
        assertEquals(1, lista.get(4).getCapacity());
        assertEquals(5, lista.get(4).getQuality());
        assertEquals(5, lista.get(4).getPrice(), 0);
    }

    /**
     * Test of readReservationCSV method, of class Reader.
     */
    @Test
    public void testReadReservationCSV() {
        System.out.println("readReservationCSV");
        Reader reader = Reader.getInstance();
        List<ReservationInstance> lista = reader.readReservationCSV("ReservationsTest.csv");
        assertEquals(1L, lista.get(0).getId());
        assertEquals(2L, lista.get(1).getId());
        assertEquals(3L, lista.get(2).getId());
        assertEquals("Zofia", lista.get(0).getClient().getName());
        assertEquals("Marek", lista.get(1).getClient().getName());
        assertEquals("Danuta", lista.get(2).getClient().getName());
        assertEquals("Wiśniewska", lista.get(0).getClient().getSurname());
        assertEquals("Wilk", lista.get(1).getClient().getSurname());
        assertEquals("Prociak", lista.get(2).getClient().getSurname());
        assertEquals(44, lista.get(0).getClient().getAge());
        assertEquals(28, lista.get(1).getClient().getAge());
        assertEquals(35, lista.get(2).getClient().getAge());
        assertEquals(73060743587L, lista.get(0).getClient().getPESEL());
        assertEquals(89072112075L, lista.get(1).getClient().getPESEL());
        assertEquals(82101288756L, lista.get(2).getClient().getPESEL());
        assertEquals("Wiśniewska", lista.get(0).getClient().getSurname());
        assertEquals("Wilk", lista.get(1).getClient().getSurname());
        assertEquals("Prociak", lista.get(2).getClient().getSurname());
        assertThat(lista.get(0).getClient(), instanceOf(Person.class));
        assertThat(lista.get(1).getClient(), instanceOf(Invalid.class));
        assertThat(lista.get(2).getClient(), instanceOf(Person.class));
        assertEquals(Period.between(LocalDate.of(2018, 2, 12), LocalDate.of(2018, 2, 16)), lista.get(0).getPeriodControl().getPeriod());
        assertEquals(Period.between(LocalDate.of(2018, 3, 10), LocalDate.of(2018, 3, 15)), lista.get(1).getPeriodControl().getPeriod());
        assertEquals(Period.between(LocalDate.of(2018, 7, 23), LocalDate.of(2018, 7, 30)), lista.get(2).getPeriodControl().getPeriod());
        assertEquals("6", lista.get(0).getRoomsInfo().get(0).getName());
        assertEquals(2, lista.get(0).getRoomsInfo().get(0).getCapacity());
        assertEquals(3, lista.get(0).getRoomsInfo().get(0).getQuality());
        assertEquals(6, lista.get(0).getRoomsInfo().get(0).getPrice(), 0);
        assertEquals("7", lista.get(1).getRoomsInfo().get(0).getName());
        assertEquals(1, lista.get(1).getRoomsInfo().get(0).getCapacity());
        assertEquals(4, lista.get(1).getRoomsInfo().get(0).getQuality());
        assertEquals(4, lista.get(1).getRoomsInfo().get(0).getPrice(), 0);
        assertEquals("6", lista.get(1).getRoomsInfo().get(1).getName());
        assertEquals(2, lista.get(1).getRoomsInfo().get(1).getCapacity());
        assertEquals(3, lista.get(1).getRoomsInfo().get(1).getQuality());
        assertEquals(6, lista.get(1).getRoomsInfo().get(1).getPrice(), 0);
        assertEquals("6", lista.get(2).getRoomsInfo().get(0).getName());
        assertEquals(2, lista.get(2).getRoomsInfo().get(0).getCapacity());
        assertEquals(3, lista.get(2).getRoomsInfo().get(0).getQuality());
        assertEquals(6, lista.get(2).getRoomsInfo().get(0).getPrice(), 0);
    }
    
    /**
     * Test of readSeasonsCSV method, of class Reader.
     */
    @Test
    public void testReadSeasonsCSV() {
    System.out.println("readSeasonsCSV");
    Reader reader = Reader.getInstance();
    
    List<Double> lista_doubli = new ArrayList<>();
    List<PeriodControl> lista_periodow = new ArrayList<>();
    HashMap<PeriodControl, Double> lista = reader.readSeasonsCSV("Seasons.csv");
        for(double val : lista.values()) {
            lista_doubli.add(val);
        }
        
        assertEquals(1.15, lista_doubli.get(7),0);
        assertEquals(1.15, lista_doubli.get(6), 0);
        assertEquals(1.2, lista_doubli.get(5), 0);
        assertEquals(1.15, lista_doubli.get(4), 0);
        assertEquals(1.2, lista_doubli.get(3), 0);
        assertEquals(1.2, lista_doubli.get(2), 0);
        assertEquals(1.2, lista_doubli.get(1), 0);
        assertEquals(1.15, lista_doubli.get(0), 0);
        
        for(PeriodControl aKey : lista.keySet()) {
            lista_periodow.add(aKey);
        }
        assertEquals(Period.between(LocalDate.of(2017, 12, 20), LocalDate.of(2018, 1, 3)), lista_periodow.get(1).getPeriod());
        assertEquals(Period.between(LocalDate.of(2021, 7, 1), LocalDate.of(2021, 9, 1)), lista_periodow.get(0).getPeriod());
    }
    
}
