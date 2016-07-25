package mybnb;

public class Listings {
	String type;
	double latitude;
	double longitude;
	String listing_address;
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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude2) {
		this.latitude = latitude2;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getListing_address() {
		return listing_address;
	}

	public void setListing_address(String listing_address) {
		this.listing_address = listing_address;
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
