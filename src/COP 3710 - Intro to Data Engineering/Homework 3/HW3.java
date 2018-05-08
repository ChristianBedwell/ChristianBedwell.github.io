import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

public class HW3 {
	
	public static void main(String args[]) throws JAXBException,
			JsonParseException, JsonMappingException, IOException,
			ClassNotFoundException, SQLException {		

		// load the JDBC driver
		Class.forName("org.h2.Driver");
		Connection connection = DriverManager.getConnection("jdbc:h2:"
				+ System.getProperty("user.dir")+"/db/movies", "sa", "");	
		
		Statement statement = connection.createStatement();
		
		// drop each table so that debugging the program is seam-less
		statement.executeUpdate("DROP TABLE IF EXISTS Movie;");
		statement.executeUpdate("DROP TABLE IF EXISTS Actor;");
		statement.executeUpdate("DROP TABLE IF EXISTS Character;");
		
		// create a relational database named "movies", with three tables: Movie, Actor, and Character
		statement.execute("CREATE TABLE Movie(" + 
				"id VARCHAR(100)," + 
				"title VARCHAR(100)," + 
				"year SMALLINT UNSIGNED," + 
				"mpaa_rating VARCHAR(10)," + 
				"audience_score SMALLINT UNSIGNED," + 
				"critics_score SMALLINT UNSIGNED," + 
				"CONSTRAINT pk_movie PRIMARY KEY (id)" + 
				");");
		statement.execute("CREATE TABLE Actor(" + 
				"id VARCHAR(100)," + 
				"name VARCHAR(100)," + 
				"CONSTRAINT pk_actor PRIMARY KEY (id)" + 
				");");
		statement.execute("CREATE TABLE Character(" + 
				"actor_id VARCHAR(100)," + 
				"movie_id VARCHAR(100)," + 
				"character VARCHAR(100)," + 
				"CONSTRAINT pk_character PRIMARY KEY (movie_id, actor_id, character)," + 
				"CONSTRAINT fk_actor_id FOREIGN KEY (actor_id) REFERENCES Actor(id)," + 
				"CONSTRAINT fk_movie_id FOREIGN KEY (movie_id) REFERENCES Movie(id)," + 
				");");

		// configure the ObjectMapper for extracting JSON data
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		// extract the data in the JSON files (1-25) to build Java objects
		for(int i = 1; i < 26; i++) {
			// read data from JSON files from "movies" folder in working directory
			MoviePages page = mapper.readValue(new File((System.getProperty("user.dir") 
					+ "//movies//page" + i + ".json")), MoviePages.class);
			
			// create a list of movies
			Movies[] arrMovies = page.getMovies();
			
			// loop through each movie
			for(Movies movies : arrMovies) {	
					
				// insert the data into the relational database for each movie
				statement.executeUpdate("MERGE INTO MOVIE (id, title, year, mpaa_rating, audience_score, critics_score) VALUES("+
							"'" + replaceSingleQuote(movies.getId()) + "', " + 
							"'" + replaceSingleQuote(movies.getTitle()) + "', " +
							"" + movies.getYear() + ", " +
							"'" + movies.getMpaa_rating() + "', " +
							"" + movies.getRatings().getAudience_score() + ", " +
							"" + movies.getRatings().getCritics_score() + ");");
				
				// create an array of actors
				Abridged_cast[] cast = movies.getAbridged_cast();
				
				// loop through each actor
				for(Abridged_cast actors : cast) {
						
					// insert the data into the relational database for actor
					statement.executeUpdate("MERGE INTO ACTOR (id, name) VALUES(" +
								"'" + replaceSingleQuote(actors.getId()) + "', " +
								"'" + replaceSingleQuote(actors.getName()) + "');");
						
					String[] characters = actors.getCharacters();
						
					if (characters != null) {
						// loop through characters since there may be multiple in one movie
						for (String character : characters) {
							// insert the data into the relational database for actor
							statement.executeUpdate("MERGE INTO CHARACTER (actor_id, movie_id, character) VALUES(" +
									"'" + replaceSingleQuote(actors.getId()) + "', " +
									"'" + replaceSingleQuote(movies.getId()) + "', " +
									"'" + replaceSingleQuote(character) + "');");
							}
						}								
					}
				}
			}	
		connection.close();
	}

	// replace all occurrences of ' with '' in each string
	private static String replaceSingleQuote(String field) {
		if (field.contains("'")) {
			field = field.replace("'", "''");
		}
		return field;
	}
}