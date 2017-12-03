package hotel;

/**
 *  Client class, using pattern prototype for clients of our system
 * @author Hubert Banaś and Mateusz Galas
 * 
 */
public abstract class Client implements Cloneable {
    protected String Name;
    protected String Surname;
    protected int age;
    protected long PESEL;
    protected double discount;
    protected int id; //typ klienta od 0 do 5

    /**
     * getName method
     * @return Client Name
     */
    
    public String getName() {
        return Name;
    }

    /**
     * getSurname method
     * @return Client Surname
     */
    public String getSurname() {
        return Surname;
    }
    /**
     * getAge method
     * @return Client Age
     */
    public int getAge() {
        return age;
    }
    /**
     * getPESEL method
     * @return Client PESEL
     */
    public long getPESEL() {
        return PESEL;
    }
    /**
     * getDiscount method
     * @return Client discount
     */
    public double getDiscount() {
        return discount;
    }
    /**
     * getId method
     * @return Client ID
     */
    public int getId(){
      return id;
    }
    
    /**
     * upgradeDiscount method, to increase client discount
     */
    public void upgradeDiscount(){
        this.discount = this.getDiscount()+0.05;
    }
        
    /**
     * Clone method
     * @return cloned object of client
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
      Object clone = null;
      
      try {
        clone = super.clone(); 
      } catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }
      
      return clone;
    }
}
