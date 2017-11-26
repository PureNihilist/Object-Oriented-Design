package hotel;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mateusz Galas
 */
public class PeriodControl implements PeriodInterface{
    private LocalDate begin;
    private LocalDate end;
    private Period p;
    private static List<PeriodControl> seasons;
    
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

    public LocalDate getBegin() {
        return begin;
    }

    public LocalDate getEnd() {
        return end;
    }
    
    public List<PeriodControl> getSeasons(){
        return seasons;
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
    /*
    Niestety ta metoda chyba musi tak wyglądać bo... 
    isBefore i isAfter nie biorą pod uwage przypadków kiedy daty sa rowne... trzeba do tego użyć isEqual.. 
    stad dodatkowe 5 przypadkow
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
        //przedzialy sa rozlaczne
    } 
    
    
    public static void loadSeasons() throws Exception{
        seasons = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++) {
            LocalDate seasonVacationBegin = LocalDate.of(2017+i, Month.JULY, 1);
            LocalDate seasonVacationEnd = LocalDate.of(2017+i, Month.SEPTEMBER, 1);
            PeriodControl vacation = new PeriodControl(seasonVacationBegin,seasonVacationEnd);
            seasons.add(vacation);
            LocalDate seasonWinterBegin = LocalDate.of(2017+i, Month.DECEMBER, 20);
            LocalDate seasonWinterEnd = LocalDate.of(2018+i, Month.JANUARY, 3);
            PeriodControl winter = new PeriodControl(seasonWinterBegin, seasonWinterEnd);
            seasons.add(winter);
        }
    }
    
    public boolean isSeason(PeriodControl p){
        LocalDate p_begin = p.getBegin();
        LocalDate p_end = p.getEnd();
        for(PeriodControl control : seasons) {
            LocalDate control_begin = control.getBegin();
            LocalDate control_end = control.getEnd();
            if(PeriodControl.isOverLaped(p_begin,p_end,control_begin,control_end)){
                return true;
            }
        }
        return false;
    }
    
    public void addEvent(PeriodControl p){
        if(seasons != null) {
            this.seasons.add(p);
        }
    }
}

    //Jeśli potrzebujesz dokładnych danych to robi się to tak:
    /* gdzie p to obiekt Period
    long p2 = ChronoUnit.DAYS.between(birthday, today);
    System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                   " months, and " + p.getDays() +
                   " days old. (" + p2 + " days total)");
    */

