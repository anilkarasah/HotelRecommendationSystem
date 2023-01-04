import Models.*;
import Services.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            CSVService csvService = new CSVService("C:\\Users\\anilk\\Desktop\\istanbul.csv");

            ArrayList<Hotel> hotelList = csvService.ReadAllValues();
//            for (Hotel h : hotelList) {
//                System.out.println("##### OTEL " + h.id + " #####");
//                System.out.println(h.toString());
//            }

            ArrayList<HotelReview> reviews = RecommendationService.getReviewsFromUser(hotelList);
            for (HotelReview review : reviews) {
                System.out.println(review.toString());
            }

            User user = new User(reviews);
            RecommendationService recommendationService = new RecommendationService(
                    user, hotelList);

            ArrayList<HotelRecommendation> suggestions = recommendationService.recommendHotels();

            ArrayList<HotelReview> newHotelReviews = RecommendationService.getReviewsFromUser(hotelList);

            reviews.addAll(newHotelReviews);

            user.setHotelReviews(reviews);

            suggestions = recommendationService.recommendHotels();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}