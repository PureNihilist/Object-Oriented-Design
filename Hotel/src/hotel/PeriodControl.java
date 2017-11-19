package hotel;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author Mateusz Galas
 */
public class PeriodControl implements PeriodInterface{
    LocalDate begin;
    LocalDate end;
    Period p;
    
    PeriodControl (LocalDate begin, LocalDate end) throws Exception{
        this.end = null;
        this.begin = begin;
        if(end.isAfter(begin)) {
            this.end = end;
        } else {
            throw new Exception("Period exception. Beginning of reservation is after end.");
        }
        this.p = Period.between(begin, end);
    }
    
    public void setBegin(LocalDate new_begin) {
        this.begin = new_begin;
    }
    
    public void setEnd(LocalDate new_end) {
        this.end = new_end;
    }
    
    @Override
    public Period getPeriod() {
        return this.p;
    }
    //Jeśli potrzebujesz dokładnych danych to robi się to tak:
    /* gdzie p to obiekt Period
    long p2 = ChronoUnit.DAYS.between(birthday, today);
    System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                   " months, and " + p.getDays() +
                   " days old. (" + p2 + " days total)");
    */
}
