package mybnb;

public class Listings {
	String type;
	float latitude;
	float longitude;
	String listing_address;
	String listing_city;
	String listing_country;
	String amenities;
	double rental_price;
	String postal_code;
	
	public double getRental_price() {
		return rental_price;
	}

	public void setRental_price(double rental_price2) {
		this.rental_price = rental_price2;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude2) {
		this.latitude = latitude2;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getListing_address() {
		return listing_address;
	}

	public void setListing_address(String listing_address) {
		this.listing_address = listing_address;
	}
	
	public String getListing_city() {
		return listing_city;
	}

	public void setListing_city(String listing_city) {
		this.listing_city = listing_city;
	}
	
	public String getListing_country() {
		return listing_country;
	}

	public void setListing_country(String listing_country) {
		this.listing_country = listing_country;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public Listings() {
		// TODO Auto-generated constructor stub
	}

}
