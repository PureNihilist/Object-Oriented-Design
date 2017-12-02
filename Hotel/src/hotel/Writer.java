package hotel;

import java.io.BufferedWriter;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Mateusz Galas and Hubert Banaś
 * Writer class writes CVS files. Uses singleton design pattern.
 */
public class Writer {

    /**
     *Instance of class Writer.
     */
    public volatile static Writer writer = null;

    /**
     *Constructor, which prevents from creating another instance of class writer.
     * @throws RuntimeException
     */
    private Writer() {
        if(writer != null) {
            throw new RuntimeException("Cannot create instance of singleton. Use getInstance().");
        }
    }

    /**
     * Singleton method to get instance of class.
     * @return Instance of class writer.
     */
    public static Writer getInstance() {
        Writer writeCSV = Writer.writer;
        if(writeCSV == null) {
            synchronized(Writer.class) {
                writeCSV = Writer.writer;
                if(writeCSV == null) {
                    Writer.writer = writeCSV = new Writer();
                }
            }
        }
        return writeCSV;
    }

    /**
     * Saves rooms to csv file
     * @param fileName
     * @param rooms
     */
    public void writeRoomsCSV(String fileName, List<Room> rooms) {
        Path pathToFile = Paths.get(fileName);
        try(BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            for(Room room : rooms){
                String line = room.getName()+";"+room.getCapacity()+";"+room.getQuality()+";";
                bw.write(line);
                bw.newLine();
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Saves reservations to csv file
     * @param fileName
     * @param reservations
     */
    public void writeReservationsCSV(String fileName, List<ReservationInstance> reservations) {
        Path pathToFile = Paths.get(fileName);
        try(BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {
            for(ReservationInstance reservationInstance : reservations){
                String line;
                long ID = reservationInstance.getId();
                String confirmation = reservationInstance.isConfirmed();
                Client client = reservationInstance.getClient();
                PeriodControl periodControl  = reservationInstance.getPeriodControl();
                List <Room> roomInfo = reservationInstance.getRoomsInfo();
                line = ID+";"+confirmation+";"+client.getName()+";"+client.getSurname()+";"+client.getAge()+";"+client.getPESEL()+";"+client.getId()+";"+periodControl.getBegin()+";"+periodControl.getEnd()+";";
                line = roomInfo.stream().map((room) -> room.getName()+";"+room.getCapacity()+";"+room.getQuality()+";").reduce(line, String::concat); //dobra wiem ze przejebana linijka ale to to samo co for each po roomie :D
                bw.write(line);
                bw.newLine();
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Saves promotional seasons to csv file
     * @param fileName
     * @param seasons
     */
    public void writeSeasonsCSV(String fileName, ArrayList<PeriodControl> seasons) {
        Path pathToFile = Paths.get(fileName);
        try(BufferedWriter bw = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8)) {          
            for(PeriodControl control : seasons) {
                double value = control.getRabate();
                String line = control.getBegin()+";"+control.getEnd()+";"+value+";";
                bw.write(line);
                bw.newLine();
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

}
