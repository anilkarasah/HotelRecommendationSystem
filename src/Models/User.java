package Models;

import Enums.Facility;

import java.util.ArrayList;

public class User {
    private String username;
    private ArrayList<HotelReview> previousHotelReviews;
    private float[] factors;

    public User(String username, ArrayList<HotelReview> hotelReviews) {
        this.username = username;
        this.previousHotelReviews = hotelReviews;
        this.factors = new float[Facility.List.length];
        for (int i = 0; i < this.factors.length; i++) this.factors[i] = 0;
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

    public void addPreviousHotel(HotelReview previousHotelReview) {
        if (previousHotelReview != null)
            this.previousHotelReviews.add(previousHotelReview);
    }

    public HotelReview removeReview(int index) {
        HotelReview removedReview = this.previousHotelReviews.remove(index);

        return removedReview;
    }

    public boolean updateReview(int index, float newReview) {
        HotelReview review = this.previousHotelReviews.get(index);

        if (review == null) return false;

        review.setScore(newReview);
        return true;
    }

    public float[] calculateFactors() {
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

        return this.factors;
    }
}
