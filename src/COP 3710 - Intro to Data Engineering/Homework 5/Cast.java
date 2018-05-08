package movies_parsing;

public class Cast {
	
	// instance variables
	private String name;
	private String id;
	private String [] characters;
	
	// getter and setter for name
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// getter and setter for id
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	// getter and setter for characters
	public String[] getCharacters() {
		return characters;
	}
	
	public void setCharacters(String[] characters) {
		this.characters = characters;
	}	
}
