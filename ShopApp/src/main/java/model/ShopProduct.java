package model;

public class ShopProduct {
	private String p_name;
	private int price;
	private int avlquan;

	public ShopProduct(String p_name, int price, int avlquan) {
		this.p_name = p_name;
		this.price = price;
		this.avlquan = avlquan;
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

	@Override
	public String toString() {
		return "{\"p_name\": \"" + p_name + "\", \"price\": \"" + price + "\", \"avlquan\": \"" + avlquan + "\"}";
	}
}
