package Singleton;

/**
 *
 * @author Mateusz Galas 
 * Plain singleton pattern (without any prevention of breaking it)
 */
public class Singleton {
    private final static Singleton SINGLETON = new Singleton();
    
    private Singleton(){
        
    }
    
    public static Singleton getInstance() {
        return SINGLETON;
    }
    
    protected static void write() {
        System.out.println("something");
    }
}
