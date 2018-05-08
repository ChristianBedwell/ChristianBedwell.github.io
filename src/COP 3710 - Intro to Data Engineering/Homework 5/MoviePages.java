package movies_parsing;
import java.util.HashMap;
import java.util.Map;

public class MoviePages {
	
	private Movie [] movies;
	
	// getter and setter for movies
	public Movie[] getMovies() {
		return movies;
	}
	
	public void setMovies(Movie[] movies) {
		this.movies = movies;
	}
	
	public Map<String, String> getActors() {
		Map<String, String> actors = new HashMap<String, String>();
		
		// for each movie, store the actor name and id
		for(Movie movie : movies) {
			Cast [] casts = movie.getAbridged_cast();
			if(casts == null) {
				continue;
			}
			
			for(Cast cast : casts) {
				if(!actors.containsKey(cast.getId())) {
					actors.put(cast.getId(), cast.getName());
				}
			}
		}
		return actors;
	}	
}
