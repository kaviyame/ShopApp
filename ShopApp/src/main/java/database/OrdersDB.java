package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Order;
import model.Product;
import model.ShopProduct;

public class OrdersDB {

	public int getOrderedQuantity(String username, String p_name) {
		String prevselquan = "SELECT quantity " + "FROM public.orders WHERE username=? and"
				+ " s_name='kaviya12' and p_name=?";
		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(prevselquan)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, p_name);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public int getAvailableQuantity(String username, String p_name) {
		String prevselquan = "SELECT quantity " + "FROM public.shopproduct WHERE" + " p_name=?;";
		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(prevselquan)) {

			preparedStatement.setString(1, p_name);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next())
				return rs.getInt(1);

		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public int getPrice(String username, String p_name) {
		String prevselquan = "SELECT price " + "FROM public.shopproduct WHERE" + " p_name=?;";
		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(prevselquan)) {

			preparedStatement.setString(1, p_name);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public void updateShopProdQuantity(String username, String p_name, int selquantity) throws SQLException {
		String SQL = "UPDATE public.shopproduct\r\n" + "SET quantity= quantity - ?  \r\n"
				+ "	WHERE p_name=? and quantity-? >=0 and ? > 0 ;";
		int rs;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setInt(1, selquantity);
			preparedStatement.setString(2, p_name);
			preparedStatement.setInt(3, selquantity);
			preparedStatement.setInt(4, selquantity);

			rs = preparedStatement.executeUpdate();
		}
		if (rs == 0)
			throw new SQLException();
	}

	public void updateOrderQuantity(String username, String p_name, int selquantity, int prevquan) throws SQLException {
		String SQL = "UPDATE public.orders\r\n" + "	SET quantity=?\r\n"
				+ "	WHERE username= ? and s_name='kaviya12' and p_name=?;";
		int rs;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setInt(1, selquantity);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, p_name);

