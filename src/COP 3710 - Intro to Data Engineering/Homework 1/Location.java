public class Location {
	// instance variables for translating tags from XML
	private String street;
	private String city;
	private String state;
	private String country;
	private String zip;
	private double latitude;
	private double longitude;
	
	// getter and setter for "street"
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	// getter and setter for "city"
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	// getter and setter for "state"
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	// getter and setter for "country"
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	// getter and setter for "zip"	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	// getter and setter for "latitude"
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	// getter and setter for "longitude"
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
