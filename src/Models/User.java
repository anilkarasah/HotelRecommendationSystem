package Models;

import Enums.FacilitiesEnum;

import java.util.ArrayList;

public class User {
    private ArrayList<HotelReview> hotelReviews;
    private float[] factors;

    public User(ArrayList<HotelReview> hotelReviews) {
        this.hotelReviews = hotelReviews;
        
        this.factors = new float[FacilitiesEnum.List.length];
        for (int i = 0; i < FacilitiesEnum.List.length; i++) {
            this.factors[i] = 0;
        }
    }

    public float[] getFactors() {
        return factors;
    }

    public void setFactors(float[] factors) {
        this.factors = factors;
    }

    public ArrayList<HotelReview> getHotelReviews() {
        return this.hotelReviews;
    }

    public void setHotelReviews(ArrayList<HotelReview> hotelReviews) {
        this.hotelReviews = hotelReviews;
    }

    public HotelReview removeReview(int index) {
        return this.hotelReviews.remove(index);
    }

    public boolean updateReview(int index, float newReview) {
        HotelReview review = this.hotelReviews.get(index);

        if (review == null) return false;

        review.setScore(newReview);
        return true;
    }

    // kullanıcının ilgi alanına uygun otel olanaklarının değerlerini hesaplar
    public void calculateFactors() {
        for (HotelReview review : this.hotelReviews) {
            Hotel hotel = review.getHotel();
            float userScore = review.getScore();

            for (int i = 0; i < hotel.facilities.length; i++) {
                // faktör hesaplama formülü:
                //                   10 - (hotelAvgScore - userScore)
                // prevFactorValue = -------------------------------- + prevFactorValue
                //                                  10
                if (hotel.facilities[i]) this.factors[i] += userScore / 10;
            }
        }
    }
}
