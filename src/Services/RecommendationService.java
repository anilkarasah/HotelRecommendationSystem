package Services;

import Models.Hotel;
import Models.HotelReview;
import Models.HotelRecommendation;
import Models.User;
import Models.UserReview;

import java.util.ArrayList;

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

    // kullanıcının eskiden kaldığı oteller için yaptığı değerlendirmelere göre yeni oteller önerir
    public void recommendHotels() {
        ArrayList<HotelReview> usersScore = this.user.getHotelReviews();

        // kullanıcının, otel olanaklarına ne kadar önem verdiğini hesaplar
        this.user.calculateFactors();
        double[] normalizedFactors = normalize(this.user.getFactors(), -0.25, 1);

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

        // skorları hesaplanan otelleri skorları azalan sırada sıralar
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

    // min ve max değerleri arasında değişen bir normalize işlemi yapar
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

        // normalizasyon formülü:
        //     x - minValue
        // --------------------- * (max - min) + min
        // maxValue - minValue

        double distance = (max - min) / (maxValue - minValue);
        for (int i = 0; i < factors.length; i++) {
            result[i] = (factors[i] - minValue) * distance + min;
        }

        return result;
    }
    
    // kullanıcının değerlendirdiği oteller listesine yenisini ekler,
    // önceden değerlendirdiği bir otel varsa onun skorunu günceller
    public void addUserReview(UserReview userReview) {
    	for (int i = 0; i < this.userReviewsList.size(); i++) {
    		UserReview ur = this.userReviewsList.get(i);
    		if (userReview.getRecomendedHotelIndex() == ur.getRecomendedHotelIndex()) {
    			ur.setScore(userReview.getScore());
    			return;
    		}
    	}
    	
    	this.userReviewsList.add(userReview);
    }
    
    // doğruluk oranı için mean squared error değerini hesaplar
    public void calculateMeanSquaredError() {
    	float sum = 0;
    	for (int i = 0; i < this.userReviewsList.size(); i++) {
    		double difference = ((double)this.userReviewsList.get(i).getScore() - this.normalizedScores[i]);
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
