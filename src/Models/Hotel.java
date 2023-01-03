package Models;

import Enums.Facility;

public class Hotel {
    public int id;
    public String name;
    public String address;
    public double avgScore;
    public double price;

    public boolean[] facilities;

    public Hotel(int id, String name, String address, double avgScore, int price, int[] facilities) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.avgScore = avgScore;
        this.price = price;
        this.facilities = new boolean[facilities.length];
        for (int i = 0; i < facilities.length; i++) {
            this.facilities[i] = facilities[i] == 1;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.name + '\n');
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

    public static Hotel parseCSV(String line, int lineNumber) {
        String[] values = line.split(",");
        String name = values[0].trim();
        String address = values[1].trim();
        float avgScore = Float.parseFloat(values[2].trim());
        int price = Integer.parseInt(values[3].trim());

        int[] facilities = new int[Facility.List.length];
        facilities[0] = Integer.parseInt(values[4]);
        facilities[1] = Integer.parseInt(values[5]);
        facilities[2] = Integer.parseInt(values[6]);
        facilities[3] = Integer.parseInt(values[7]);
        facilities[4] = Integer.parseInt(values[8]);
        facilities[5] = Integer.parseInt(values[9]);
        facilities[6] = Integer.parseInt(values[10]);
        facilities[7] = Integer.parseInt(values[11]);
        facilities[8] = Integer.parseInt(values[12]);

        return new Hotel(lineNumber, name, address, avgScore, price, facilities);
    }
}
