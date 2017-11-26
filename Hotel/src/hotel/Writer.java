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
 * @author Mateusz Galas
 */
public class Writer {
    public volatile static Writer writer = null;

    private Writer() {
        if(writer != null) {
            throw new RuntimeException("Cannot create instance of singleton. Use getInstance().");
        }
    }

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

}
