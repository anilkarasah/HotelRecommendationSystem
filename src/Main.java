import Models.*;
import Services.*;

import java.util.ArrayList;

import Enums.DistrictsEnum;

public class Main {
    public static void main(String[] args) {
        try {
            CSVService csvService = new CSVService("./hotels.csv");

            ArrayList<Hotel> hotelList = csvService.ReadAllValues();

            ArrayList<Hotel> hotelsList = DistrictsEnum.getHotelsOfDistrict("Kemer");
            if (hotelsList != null)
                for (Hotel hotel : hotelsList) {
                    System.out.println(hotel.name);
                }   //  */
/*
            for (ArrayList<String> districtList : DistrictsEnum.cityMap.values()) {
            	for (String district : districtList) {
            		System.out.println(district);
            	}
            }   //*/
            
  /*          for (String district : DistrictsEnum.getDistrictsOfCity("Antalya")) {
            	System.out.println(district);
            } // */


  /*          for (Hotel h : hotelList) {
                System.out.println("##### OTEL " + h.id + " #####");
                System.out.println(h.toString());
            }

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

            suggestions = recommendationService.recommendHotels(); // */
        } catch (Exception e) {
            System.out.println("Exception üretildi!!!!");
            e.printStackTrace();
        }
    }
}