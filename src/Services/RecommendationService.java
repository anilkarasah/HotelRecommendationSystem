package Services;

import Models.Hotel;
import Models.HotelReview;
import Models.HotelRecommendation;
import Models.User;
import Models.UserReview;

import java.util.ArrayList;
import java.util.Scanner;

public class RecommendationService {
    private User user;
    private ArrayList<Hotel> hotels;
    private ArrayList<HotelRecommendation> recommendedHotels;
    private double[] normalizedScores;
    private ArrayList<UserReview> userReviewsList;
    private double meanSquaredError;

    public RecommendationService(User user, ArrayList<Hotel> hotels) {
        this.user = user;
        this.hotels = hotels;
        this.normalizedScores = new double[hotels.size()];
        this.userReviewsList = new ArrayList<>();
        this.meanSquaredError = 0;
    }

    public void recommendHotels() {
        ArrayList<HotelReview> usersScore = this.user.getHotelReviews();

        this.user.calculateFactors();
        double[] normalizedFactors = normalize(this.user.getFactors(), -0.25, 1.25);

        ArrayList<HotelRecommendation> response = new ArrayList<>();
        for (Hotel hotel : this.hotels) {
            boolean skipFlag = false;

            for (HotelReview usersReviewedHotels : usersScore) {
                if (hotel.equals(usersReviewedHotels.getHotel())) {
                    skipFlag = true;
                    break;
                }
            }

            if (!skipFlag) {
                float score = 0;
                for (int i = 0; i < normalizedFactors.length; i++) {
                    score += normalizedFactors[i] * (hotel.facilities[i] ? hotel.avgScore : 0);
                }
                response.add(new HotelRecommendation(hotel, score));
            }
        }

        response.sort((o1, o2) -> Float.compare(o2.getCalculatedScore(), o1.getCalculatedScore()));
        
        float[] factors = new float[response.size()];
        int i = 0;
        for (HotelRecommendation hr : response ) {
        	factors[i] = hr.getCalculatedScore();
        	i++;
        }
        
        this.normalizedScores = normalize(factors, 0, 10);
        this.recommendedHotels = response;
    }

    // returns a new factors array with values that ranges between -1..1
    public static double[] normalize(float[] factors, double min, double max) {
        double[] result = new double[factors.length];
        float minValue = 100, maxValue = -100;

        for (float factor : factors) {
            if (factor > maxValue) {
                maxValue = factor;
            }

            if (factor < minValue) {
                minValue = factor;
            }
        }

        // normalization formula:
        //     x - minValue
        // --------------------- * max - min
        // maxValue - minValue

        double distance = (max - min) / (maxValue - minValue);
        for (int i = 0; i < factors.length; i++) {
            result[i] = (factors[i] - minValue) * distance - min;
        }

        System.out.print("Initial factors: ");
        for (float x : factors) {
            System.out.printf("%.2f ", x);
        }
        System.out.println();

        System.out.print("Normalized factors: ");
        for (double x : result) {
            System.out.printf("%.2f ", x);
        }
        System.out.println();

        return result;
    }
    
    public void addUserReview(UserReview userReview) {
    	this.userReviewsList.add(userReview);
    }
    
    public void calculateMeanSquaredError() {
    	float sum = 0;
    	for (int i = 0; i < this.userReviewsList.size(); i++) {
    		float difference = (float) (this.userReviewsList.get(i).getScore() - this.normalizedScores[i]); 
    		sum += difference * difference;
    	}
    	
    	this.meanSquaredError = sum / this.userReviewsList.size();
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
    
    public ArrayList<HotelRecommendation> getRecommendedHotels() { return this.recommendedHotels; }
    
    public double getMeanSquaredError() { return this.meanSquaredError; }
}
