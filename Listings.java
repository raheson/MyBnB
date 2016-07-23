package myBNB;

public class Listings {
	String type;
	int latitude;
	int longitude;
	String listing_address;
	String amenities;
	int rental_price;
	String postal_code;
	
	public int getRental_price() {
		return rental_price;
	}

	public void setRental_price(int rental_price) {
		this.rental_price = rental_price;
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

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public int getLongitude() {
		return longitude;
	}

	public void setLongitude(int longitude) {
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
