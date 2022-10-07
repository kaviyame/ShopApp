package database;

public class Cart {
	String name;
	String p_name;
	String price;
	String location;

	public Cart(String name, String p_name, String price, String location) {
		this.name = name;
		this.p_name = p_name;
		this.price = price;
		this.location = location;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
