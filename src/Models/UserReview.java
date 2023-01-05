package Models;

public class UserReview {
	private int recomendedHotelIndex;
	private float score;

	public UserReview(int recomendedHotelIndex, float score) {
		this.recomendedHotelIndex = recomendedHotelIndex;
		this.score = score;
	}
	
	public int getRecomendedHotelIndex() { return this.recomendedHotelIndex; }
	public float getScore() { return this.score; }
}
