package Models;

public class HotelSuggestion {
    private Hotel hotel;
    private float calculatedScore;

    public HotelSuggestion(Hotel hotel, float calculatedScore) {
        this.hotel = hotel;
        this.calculatedScore = calculatedScore;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public float getCalculatedScore() {
        return calculatedScore;
    }

    public void setCalculatedScore(float calculatedScore) {
        this.calculatedScore = calculatedScore;
    }
}
