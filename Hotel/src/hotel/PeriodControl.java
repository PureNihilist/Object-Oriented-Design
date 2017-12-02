package hotel;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class PeriodControl, which represents periods. Implements methods from interface PeriodInterface.
 * @author Hubert Banaś and Mateusz Galas
 */
public class PeriodControl implements PeriodInterface{
    private LocalDate begin;
    private LocalDate end;
    private Period p;
    private double rabate = 1.0;
    
    /**
     * Constructor of class PeriodControl, creates object if begin is before end
     * @param begin
     * @param end
     * @throws Exception 
     */
    PeriodControl (LocalDate begin, LocalDate end) throws Exception{
        this.begin = begin;
        if(end.isAfter(begin)) {
            this.end = end;
        } else {
            throw new Exception("Początek jest później niż koniec okresu.");
        }
        this.p = Period.between(begin, end);
    }
    
    /**
     * setRabate method
     * @param newRabate
     */
    public void setRabate(double newRabate){
        rabate = newRabate;
    }
    
    /**
     * getRabate method
     * @return period rabate
     */
    public double getRabate() {
        return rabate;
    }

    /**
     * getBegin method
     * @return Begin date of period
     */
    public LocalDate getBegin() {
        return begin;
    }

    /**
     * getEnd method
     * @return End date of period
     */
    public LocalDate getEnd() {
        return end;
    }
    
    /**
     * setBegin method
     * @param new_begin
     */
    public void setBegin(LocalDate new_begin) {
        this.begin = new_begin;
    }
    
    /**
     * setEnd method
     * @param new_end
     */
    public void setEnd(LocalDate new_end) {
        this.end = new_end;
    }
    
    /**
     * getPeriod method
     * @return period
     */
    @Override
    public Period getPeriod() {
        return this.p;
    }

    /**
     * checks if periods are overlaped
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return true if periods are overlaped, else false
     */

    public static boolean isOverLaped(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2){
        return (start1.isBefore(start2) && start2.isBefore(end1) && end1.isBefore(end2)) || //czesc wspólna
                (start2.isBefore(start1) && start1.isBefore(end2) && end2.isBefore(end1)) || //czesc wspolna odwrotnie
                (start1.isBefore(start2) && end2.isBefore(end1)) || // zawieranie
                (start2.isBefore(start1) && end1.isBefore(end2)) || //zawieranie w druga strone
                (start1.isBefore(start2) && end1.isEqual(start2)) || //wspolny koniec
                (start2.isBefore(start1) && end2.isEqual(start1)) || //wspolny koniec odwrotnie
                (start1.isEqual(start2) && end2.isBefore(end1)) || // wspolny poczatek
                (start1.isEqual(start2) && end1.isBefore(end2)) || // wspolny poczatek odwrotnie
                (start1.isEqual(start2) && end1.isEqual(end2)); //rowne przedzialy
    } 
}

    //Jeśli potrzebujesz dokładnych danych to robi się to tak:
    /* gdzie p to obiekt Period
    long p2 = ChronoUnit.DAYS.between(birthday, today);
    System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                   " months, and " + p.getDays() +
                   " days old. (" + p2 + " days total)");
    */

