
public class Movies {
	private String id;
	private String title;
	private int year;
	private String mpaa_rating;
	private int runtime;
	private String critics_consensus;
	private Release_dates release_dates;
	private Ratings ratings;
	private String synopsis;
	private Posters posters;
	private Abridged_cast[] abridged_cast;
	private Alternative_ids alternative_ids;
	private Links links;
	
	// getter and setter for "id"
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	// getter and setter for "title"
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	// getter and setter for "year"
	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	// getter and setter for "mpaa_rating"
	public String getMpaa_rating() {
		return mpaa_rating;
	}
	
	public void setMpaa_rating(String mpaa_rating) {
		this.mpaa_rating = mpaa_rating;
	}
	
	// getter and setter for "runtime"
	public int getRuntime() {
		return runtime;
	}
	
	public void setRuntime(int runtime) {
		this.runtime = runtime;
	}
	
	// getter and setter for "critics_consensus"
	public String getCritics_consensus() {
		return critics_consensus;
	}
	
	public void setCritics_consensus(String critics_consensus) {
		this.critics_consensus = critics_consensus;
	}
	
	// getter and setter for "release_dates"
	public Release_dates getRelease_dates() {
		return release_dates;
	}
	
	public void setRelease_dates(Release_dates release_dates) {
		this.release_dates = release_dates;
	}
	
	// getter and setter for "ratings"
	public Ratings getRatings() {
		return ratings;
	}
	
	public void setRatings(Ratings ratings) {
		this.ratings = ratings;
	}
	
	// getter and setter for "synopsis"
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	// getter and setter for "posters"
	public Posters getPosters() {
		return posters;
	}
	
	public void setPosters(Posters posters) {
		this.posters = posters;
	}
	
	// getter and setter for "abridged_cast"
	public Abridged_cast[] getAbridged_cast() {
		return abridged_cast;
	}
	
	public void setAbridged_cast(Abridged_cast[] abridged_cast) {
		this.abridged_cast = abridged_cast;
	}
	
	// getter and setter for "alternative_ids"
	public Alternative_ids getAlternative_ids() {
		return alternative_ids;
	}
	
	public void setAlternative_ids(Alternative_ids alternative_ids) {
		this.alternative_ids = alternative_ids;
	}
	
	// getter and setter for "links"
	public Links getLinks() {
		return links;
	}
	
	public void setLinks(Links links) {
		this.links = links;
	}	
}
