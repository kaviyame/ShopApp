package model;

public class Cart {

	private String p_name;
	private int cartquan;
	private int price;
	private int netprice;

	public Cart(String p_name, int cartquan, int price, int netprice) {
		this.p_name = p_name;
		this.cartquan = cartquan;
		this.price = price;
		this.netprice = netprice;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getCartquan() {
		return cartquan;
	}

	public void setCartquan(int cartquan) {
		this.cartquan = cartquan;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNetprice() {
		return netprice;
	}

	public void setNetprice(int netprice) {
		this.netprice = netprice;
	}

	@Override
	public String toString() {
		return "{\"p_name\": \"" + p_name + "\", \"cartquan\": \"" + cartquan + "\", \"price\": \"" + price
				+ "\", \"netprice\": \"" + netprice + "\"}";
	}

}
