package Singleton;

/**
 *
 * @author Mateusz Galas
 */
public class Tester {
    static void print(String name, Object object) {
        System.out.println(String.format("Object : %s, Hashcode: %d",name,object.hashCode()));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*
        Singleton obj = Singleton.getInstance();
        Singleton obj2 = Singleton.getInstance();
        
        print("obj",obj);
        print("obj2",obj2);
        
        if(obj.equals(obj2)) {
            System.out.println("to samo");
        }
        */
        
        Ticket t = Ticket.getInstance();
        t.createPassanger("Mateusz", "Galas", "21-10-2017",45);
        t.createPassanger("Mateusz", "Galas", "21-10-2017",45); //outputs on standard error information that it cannot be created again
        t.createPassanger("Agata","Leśniak","21-10-2017",45);
        Ticket t2 = Ticket.getInstance();
        print("t",t);
        print("t2",t2);
        if(t == t2) {
            System.out.println("same object");
        }
    }
    
}
