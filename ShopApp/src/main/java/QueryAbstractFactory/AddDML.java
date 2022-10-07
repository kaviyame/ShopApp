package QueryAbstractFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.google.gson.Gson;

import database.ConnectDB;
import database.OrdersDB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.OrderRemove;
import model.ShopProduct;

//Concrete class for adding a new product in shop.

public class AddDML implements DML {

	@Override
	public void getDML() {
		System.out.println("add dml");
	}

	@Override
	public void doDMLOperation(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		res.setContentType("application/json");

		ShopProduct shopproduct = new Gson().fromJson(req.getReader(), ShopProduct.class);

		String p_name = shopproduct.getP_name();
		int avlquan = shopproduct.getavlquan();
		int price = shopproduct.getPrice();
		String msg = null;

		PrintWriter out = res.getWriter();
	
		String SQL = "INSERT INTO public.shopproduct(\r\n" + "	p_name, price, quantity)\r\n" + "	VALUES (?, ?, ?);";

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, p_name);
			preparedStatement.setInt(2, price);
			preparedStatement.setInt(3, avlquan);

			if (preparedStatement.executeUpdate() > 0) {
				msg = p_name + " added to products successfully";
			} else {
				msg = "Already exists !!";
			}

		} catch (SQLException e) {
			System.out.println(e);
			msg = "Product already exists !!";

		} catch (NumberFormatException e) {
			System.out.println(e);
			msg = "Ensure you have filled all details";
		} catch (NullPointerException e) {
			System.out.println(e);
			msg = "Ensure you have filled all details";
		}
		out.println("{\"p_name\":\"" + p_name + "\", " + "\"msg\":\"" + msg + "\"}");
	}
}
