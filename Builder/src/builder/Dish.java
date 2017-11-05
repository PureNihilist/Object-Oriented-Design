package builder;

/**
 *
 * @author Mateusz Galas
 */
public class Dish {
    private String name = "";
    private String describe = "";
    
    public String getName() {
        return name;
    }
    public String getDescribe() {
        return describe;
    }
    public void setName(String new_name) {
        name = new_name;
    }
    public void setOpis(String new_describe) {
        describe = new_describe;
    }
}
