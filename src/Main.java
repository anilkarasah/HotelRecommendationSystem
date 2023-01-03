import Enums.Facility;
import Models.*;
import Services.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            CSVService service = new CSVService("C:\\Users\\anilk\\Desktop\\alsana.csv");

            ArrayList<Hotel> hotelList = service.ReadAllValues();
            int i = 0;
            for (Hotel h : hotelList) {
                System.out.println("##### OTEL " + i + " #####");
                System.out.println(h.toString());
                i++;
            }

            System.out.println("Hangi otelde kaldınız?");

            ArrayList<HotelReview> reviews = new ArrayList<>();
            Scanner scanner = new Scanner(System.in);
            int menu;
            float score;
            boolean flag = true;
            do {
                System.out.print("> ");
                menu = scanner.nextInt();

                if (menu >= 0 && menu < hotelList.size()) {
                    Hotel selectedHotel = hotelList.get(menu);
                    System.out.println(selectedHotel.name + " isimli otel için puanınız: ");
                    score = scanner.nextFloat();

                    reviews.add(new HotelReview(selectedHotel, score));
                } else {
                    flag = false;
                }
            } while (flag);

            scanner.close();

            for (HotelReview review : reviews) {
                System.out.println(review.toString());
            }

            User user = new User("Sanaldeli", reviews);
            user.calculateFactors();

            float[] factors = user.getFactors();
            for (i = 0; i < factors.length - 1; i++) {
                System.out.println(String.format("%.2f", factors[i]) + " - " + Facility.List[i]);
            }

            SuggestionService suggestionService = new SuggestionService(user, hotelList);

            ArrayList<HotelSuggestion> suggestions = suggestionService.calculate();

            i = 0;
            for (HotelSuggestion suggestion : suggestions) {
                System.out.println(i + " > " + suggestion.getHotel().name + " - "
                        + String.format("%.2f", suggestion.getCalculatedScore()));
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}