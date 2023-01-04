package Services;

import java.io.*;
import java.util.ArrayList;
import Models.Hotel;

public class CSVService {
    private String filePath;

    public CSVService(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Hotel> ReadAllValues() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        ArrayList<Hotel> hotels = new ArrayList<>();

        line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            Hotel hotel = Hotel.parseCSV(line);
            hotels.add(hotel);
        }

        reader.close();

        return hotels;
    }
}
