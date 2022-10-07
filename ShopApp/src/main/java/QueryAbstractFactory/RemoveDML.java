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

public class RemoveDML implements DML {

	@Override
	public void getDML() {
		System.out.println("remove DML");
	}

	@Override
	public void doDMLOperation(HttpServletRequest req, HttpServletResponse res) throws IOException {

		HttpSession session = req.getSession(false);

		res.setContentType("application/json");

		OrderRemove orderremove = new Gson().fromJson(req.getReader(), OrderRemove.class);

		String p_name = orderremove.getP_name();
		String msg = null;

		PrintWriter out = res.getWriter();

		OrdersDB ordersDB = new OrdersDB();

		String SQL = "DELETE FROM public.shopproduct\r\n" + "	WHERE p_name= ?;";
		String CartRemove = "DELETE FROM public.cart\r\n" + "	WHERE p_name= ?;";

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL);
				PreparedStatement CartRemoveStatement = connection.prepareStatement(CartRemove)) {

			preparedStatement.setString(1, p_name);
			CartRemoveStatement.setString(1, p_name);
			CartRemoveStatement.executeUpdate();
			if (preparedStatement.executeUpdate() > 0) {
				msg = "Product removed successfully";
			} else {
				msg = "Already removed!!";
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
		out.println("{\"p_name\":\"" + p_name + "\", " + "\"msg\":\"" + msg + "\"}");
	}

}
