import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class School {
	// instance variables for translating tags from XML
	private String id;
	private String about;
	private String founded;
	private int likes;
	private String link;
	private Location location;
	private String name;
	private int talking_about_count;
	private String username;
	private String website;
	private int were_here_count;
	
	// getter and setter for "id"
	public String getId() {	
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	// getter and setter for "about"
	public String getAbout() {	
		return about;
	}
	
	public void setAbout(String about) {
		this.about = about;
	}
	
	// getter and setter for "founded"	
	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}

	// getter and setter for "likes"
	public int getLikes() {
		return likes;
	}
	
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	// getter and setter for "link"
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	// getter and setter for "location"
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	// getter and setter for "name"
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	// getter and setter for "count"
	public int getTalking_about_count() {
		return talking_about_count;
	}
	
	public void setTalking_about_count(int talking_about_count) {
		this.talking_about_count = talking_about_count;
	}
	
	// getter and setter for "username"
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	// getter and setter for "website"
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	// getter and setter for "were_here_count"
	public int getWere_here_count() {
		return were_here_count;
	}
	
	public void setWere_here_count(int were_here_count) {
		this.were_here_count = were_here_count;
	}	
}
