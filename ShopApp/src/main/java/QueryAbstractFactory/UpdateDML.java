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
import model.ShopProduct;

public class UpdateDML implements DML {

	@Override
	public void getDML() {
		System.out.println("update dml");
	}

	@Override
	public void doDMLOperation(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		HttpSession session = req.getSession(false);

		res.setContentType("application/json");

		ShopProduct shopproduct = new Gson().fromJson(req.getReader(), ShopProduct.class);

		String p_name = shopproduct.getP_name();
		int price = shopproduct.getPrice();
		int avlquan = shopproduct.getavlquan();
		String msg = null;

		PrintWriter out = res.getWriter();


		String SQL = "UPDATE public.shopproduct\r\n" + "	SET price= ? , quantity= ? \r\n"
				+ "	WHERE p_name=? and ?>0 and ?>0;";

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			
			preparedStatement.setInt(1, price);
			preparedStatement.setInt(2, avlquan);
			preparedStatement.setString(3, p_name);
			preparedStatement.setInt(4, avlquan);
			preparedStatement.setInt(5, price);

			if (preparedStatement.executeUpdate() > 0) {
				msg = "Product details updated";
			} else {
				msg = "Check price and quantity details !!";
			}

		} catch (SQLException e) {
			System.out.println(e);
			msg = "Check price and quantity details !!";

		} catch (NumberFormatException e) {
			System.out.println(e);
			msg = "Ensure you have filled all details";
		} catch (NullPointerException e) {
			System.out.println(e);
			msg = "Ensure you have filled all details";
		}
		out.println("{\"p_name\":\"" + p_name + "\", " + "\"avlquan\":\"" + avlquan + "\", " + "\"price\":\"" + price
				+ "\", " + "\"msg\":\"" + msg + "\"}");
	}

}
