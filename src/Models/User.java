package Models;

import Enums.Facility;

import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private final String username;
    private ArrayList<HotelReview> previousHotelReviews;
    private float[] factors;

    public User(String username, ArrayList<HotelReview> hotelReviews) {
        this.username = username;
        this.previousHotelReviews = hotelReviews;
        this.factors = new float[Facility.List.length];
        Arrays.fill(this.factors, 0);
    }

    public float[] getFactors() {
        return factors;
    }

    public void setFactors(float[] factors) {
        this.factors = factors;
    }

    public String getUsername() {
        return this.username;
    }

    public ArrayList<HotelReview> getPreviousHotels() {
        return this.previousHotelReviews;
    }

    public void addHotelReview(HotelReview previousHotelReview) {
        if (previousHotelReview != null)
            this.previousHotelReviews.add(previousHotelReview);
    }

    public HotelReview removeReview(int index) {
        return this.previousHotelReviews.remove(index);
    }

    public boolean updateReview(int index, float newReview) {
        HotelReview review = this.previousHotelReviews.get(index);

        if (review == null) return false;

        review.setScore(newReview);
        return true;
    }

    public void calculateFactors() {
        for (HotelReview review : this.previousHotelReviews) {
            Hotel hotel = review.getHotel();
            float userScore = review.getScore();

            for (int i = 0; i < hotel.facilities.length; i++) {
                // factor calculation formula:
                //                   10 - (hotelAvgScore - userScore)
                // prevFactorValue = -------------------------------- + prevFactorValue
                //                                  10
                if (hotel.facilities[i]) this.factors[i] += (10 - hotel.avgScore + userScore) / 10;
            }
        }
    }
}
