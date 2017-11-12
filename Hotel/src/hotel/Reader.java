package hotel;

import java.io.File;

/**
 *
 * @author Mateusz Galas
 * Czytanie plików CSV 
 */
public class Reader {
    
    public volatile static Reader reader = null;
    
    private Reader() {
        if(reader != null) { 
            throw new RuntimeException("Cannot create instance of singleton. Use getInstance().");
        }
    }
    
    public static Reader getInstance() {
        if(reader == null) {
            synchronized(Reader.class) {
                if(reader == null) {
                    reader = new Reader();
                }
            }
        }
        return reader;
    }
    
    public void read(File fileName){
        
    }
    
}
