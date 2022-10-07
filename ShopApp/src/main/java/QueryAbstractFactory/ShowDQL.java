package QueryAbstractFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.ConnectDB;
import database.OrdersDB;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ShopProduct;

//Concrete class to show products in the Shop

public class ShowDQL implements DQL {

	@Override
	public void getDQL() {
		System.out.println("show DQL");
	}

	@Override
	public void doDQLOperation(HttpServletRequest req, HttpServletResponse res) throws IOException {

		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		OrdersDB ordersDB = new OrdersDB();

		try {

			List<ShopProduct> L = ordersDB.getProducts();

			out.print("{\"products\":[");

			for (int i = 0; i < L.size(); i++) {
				if (i == L.size() - 1)
					out.print(L.get(i).toString());
				else
					out.print(L.get(i).toString() + ",");
			}
			out.print("]}");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			out.println("Session not valid. Log in again");
		}

	}
}
