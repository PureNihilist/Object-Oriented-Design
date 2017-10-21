package Singleton;
import java.io.*;
import java.util.*;
/**
 *
 * @author Mateusz Galas
 * Ticket - Singleton pattern
 * Additionally preventing breaking singleton one instance rule with 
 * reflection, serialization, multi-threads, cloning 
 */
public class Ticket implements Serializable, Cloneable {
    private static volatile Ticket ticket = null; // prevent breaking double-check with volatile
 //   private Passanger passanger;
    private static List<Passanger> passangers;
    
    private Ticket() {
    //Prevent reflection -> changing constructor with constuctor.setAccessible(true)
        if(ticket != null) { 
            throw new RuntimeException("Cannot create instance of singleton. Use getInstance().");
        }
    }
  
    public static Ticket getInstance() {
        //Thread safe instance creation using double-check locking 
        if(ticket == null) {
            synchronized(Ticket.class) {
                if(ticket == null) {
                    ticket = new Ticket();
                    passangers = Collections.synchronizedList(new ArrayList<>()); //If we want to be thread safe "synchronizedList" synchronizes whole collection not every element (unlike Vector)
                }
            }
        }
        return ticket;
    }
    
    public Passanger createPassanger(String name, String surname, String date, int flightNumber) {
        for(Passanger p : passangers) {
            if(p.getName().equals(name) && p.getSurname().equals(surname) && p.getDate().equals(date) && p.getFlightNumber() == flightNumber){
                System.err.println("There is already passanger "+name +" "+surname+" on flight " + flightNumber +" taking place at " + date);
                return null;
            }
        }
        Passanger passanger = new Passanger(name,surname,date,flightNumber);
        passangers.add(passanger);
        return passanger;
    }
    
    //Prevent creating new instance with serialization/deserialization
    private Object readResolve() throws ObjectStreamException { 
        return ticket;
    } 
    
    //Prevent cloning instance
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


class Passanger {
      private String name;
      private String surname;
      private String date;
      private int flightNumber;
  
      Passanger(String name, String surname, String date, int flightNumber){
          this.name = name;
          this.surname = surname;
          this.date = date;
          this.flightNumber = flightNumber;
      }
      
      String getName() {
          return this.name;
      }
      String getSurname() {
          return this.surname;
      }
      String getDate() {
          return this.date;
      }
      Integer getFlightNumber() {
          return this.flightNumber;
      }
}
