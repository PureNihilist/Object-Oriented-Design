package hotel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        Path pathToFile = Paths.get(fileName);
        try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            String line;
            while((line = br.readLine()) != null) {
                String [] attributes = line.split(";");
                long reservationId = Long.valueOf(attributes[0]);
                String clientName = attributes[1];
                String clientSurname = attributes[2];
                int clientAge = Integer.valueOf(attributes[3]);
                long clientPESEL = Long.valueOf(attributes[4]);
                String clientType = attributes[5];
                Client client = null;
                switch(clientType) {
                    case "Person" :
                        client = new Person(clientName,clientSurname,clientAge,clientPESEL);
                        break;
                    case "LoyalClient" :
                        client = new LoyalClient(clientName,clientSurname,clientAge,clientPESEL);
                        break;
                    case "Learner" :
                        client = new Learner(clientName,clientSurname,clientAge,clientPESEL);
                        break;
                    case "Student":
                        client = new Student(clientName,clientSurname,clientAge,clientPESEL);
                        break;
                    case "Pensioner":
                        client = new Pensioner(clientName,clientSurname,clientAge,clientPESEL);
                        break;
                    case "Invalid":
                        client = new Invalid(clientName,clientSurname,clientAge,clientPESEL);
                        break;
                    case "CompanyAgent":
                        client = new CompanyAgent(clientName,clientSurname,clientAge,clientPESEL);
                        break;
                }
                String periodFrom = attributes[6]; //Date format : year-month-day example : 2017-11-19
                LocalDate from = LocalDate.parse(periodFrom);
                String periodTo = attributes[7];
                LocalDate to = LocalDate.parse(periodTo);
                PeriodControl period_control = new PeriodControl(from,to); //throws exception but i catch it in global catch
                List<Room> room_list = new ArrayList();
                for(int i = 8 ; i < attributes.length ; i+=3) { //One client may take reservation for more then 1 room, every room requires name,capacity and quality
                    String roomName = attributes[i];
                    int roomCapacity = Integer.valueOf(attributes[i + 1]);
                    int roomQuality = Integer.valueOf(attributes[i + 2]);
                    Room room = new Room(roomName, roomCapacity, roomQuality);
                    room_list.add(room);
                }
                clients.add(client);
                ReservationInstance reservation = new ReservationInstance(reservationId,client,period_control,room_list);//ustalic pola
                reservations.add(reservation);
            }
        } catch(Exception ex) {
            ex.printStackTrace();            
        }
        return reservations;
    }
}
