import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.neo4j.io.fs.FileUtils;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.traversal.Evaluators;
import org.neo4j.graphdb.traversal.Traverser;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import hw5.RelationType;
import movies_parsing.Cast;
import movies_parsing.Labels;
import movies_parsing.Movie;
import movies_parsing.MoviePages;

public class HW5 {
	private static final String DB_PATH = "target/movies-db";
	private static GraphDatabaseService graphDb;

	public static void main(String args[])
			throws JsonParseException, JsonMappingException, IOException {

		createDb(); // creating a graph database in DB_PATH
		load(); // loading the movie data from the JSON files in folder "movies"
		doQueries(); // do some queries
		shutDown(); // shuts down the database
	}

	// calls the queries executed by each method
	private static void doQueries() {
			
			printMoviesIn_Java(1980);			
			printCoStars_Java("Audrey Tautou");			
			printMoviesIn_Cql(1980);			
			printCoStars_Cql("Audrey Tautou");
	}
	
	// prints the movie titles made in a specific
	// year using Neo4j's Java API's
	public static void printMoviesIn_Java(int year) {		
		System.out.println("\n--------- Movies in 1980 (Java)-----------");
		// starting a transaction
		try(Transaction tx = graphDb.beginTx()) {
			ResourceIterator<Node> movies = graphDb.findNodes(
					Labels.MOVIE, "year", year);
			while(movies.hasNext()) {
				Node movie = movies.next();
				Object title = movie.getProperty("title");
				System.out.println(title);
			}
			tx.success();
		}		
	}
	
	// prints the names of actors who co-acted with a given actor
	// using Neo4j's Java API's
	public static void printCoStars_Java(String name) {		
		System.out.println("\n--------- Tautou's Co-Stars (Java)-----------");
		// starting a transaction
		try(Transaction tx = graphDb.beginTx()) {
			Node tomNode = graphDb.findNodes(
					Labels.ACTOR, "name", name).next();
			Traverser traverser = graphDb.traversalDescription()
				.breadthFirst()
				.evaluator(Evaluators.toDepth(2))
				.relationships(RelationType.ACTING, Direction.BOTH)
				.traverse(tomNode);
			
			for(Path path : traverser) {
				if(path.length() > 1) {
					System.out.println(path.endNode().getProperty("name"));
				}
			}
			tx.success();
		}
	}
	
	// prints the movie titles made in a specific  
	// year using Neo4j’s Cypher Query Language (CQL) 
	public static void printMoviesIn_Cql(int year) {		
		System.out.println("\n--------- Movies in 1980 (CQL)-----------");
		// starting a transaction
		try(Transaction tx = graphDb.beginTx()) {
			Result result = graphDb.execute(
					"match (m:MOVIE) where m.year = " + year + " return m.title");
			while(result.hasNext()) {
				Map<String, Object> row = result.next();
				String movie_title = (String) row.get("m.title");
				System.out.println(movie_title);
			}			
			tx.success();
		}
	}
	
	// prints the names of actors who co-acted with a given actor 
	// using Neo4j’s Cypher Query Language (CQL) 
	public static void printCoStars_Cql(String name) {
		System.out.println("\n--------- Tautou's Co-Stars (CQL)-----------");
		// starting a transaction
		try(Transaction tx = graphDb.beginTx()) {
			Result result = graphDb.execute(
					"match (t:ACTOR)-[*2]-(a:ACTOR) where t.name = '" + name 
					+ "' return distinct a.name");
			while(result.hasNext()) {
				Map<String, Object> row = result.next();
				String actor_name = (String) row.get("a.name");
				System.out.println(actor_name);
			}
			tx.success();
		}
	}
	
	// creates a graph database in the specified path
	private static void createDb() {
		clearDb();
		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File(DB_PATH));
	    registerShutdownHook(graphDb);
	}
	
	// loads movie data from 25 JSON files into a SQL database
	private static void load() throws JsonParseException, JsonMappingException, IOException {
		// parses the JSON files from the movies folder
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		List<Movie> movies = new ArrayList<Movie>();
		for(int i = 1; i < 26; i++) {
			MoviePages page = mapper.readValue(new File((System.getProperty("user.dir") 
					+ "//movies//page" + i + ".json")), MoviePages.class);
			movies.addAll(Arrays.asList(page.getMovies()));
		}
		
		// starting a transaction
		try (Transaction tx = graphDb.beginTx()) {
			Map<String, Node> nodes = new HashMap<String, Node>();
			
			// create a node and properties for each parsed movie
			for(Movie movie : movies) {
				Node movieNode = graphDb.createNode(Labels.MOVIE);

				movieNode.setProperty("title", movie.getTitle());
				movieNode.setProperty("year", movie.getYear());
				movieNode.setProperty("critics_rating", movie.getRatings().getCritics_score());
				movieNode.setProperty("audience_rating", movie.getRatings().getAudience_score());
				movieNode.setProperty("mpaa_rating", movie.getMpaa_rating());
				
				nodes.put(movie.getId(), movieNode);
				
				// get the abridged cast from each parsed movie
				Cast[] casts = movie.getAbridged_cast();
				
				// if casts are not null
				if(casts != null) {
					// create a node and properties for each casted actor
					for(Cast cast : casts) {
						// if the node does not already contain the cast ID, add it
						if(!nodes.containsKey(cast.getId())) {
							Node actorNode = graphDb.createNode(Labels.ACTOR);
							actorNode.setProperty("name", cast.getName());
							nodes.put(cast.getId(), actorNode);
						}
						
						// create relationship between casted actors and movie
						Node actorNode = nodes.get(cast.getId());
						Relationship relationship = actorNode.createRelationshipTo(
								nodes.get(movie.getId()), RelationType.ACTING);
						
						// if casted characters are not null
						if(cast.getCharacters() != null) {
							relationship.setProperty("characters", cast.getCharacters());
						}			
					}
				}				
				// if casts are null
				else {
					return;
				}				
			}				
			tx.success();
		}
	}	
	
	
	// clears the graph database
	private static void clearDb() {
        try {
            FileUtils.deleteRecursively(new File(DB_PATH ));
        } 
        catch (IOException e) {
            throw new RuntimeException( e );
        }
    }
	
	// shuts down the graph database
	private static void shutDown() {
		System.out.println();
        System.out.println("Shutting down database ...");
        graphDb.shutdown();		
	}
	
	// shuts down the graph database when the JVM is exiting
	private static void registerShutdownHook(GraphDatabaseService graphDb2) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                graphDb.shutdown();
            }
        });		
	}
}
