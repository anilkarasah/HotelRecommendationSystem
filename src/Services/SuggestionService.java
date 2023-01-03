package Services;

import Models.Hotel;
import Models.HotelReview;
import Models.HotelSuggestion;
import Models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SuggestionService {
    private User user;
    private ArrayList<Hotel> hotels;

    public SuggestionService(User user, ArrayList<Hotel> hotels) {
        this.user = user;
        this.hotels = hotels;
    }

    public ArrayList<HotelSuggestion> calculate() {
        float[] factors = this.user.getFactors();
        ArrayList<HotelReview> usersScore = this.user.getPreviousHotels();

        ArrayList<HotelSuggestion> response = new ArrayList<>();
        for (Hotel hotel : hotels) {
            boolean skipFlag = false;
            for (HotelReview usersReviewedHotels : this.user.getPreviousHotels()) {
                if (hotel.equals(usersReviewedHotels.getHotel()))
                    skipFlag = true;
            }

            if (!skipFlag) {
                float score = 0;
                int i;
                for (i = 0; i < factors.length - 1; i++) {
                    score += factors[i] * (hotel.facilities[i] ? 1 : 0);
                }
                score += factors[factors.length - 1] * hotel.avgScore;

                response.add(new HotelSuggestion(hotel, score));
            }
        }

        Collections.sort(response,
                (o1, o2) -> o1.getCalculatedScore() > o2.getCalculatedScore() ?
                -1 : (o1.getCalculatedScore() < o2.getCalculatedScore() ? 1 : 0));
        return response;
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
