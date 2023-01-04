package Services;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

import Enums.DistrictsEnum;
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
        HashMap<String, ArrayList<String>> cityMap = new HashMap<String, ArrayList<String>>();

        int i = 0;
        while ((line = reader.readLine()) != null) {
            Hotel hotel = Hotel.parseCSV(line, i);

            ArrayList<String> districtList;
            if ((districtList = cityMap.get(hotel.province)) != null) {
            	if (!districtList.contains(hotel.district))
            		districtList.add(hotel.district);
            } else {
            	cityMap.put(hotel.province, new ArrayList<String>());
            }

            hotels.add(hotel);
            i++;
        }
        
        DistrictsEnum.setDistrictsEnum(cityMap);

        reader.close();

        return hotels;
    }
}
