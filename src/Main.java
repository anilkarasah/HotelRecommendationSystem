import Models.*;
import Services.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            CSVService csvService = new CSVService("C:\\Users\\anilk\\Desktop\\istanbul.csv");

            ArrayList<Hotel> hotelList = csvService.ReadAllValues();
            int i = 0;
            for (Hotel h : hotelList) {
                System.out.println("##### OTEL " + i + " #####");
                System.out.println(h.toString());
                i++;
            }

            ArrayList<HotelReview> reviews = RecommendationService.getPreviousHotelsFromUser(hotelList);
            for (HotelReview review : reviews) {
                System.out.println(review.toString());
            }

            RecommendationService recommendationService = new RecommendationService(
                    new User("Sanaldeli", reviews),
                    hotelList);

            ArrayList<HotelRecommendation> suggestions = recommendationService.recommendHotels();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}