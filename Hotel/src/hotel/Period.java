package hotel;

/**
 *
 * @author Mateusz Galas
 * Period to interfejs zarzadzajacy okresem rezerwacji od - do
 */
public class Period {
    private Date begin;
    private Date end;

    public Period(Date Begin, Date End){
        this.begin = Begin;
        this.end = End;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getBegin() {
        return begin;
    }

    public Date getEnd() {
        return end;
    }

    public long getLengthOfReservation(){
        long diff = Math.abs(this.end.getTime() - this.begin.getTime());
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays;
    }
}
