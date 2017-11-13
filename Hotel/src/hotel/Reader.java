package hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mateusz Galas
 * Reader class reads CVS files. Uses singleton design pattern.
 */
public class Reader {
    
    public volatile static Reader reader = null;
    
    private Reader() {
        if(reader != null) { 
            throw new RuntimeException("Cannot create instance of singleton. Use getInstance().");
        }
    }
    
    public static Reader getInstance() {
        Reader readCSV = Reader.reader;
        if(readCSV == null) {
            synchronized(Reader.class) {
                readCSV = Reader.reader;
                if(readCSV == null) {
                    Reader.reader = readCSV = new Reader();
                }
            }
        }
        return readCSV;
    }
    
    public List<Room> readRoomsCSV(String fileName) {
        List<Room> rooms = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line;
            while((line = br.readLine()) != null) {
                String [] attributes = line.split(";");
                String roomName = attributes[0];
                int roomCapacity = Integer.valueOf(attributes[1]);
                int roomFill = Integer.valueOf(attributes[2]);
                Room room = new Room(roomName,roomCapacity,roomFill);
                rooms.add(room);
            }
        } catch(IOException ex) {
            ex.printStackTrace();            
        }
        return rooms;
    }
    
    public List<Reservation> readReservationCSV(String fileName) {
        List<Reservation> reservations = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line;
            while((line = br.readLine()) != null) {
                String [] attributes = line.split(";");
                long reservationId = Long.valueOf(attributes[0]);
                String clientName = attributes[1];
                String clientSurname = attributes[2];
                Client client = new Client(); //dorobic
                String periodFrom = attributes[3];
                String periodTo = attributes[4];
                String roomName = attributes[5];
                Reservation reservation = new ReservationInstance();//ustalic pola  
                reservations.add(reservation);
            }
        } catch(IOException ex) {
            ex.printStackTrace();            
        }
        return reservations;
    }
}
