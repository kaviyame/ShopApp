package model;

public class Product {
	private String p_name;
	private int price;
	private int avlquan;
	private int cartquan = 0;

	public Product(String p_name, int price, int avlquan, int cartquan) {
		this.p_name = p_name;
		this.price = price;
		this.avlquan = avlquan;
		this.cartquan = cartquan;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getavlquan() {
		return avlquan;
	}

	public void setavlquan(int avlquan) {
		this.avlquan = avlquan;
	}

	public int getcartquan() {
		return cartquan;
	}

	public void setcartquan(int cartquan) {
		this.cartquan = cartquan;
	}

	@Override
	public String toString() {
		return "{\"p_name\": \"" + p_name + "\", \"price\": \"" + price + "\", \"avlquan\": \"" + avlquan
				+ "\", \"cartquan\": \"" + cartquan + "\"}";
	}
}
