
public class MoviePages {
	private int total;
	private Movies[] movies;
	private Links links;
	private String link_template;
		
	// getter and setter for "total"
	public int getTotal() {
		return total;
	}
		
	public void setTotal(int total) {
		this.total = total;
	}
		
	// getter and setter for "movies"
	public Movies[] getMovies() {
		return movies;
	}
		
	public void setMovies(Movies[] movies) {
		this.movies = movies;
	}	
		
	// getter and setter for "links"
	public Links getLinks() {
		return links;
	}
		
	public void setLinks(Links links) {
		this.links = links;
	}	
		
	// getter and setter for "link_template"
	public String getLink_template() {
		return link_template;
	}
		
	public void setLink_template(String link_template) {
		this.link_template = link_template;
	}	
}
