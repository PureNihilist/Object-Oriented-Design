/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
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
 * @author huber
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PeriodControlTest {
    
    PeriodControl period1;
    PeriodControl period2;
    PeriodControl period3;
    PeriodControl period4;
    PeriodControl period5;
    PeriodControl period_set;
    
    
    public PeriodControlTest() throws Exception {
        period1 = new PeriodControl(LocalDate.of(2017, 12, 12), LocalDate.of(2017, 12, 18));
        period2 = new PeriodControl(LocalDate.of(2018, 1, 15), LocalDate.of(2018, 1, 17));
        period3 = new PeriodControl(LocalDate.of(2018, 2, 1), LocalDate.of(2018, 2, 5));
        period4 = new PeriodControl(LocalDate.of(2017, 12, 1), LocalDate.of(2017, 12, 8));
        period5 = new PeriodControl(LocalDate.of(2018, 4, 12), LocalDate.of(2018, 4, 18));
        period_set = new PeriodControl(LocalDate.of(2018, 4, 12), LocalDate.of(2018, 4, 18));
    }
    
    /**
     * Test of getBegin method, of class PeriodControl.
     */
    @Test
    public void testGetBegin() {
        System.out.println("getBegin");
        assertEquals(LocalDate.of(2017, 12, 12), period1.getBegin());
        assertEquals(LocalDate.of(2018, 1, 15), period2.getBegin());
        assertEquals(LocalDate.of(2018, 2, 1), period3.getBegin());
        assertEquals(LocalDate.of(2017, 12, 1), period4.getBegin());
        assertEquals(LocalDate.of(2018, 4, 12), period5.getBegin());
    }

    /**
     * Test of getEnd method, of class PeriodControl.
     */
    @Test
    public void testGetEnd() {
        System.out.println("getEnd");
        assertEquals(LocalDate.of(2017, 12, 18), period1.getEnd());
        assertEquals(LocalDate.of(2018, 1, 17), period2.getEnd());
        assertEquals(LocalDate.of(2018, 2, 5), period3.getEnd());
        assertEquals(LocalDate.of(2017, 12, 8), period4.getEnd());
        assertEquals(LocalDate.of(2018, 4, 18), period5.getEnd());
    }

    /**
     * Test of setBegin method, of class PeriodControl.
     */
    @Test
    public void testSetBegin() {
        System.out.println("setBegin");
        period_set.setBegin(LocalDate.of(2017, 12, 5));
        assertEquals(LocalDate.of(2017, 12, 5), period_set.getBegin());
    }

    /**
     * Test of setEnd method, of class PeriodControl.
     */
    @Test
    public void testSetEnd() {
        System.out.println("setEnd");
        period_set.setEnd(LocalDate.of(2017, 12, 29));
        assertEquals(LocalDate.of(2017, 12, 29), period_set.getEnd());
    }

    /**
     * Test of getPeriod method, of class PeriodControl.
     */
    @Test
    public void testGetPeriod() {
        System.out.println("getPeriod");
        assertEquals(Period.between(period1.getBegin(), period1.getEnd()), period1.getPeriod());
        assertEquals(Period.between(period2.getBegin(), period2.getEnd()), period2.getPeriod());
        assertEquals(Period.between(period3.getBegin(), period3.getEnd()), period3.getPeriod());
        assertEquals(Period.between(period4.getBegin(), period4.getEnd()), period4.getPeriod());
        assertEquals(Period.between(period5.getBegin(), period5.getEnd()), period5.getPeriod());
    }

    /**
     * Test of isOverLaped method, of class PeriodControl.
     */
    @Test
    public void testIsOverLaped() {
        System.out.println("isOverLaped");
        boolean expResult = false;
        boolean result = PeriodControl.isOverLaped(period1.getBegin(), period1.getEnd(), period2.getBegin(), period2.getEnd());
        boolean result1 = PeriodControl.isOverLaped(period1.getBegin(), period1.getEnd(), period3.getBegin(), period3.getEnd());
        boolean result2 = PeriodControl.isOverLaped(period1.getBegin(), period1.getEnd(), period4.getBegin(), period4.getEnd());
        boolean result3 = PeriodControl.isOverLaped(period1.getBegin(), period1.getEnd(), period5.getBegin(), period5.getEnd());
        boolean result4 = PeriodControl.isOverLaped(period2.getBegin(), period2.getEnd(), period3.getBegin(), period3.getEnd());
        boolean result5 = PeriodControl.isOverLaped(period2.getBegin(), period2.getEnd(), period4.getBegin(), period4.getEnd());
        boolean result6 = PeriodControl.isOverLaped(period2.getBegin(), period2.getEnd(), period5.getBegin(), period5.getEnd());
        boolean result7 = PeriodControl.isOverLaped(period3.getBegin(), period3.getEnd(), period4.getBegin(), period4.getEnd());
        boolean result8 = PeriodControl.isOverLaped(period3.getBegin(), period3.getEnd(), period5.getBegin(), period5.getEnd());
        boolean result9 = PeriodControl.isOverLaped(period4.getBegin(), period4.getEnd(), period5.getBegin(), period5.getEnd());
        assertEquals(expResult, result);
        assertEquals(expResult, result1);
        assertEquals(expResult, result2);
        assertEquals(expResult, result3);
        assertEquals(expResult, result4);
        assertEquals(expResult, result5);
        assertEquals(expResult, result6);
        assertEquals(expResult, result7);
        assertEquals(expResult, result8);
        assertEquals(expResult, result9);
    }
    
    
}
