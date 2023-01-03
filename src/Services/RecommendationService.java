package Services;

import Models.Hotel;
import Models.HotelReview;
import Models.HotelRecommendation;
import Models.User;

import java.util.ArrayList;
import java.util.Scanner;

public class RecommendationService {
    private User user;
    private ArrayList<Hotel> hotels;

    public RecommendationService(User user, ArrayList<Hotel> hotels) {
        this.user = user;
        this.hotels = hotels;
    }

    public ArrayList<HotelRecommendation> calculate() {
        ArrayList<HotelReview> usersScore = this.user.getHotelReviews();

        this.user.calculateFactors();
        float[] normalizedFactors = normalizeFactors(this.user.getFactors());

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
        return response;
    }

    // returns a new factors array with values that ranges between -1..1
    public static float[] normalizeFactors(float[] factors) {
        float[] result = new float[factors.length];
        float min = 100, max = -100;

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
        // ----------- * 1.25 - 0.25
        // (max - min)

        float distance = (float)1.25 / (max - min);
        for (int i = 0; i < factors.length; i++) {
            result[i] = (factors[i] - min) * distance - (float)0.25;
        }

        System.out.print("Initial factors: ");
        for (float x : factors) {
            System.out.printf("%.2f ", x);
        }
        System.out.println();

        System.out.print("Normalized factors: ");
        for (float x : result) {
            System.out.printf("%.2f ", x);
        }
        System.out.println();

        return result;
    }

    public static HotelReview getOneReviewFromUser(ArrayList<Hotel> hotelList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hangi otelde kaldınız?");
        System.out.println("(ID'sini yazınız. Geçerli bir ID girilmezse işlem sonlanacaktır.)");
        System.out.print("> ");
        int hotelId = scanner.nextInt();

        if (hotelId < 0 || hotelId >= hotelList.size())
            return null;

        System.out.println(hotelId);
        Hotel selectedHotel = hotelList.get(hotelId);

        System.out.println(selectedHotel.name + " isimli otel için puanınız: ");
        float score = scanner.nextFloat();
        System.out.println(score);

        // !!! this causes an error - WTF 🤯🤯🤯
        // scanner.close();

        return new HotelReview(selectedHotel, score);
    }

    public static ArrayList<HotelReview> getReviewsFromUser(ArrayList<Hotel> hotelList) {
        ArrayList<HotelReview> reviews = new ArrayList<>();

        while (true) {
            HotelReview hotelReview = getOneReviewFromUser(hotelList);

            if (hotelReview == null)
                break;
            else
                reviews.add(hotelReview);
        }

        return reviews;
    }

    public ArrayList<HotelRecommendation> recommendHotels() {
        ArrayList<HotelRecommendation> suggestions = this.calculate();

        for (HotelRecommendation suggestion : suggestions) {
            System.out.printf("%3d > %50s - %.2f%n",
                    suggestion.getHotel().id,
                    suggestion.getHotel().name,
                    suggestion.getCalculatedScore());
        }

        return suggestions;
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
