package movies_parsing;

public class Movie {
	
	// instance variables
	private String id;
	private String title;
	private int year;
	private String mpaa_rating;
	private Ratings ratings;
	private Cast [] abridged_cast;
	
	// getter and setter for id
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	// getter and setter for title
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title.replace("\'", "\'\'");		
	}
	
	// getter and setter for year
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	// getter and setter for mpaa_rating
	public String getMpaa_rating() {
		return mpaa_rating;
	}
	
	public void setMpaa_rating(String mpaa_rating) {
		this.mpaa_rating = mpaa_rating;
	}
	
	// getter and setter for ratings
	public Ratings getRatings() {
		return ratings;
	}
	
	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}
	
	// getter and setter for abridged_cast
	public Cast[] getAbridged_cast() {
		return abridged_cast;
	}
	
	public void setAbridged_cast(Cast[] abridged_cast) {
		this.abridged_cast = abridged_cast;
	}	
}
