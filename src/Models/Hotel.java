package Models;

import Enums.FacilitiesEnum;

public class Hotel {
    public int id;
    public String name;
    public String province;
    public String district;
    public String address;
    public float avgScore;
    public float price;

    public boolean[] facilities;

    public Hotel(int id, String name, String province, String district,
                 String address, float avgScore, float price, boolean[] facilities) {
        this.id = id;
        this.name = name;
        this.province = province;
        this.district = district;
        this.address = address;
        this.avgScore = avgScore;
        this.price = price;
        this.facilities = facilities;
    }

    // her bir otelin, CSV dosyasından neye göre okunacağını ayarlar
    public static Hotel parseCSV(String line, int index) {
        // split current line by commas
        String[] values = line.split(",");

        // parse values
        String name = values[1];
        String province = values[2];
        String district = values[3];
        String address = values[4];
        float avgScore = Float.parseFloat(values[5]);
        float price = Float.parseFloat(values[6]);

        // parse facilities individually
        boolean[] facilities = new boolean[FacilitiesEnum.List.length];
        for (int i = 0; i < facilities.length; i++) {
            // facility values contain 0 or 1, so
            // we can parse it into booleans easily
            facilities[i] = Integer.parseInt(values[i + 7]) == 1;
        }

        return new Hotel(index, name, province, district, address, avgScore, price, facilities);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(String.format("%50s - ", this.name));
        result.append(String.format("%6.2f TL - ", this.price));
        result.append(String.format("%2.1f / 10", this.avgScore));

        return result.toString();
    }
}
