package servlets.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CustomerOrder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import database.ConnectDB;
import database.OrdersDB;

public class OrderRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OrderRemove() {
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

		model.OrderRemove orderRemove = new Gson().fromJson(request.getReader(), model.OrderRemove.class);

		
		String p_name = orderRemove.getP_name();

		int prevquan = ordersDB.getOrderedQuantity(username, p_name);

	

		try {
			ordersDB.updateQuan(username, p_name, prevquan);
			ordersDB.deleteOrder(username, p_name);
			request.setAttribute("msg", "Item removed from orders");
		} catch (SQLException e) {
			request.setAttribute("msg", "Item already removed from orders");
			e.printStackTrace();
		}

		int avlquan = ordersDB.getAvailableQuantity(username, p_name);
		int price = ordersDB.getPrice(username, p_name);

		out.println("{\"p_name\":\"" + p_name + "\", " + "\"avlquan\":\"" + avlquan + "\", " + "\"price\":\"" + price
				+ "\", " + "\"msg\":\"" + request.getAttribute("msg") + "\"}");

	}

}
