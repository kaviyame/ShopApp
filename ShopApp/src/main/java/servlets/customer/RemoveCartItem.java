package servlets.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CustomerCartItemRemove;
import model.CustomerOrder;
import model.OrderRemove;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import database.ConnectDB;
import database.OrdersDB;

public class RemoveCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveCartItem() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		OrdersDB ordersDB = new OrdersDB();

		String username = null;
		try {
			username = (String) session.getAttribute("username");
		} catch (NullPointerException e) {
			out.println("Session not valid. Log in again");
		}

		CustomerCartItemRemove removeItem = new Gson().fromJson(request.getReader(), CustomerCartItemRemove.class);

		
		String p_name = removeItem.getP_name();

		

		try {
			ordersDB.deleteCartItem(username, p_name);
			request.setAttribute("msg", "Item removed from cart");
		} catch (SQLException e) {
			request.setAttribute("msg", "Item already removed from cart");
			e.printStackTrace();
		}

		int cartquan = ordersDB.getCartQuantity(username, p_name);
		int price = ordersDB.getPrice(username, p_name);

		out.println("{\"p_name\":\"" + p_name + "\", " + "\"cartquan\":\"" + cartquan + "\", " + "\"price\":\"" + price
				+ "\", " + "\"msg\":\"" + request.getAttribute("msg") + "\"}");

	}

}
