package hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
                int roomQuality = Integer.valueOf(attributes[2]);
                Room room = new Room(roomName,roomCapacity,roomQuality);
                rooms.add(room);
            }
        } catch(IOException ex) {
            ex.printStackTrace();            
        }
        return rooms;
    }

    public List<ReservationInstance> readReservationCSV(String fileName) {
        List<ReservationInstance> reservations = new ArrayList<>();
        List<Client> clients = new ArrayList<>();
        ClientCache cache = ClientCache.getInstance();
        cache.initilizeClientCache(clients); //tutaj tworze liste klientow ktora jest przechowywana w ClientCache
        Path pathToFile = Paths.get(fileName);
        try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line;
            while((line = br.readLine()) != null) {
                String [] attributes = line.split(";");
                long reservationId = Long.valueOf(attributes[0]);
                String confirmation = attributes[1];
                boolean confirm = false;
                if(confirmation.equals("TAK")){
                    confirm = true;
                }
                String clientName = attributes[2];
                String clientSurname = attributes[3];
                int clientAge = Integer.valueOf(attributes[4]);
                long clientPESEL = Long.valueOf(attributes[5]);
                int clientType = Integer.valueOf(attributes[6]);
                Client client = cache.createClient(clientName, clientSurname, clientAge, clientPESEL, clientType);
                String periodFrom = attributes[7]; //Date format : year-month-day example : 2017-11-19
                LocalDate from = LocalDate.parse(periodFrom);
                String periodTo = attributes[8];
                LocalDate to = LocalDate.parse(periodTo);
                PeriodControl period_control = new PeriodControl(from,to); //throws exception but i catch it in global catch
                List<Room> room_list = new ArrayList();
                for(int i = 9 ; i < attributes.length ; i+=3) { //One client may take reservation for more then 1 room, every room requires name,capacity and quality
                    String roomName = attributes[i];
                    int roomCapacity = Integer.valueOf(attributes[i + 1]);
                    int roomQuality = Integer.valueOf(attributes[i + 2]);
                    Room room = new Room(roomName, roomCapacity, roomQuality);
                    room_list.add(room);
                }
                clients.add(client);
                ReservationInstance reservation = new ReservationInstance(reservationId,client,period_control,room_list,confirm);//ustalic pola
                reservations.add(reservation);
            }
        } catch(Exception ex) {
            ex.printStackTrace();            
        }
        return reservations;
    }
    
    public HashMap<PeriodControl,Double> readSeasonsCSV(String fileName) {
        HashMap<PeriodControl,Double> seasons = new HashMap<>();
        Path pathToFile = Paths.get(fileName);
        try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line;
            while((line = br.readLine()) != null) {
                String [] attributes = line.split(";");
                String periodFrom = attributes[0]; //Date format : year-month-day example : 2017-11-19
                LocalDate from = LocalDate.parse(periodFrom);
                String periodTo = attributes[1];
                LocalDate to = LocalDate.parse(periodTo);
                PeriodControl period_control = new PeriodControl(from,to); //throws exception but i catch it in global catch
                double discount = Double.valueOf(attributes[2]);
                seasons.put(period_control,discount);
            }
        } catch(Exception ex) {
            ex.printStackTrace();            
        }
        return seasons;
    }
}