			rs = preparedStatement.executeUpdate();
		}

		if (rs == 0)
			throw new SQLException();
	}

	public void insertOrder(String username, String p_name, int selquantity) throws SQLException {
		String SQL = "INSERT INTO public.orders(username, s_name, p_name, quantity)\r\n"
				+ "	VALUES (?, 'kaviya12', ?, ?);";
		int rs;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, p_name);
			preparedStatement.setInt(3, selquantity);

			rs = preparedStatement.executeUpdate();
		}
		if (rs == 0)
			throw new SQLException();
	}

	public void deleteOrder(String username, String p_name) throws SQLException {
		String SQL = "DELETE FROM public.orders WHERE username= ? and s_name='kaviya12' and p_name=?;";
		int rs;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, p_name);

			rs = preparedStatement.executeUpdate();
		}
		if (rs == 0)
			throw new SQLException();

	}

	public void updateQuan(String username, String p_name, int prevquan) throws SQLException {
		String SQL = "UPDATE public.shopproduct\r\n" + "SET quantity= quantity + ? \r\n" + "	WHERE p_name=? ;";
		int rs;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setInt(1, prevquan);
			preparedStatement.setString(2, p_name);

			rs = preparedStatement.executeUpdate();
		}
		if (rs == 0)
			throw new SQLException();
	}

	public void updateCartQuantity(String username, String p_name, int selquantity, int prevquan) throws SQLException {
		String SQL = "UPDATE public.cart\r\n" + "	SET quantity=?\r\n"
				+ "	WHERE username= ? and s_name='kaviya12' and p_name=?;";
		int rs;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setInt(1, selquantity);
			preparedStatement.setString(2, username);
			preparedStatement.setString(3, p_name);

			rs = preparedStatement.executeUpdate();
		}

		if (rs == 0)
			throw new SQLException();

	}

	public void insertCart(String username, String p_name, int selquantity, int prevquan) throws SQLException {
		String SQL = "INSERT INTO public.cart(username, s_name, p_name, quantity)\r\n"
				+ "	VALUES (?, 'kaviya12', ?, ?);";
		int rs;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, p_name);
			preparedStatement.setInt(3, selquantity);

			rs = preparedStatement.executeUpdate();
		}
		if (rs == 0)
			throw new SQLException();
	}

	public List<Order> getOrders(String username) throws SQLException {
		String p_name = null;
		int ordquan = 0;
		int price = 0;
		int netprice = 0;
		Order order;
		List<Order> L = new ArrayList<Order>();

		String SQL = "SELECT public.orders.p_name, public.orders.quantity, public.shopproduct.price,"
				+ "public.shopproduct.price * public.orders.quantity\r\n"
				+ "FROM public.shopproduct JOIN public.orders ON orders.p_name = shopproduct.p_name "
				+ "WHERE public.orders.username=? and public.orders.s_name='kaviya12'\r\n" + "ORDER BY p_name ASC ;";
		ResultSet rs = null;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, username);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				p_name = rs.getString(1);
				ordquan = rs.getInt(2);
				price = rs.getInt(3);
				netprice = rs.getInt(4);

				order = new Order(username, p_name, ordquan, price, netprice);
				L.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs == null)
			throw new SQLException();

		return L;
	}

	public List<Product> getProducts(String username) throws SQLException {
		String p_name = null;
		int price = 0;
		int avlquan = 0;
		int cartquan = 0;

		Product product;
		List<Product> L = new ArrayList<Product>();

		String SQL = "SELECT p_name, price, quantity, COALESCE( \r\n"
				+ "( SELECT quantity FROM cart WHERE s_name='kaviya12' and p_name=public.shopproduct.p_name and username=?)\r\n"
				+ " , 0) AS quantity\r\n" + "FROM public.shopproduct\r\n" + "ORDER BY p_name ASC;";
		ResultSet rs = null;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, username);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				p_name = rs.getString(1);
				price = rs.getInt(2);
				avlquan = rs.getInt(3);
				cartquan = rs.getInt(4);

				product = new Product(p_name, price, avlquan, cartquan);
				L.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs == null)
			throw new SQLException();

		return L;
	}

	public int getCartQuantity(String username, String p_name) {
		String cartquan = "SELECT quantity " + "FROM public.cart WHERE username=? and"
				+ " s_name='kaviya12' and p_name=?";
		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(cartquan)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, p_name);

			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next())
				return rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e);
		}
		return 0;
	}

	public List<Cart> getCart(String username) throws SQLException {
		String p_name = null;
		int cartquan = 0;
		int price = 0;
		int netprice = 0;
		Cart cart;
		List<Cart> L = new ArrayList<Cart>();

		String SQL = "SELECT public.cart.p_name, public.cart.quantity, public.shopproduct.price,"
				+ "public.shopproduct.price * public.cart.quantity\r\n"
				+ "FROM public.shopproduct JOIN public.cart ON cart.p_name = shopproduct.p_name "
				+ "WHERE public.cart.username=? and public.cart.s_name='kaviya12'\r\n" + "ORDER BY p_name ASC ;";
		ResultSet rs = null;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, username);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				p_name = rs.getString(1);
				cartquan = rs.getInt(2);
				price = rs.getInt(3);
				netprice = rs.getInt(4);

				cart = new Cart(p_name, cartquan, price, netprice);
				L.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs == null)
			throw new SQLException();

		return L;
	}

	public void deleteCartItem(String username, String p_name) throws SQLException {
		String SQL = "DELETE FROM public.cart WHERE username= ? and s_name='kaviya12' and p_name=?;";
		int rs;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, p_name);

			rs = preparedStatement.executeUpdate();
		}
		if (rs == 0)
			throw new SQLException();
	}

	public List<Order> getOrders() throws SQLException {
		String username = null;
		String p_name = null;
		int ordquan = 0;
		int price = 0;
		int netprice = 0;
		Order order;
		List<Order> L = new ArrayList<Order>();

		String SQL = "SELECT public.orders.username, public.orders.p_name, public.orders.quantity, public.shopproduct.price,"
				+ "public.shopproduct.price * public.orders.quantity\r\n"
				+ "FROM public.shopproduct JOIN public.orders ON orders.p_name = shopproduct.p_name "
				+ "WHERE public.orders.s_name='kaviya12'\r\n" + "ORDER BY p_name ASC ;";
		ResultSet rs = null;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				username = rs.getString(1);
				p_name = rs.getString(2);
				ordquan = rs.getInt(3);
				price = rs.getInt(4);
				netprice = rs.getInt(5);

				order = new Order(username, p_name, ordquan, price, netprice);
				L.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs == null)
			throw new SQLException();

		return L;
	}

	public List<ShopProduct> getProducts() throws SQLException {
		String p_name = null;
		int price = 0;
		int avlquan = 0;

		ShopProduct shopproduct;
		List<ShopProduct> L = new ArrayList<ShopProduct>();

		String SQL = "SELECT p_name, price, quantity " + "FROM public.shopproduct\r\n" + "ORDER BY p_name ASC;";
		ResultSet rs = null;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				p_name = rs.getString(1);
				price = rs.getInt(2);
				avlquan = rs.getInt(3);

				shopproduct = new ShopProduct(p_name, price, avlquan);
				L.add(shopproduct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs == null)
			throw new SQLException();

		return L;
	}

}
