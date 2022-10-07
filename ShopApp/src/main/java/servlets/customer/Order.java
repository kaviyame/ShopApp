package servlets.customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import org.json.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import database.Cart;
import database.ConnectDB;
import database.OrdersDB;
import model.CustomerOrder;

public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Order() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		String username = null;
		try {
			username = (String) session.getAttribute("username");
		} catch (NullPointerException e) {
			out.println("Session not valid. Log in again");
		}

		response.setContentType("application/json");

		CustomerOrder order = new Gson().fromJson(request.getReader(), CustomerOrder.class);

		
		String p_name = order.getP_name();
		int selquantity = order.getSelectedquan();

		OrdersDB ordersDB = new OrdersDB();

		int avlquan = ordersDB.getAvailableQuantity(username, p_name);
		int price = ordersDB.getPrice(username, p_name);
		int ordquan = 0;

		
		try {
			ordersDB.updateShopProdQuantity(username, p_name, selquantity);
			avlquan = ordersDB.getAvailableQuantity(username, p_name);
			try {
				ordersDB.insertOrder(username, p_name, selquantity);
				ordquan = ordersDB.getOrderedQuantity(username, p_name);
				request.setAttribute("msg", "You've ordered " + ordquan + " " + p_name);
			} catch (SQLException e) {
				request.setAttribute("msg", "Couldn't insert order");
			}

			ordersDB.deleteCartItem(username, p_name);
		} catch (SQLException e) {
			ordquan = ordersDB.getOrderedQuantity(username, p_name);
			request.setAttribute("msg", "Check the item quantity");
		}

		out.println("{\"p_name\":\"" + p_name + "\", " + "\"selquantity\":\"" + selquantity + "\", " + "\"avlquan\":\""
				+ avlquan + "\", " + "\"price\":\"" + price + "\", " + "\"ordquan\": \"" + ordquan + "\", "
				+ "\"msg\":\"" + request.getAttribute("msg") + "\"}");

	}

}
