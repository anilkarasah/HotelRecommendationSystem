package Models;

public class HotelReview {
    private Hotel hotel;
    private float score;

    private static final float fMaxScore = 10;

    public HotelReview(Hotel hotel, float score) {
        this.hotel = hotel;
        this.score = score <= fMaxScore ? score : fMaxScore;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score <= fMaxScore ? score : fMaxScore;
    }

    @Override
    public String toString() {
        return this.hotel.name + " için " + String.format("%.1f puan\n", this.score);
    }
}
