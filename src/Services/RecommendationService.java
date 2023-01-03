package Services;

import Models.Hotel;
import Models.HotelReview;
import Models.HotelRecommendation;
import Models.User;

import java.util.ArrayList;
import java.util.Arrays;

public class RecommendationService {
    private User user;
    private ArrayList<Hotel> hotels;

    public RecommendationService(User user, ArrayList<Hotel> hotels) {
        this.user = user;
        this.hotels = hotels;
    }

    public ArrayList<HotelRecommendation> calculate() {
        float[] factors = normalizeFactors(this.user.getFactors());
        ArrayList<HotelReview> usersScore = this.user.getPreviousHotels();

        ArrayList<HotelRecommendation> response = new ArrayList<>();
        for (Hotel hotel : hotels) {
            boolean skipFlag = false;
            for (HotelReview usersReviewedHotels : usersScore) {
                if (hotel.equals(usersReviewedHotels.getHotel())) {
                    skipFlag = true;
                }
            }

            if (!skipFlag) {
                float score = 0;
                for (int i = 0; i < factors.length; i++) {
                    score += factors[i] * (hotel.facilities[i] ? (hotel.avgScore) : 0);
                }
                response.add(new HotelRecommendation(hotel, score));
            }
        }

        response.sort((o1, o2) -> Float.compare(o2.getCalculatedScore(), o1.getCalculatedScore()));
        return response;
    }

    // returns a new factors array with values that ranges between -1..1
    public static float[] normalizeFactors(float[] factors) {
        float min = 100, max = -100;

        float[] result = new float[factors.length];

        for (float factor : factors) {
            if (factor > max) {
                max = factor;
            }

            if (factor < min) {
                min = factor;
            }
        }

        // normalization formula:
        //  (x - min)
        // ----------- * 2 - 1
        // (max - min)

        float distance = 2 / (max - min);

        for (int i = 0; i < factors.length; i++) {
            result[i] = (factors[i] - min) * distance - 1;
        }

        System.out.print("Initial factors: ");
        for (float x : factors) {
            System.out.print(String.format("%.2f ", x));
        }
        System.out.println();

        System.out.print("Normalized factors: ");
        for (float x : result) {
            System.out.print(String.format("%.2f ", x));
        }
        System.out.println();

        return result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }
}
