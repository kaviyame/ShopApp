package model;

public class Order {

	private String username;
	private String p_name;
	private int ordquan;
	private int price;
	private int netprice;

	public Order(String username, String p_name, int ordquan, int price, int netprice) {
		this.username = username;
		this.p_name = p_name;
		this.ordquan = ordquan;
		this.price = price;
		this.netprice = netprice;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public int getOrdquan() {
		return ordquan;
	}

	public void setOrdquan(int ordquan) {
		this.ordquan = ordquan;
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
		return "{\"username\": \"" + username + "\", " + "\"p_name\": \"" + p_name + "\", \"ordquan\": \"" + ordquan
				+ "\", \"price\": \"" + price + "\", \"netprice\": \"" + netprice + "\"}";
	}

}
