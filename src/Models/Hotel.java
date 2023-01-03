package Models;

import Enums.Facility;

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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.name + '\n');
        result.append("\t" + this.province + '\n');
        result.append("\t" + this.district + '\n');
        result.append("\t" + this.address + '\n');
        result.append("\t" + String.format("Average score: %.1f", this.avgScore) + '\n');
        result.append("\t" + String.format("Price: %.1fTL", this.price) + '\n');

        for (int i = 0; i < this.facilities.length; i++) {
            if (this.facilities[i]) {
                result.append("\t> " + Facility.getFacilityName(i));
            }
        }

        return result.toString();
    }

    public static Hotel parseCSV(String line) {
        // split current line by commas
        String[] values = line.split(",");

        // parse values
        int id = Integer.parseInt(values[0]);
        String name = values[1];
        String province = values[2];
        String district = values[3];
        String address = values[4];
        float avgScore = Float.parseFloat(values[5]);
        float price = Float.parseFloat(values[6]);

        // parse facilities individually
        boolean[] facilities = new boolean[Facility.List.length];
        for (int i = 0; i < facilities.length; i++) {
            // facility values contain 0 or 1, so
            // we can parse it into booleans easily
            facilities[i] = Integer.parseInt(values[i + 7]) == 1;
        }

        return new Hotel(id, name, province, district, address, avgScore, price, facilities);
    }
}