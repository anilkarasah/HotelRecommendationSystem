package Models;

public class HotelReview {
    private Hotel hotel;
    private float score;

    private static final float fMaxScore = 10;

    public HotelReview(Hotel hotel, float score) {
        this.hotel = hotel;
        this.score = scoreConstraint(score);
    }

    public static float scoreConstraint(float score) {
        float absScore = score < 0 ? -score : score;
        return absScore >= fMaxScore ? fMaxScore : absScore;
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
        this.score = scoreConstraint(score);
    }

    @Override
    public String toString() {
        return this.hotel.name + " için " + String.format("%.1f puan\n", this.score);
    }
}
