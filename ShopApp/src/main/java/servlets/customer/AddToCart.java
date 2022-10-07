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
import model.CustomerCart;
import model.CustomerOrder;

public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddToCart() {
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

		CustomerCart cart = new Gson().fromJson(request.getReader(), CustomerCart.class);

		System.out.println(cart.getP_name());
		System.out.println(cart.getSelectedquan());
		String p_name = cart.getP_name();
		int selquantity = cart.getSelectedquan();

		OrdersDB ordersDB = new OrdersDB();

		int prevquan = ordersDB.getCartQuantity(username, p_name);
		int avlquan = ordersDB.getAvailableQuantity(username, p_name);
		int price = ordersDB.getPrice(username, p_name);
		int cartquan = ordersDB.getCartQuantity(username, p_name);

		int netprice = prevquan * price;

		System.out.println(
				username + " " + " p_name " + p_name + " selquantity " + selquantity + " prevquan " + prevquan);

		avlquan = ordersDB.getAvailableQuantity(username, p_name);

		if (prevquan > 0 && selquantity <= avlquan) {
			try {
				ordersDB.updateCartQuantity(username, p_name, selquantity, prevquan);
				netprice = selquantity * price;
				cartquan = ordersDB.getCartQuantity(username, p_name);
				request.setAttribute("msg", p_name + " quantity changed to" + selquantity);
			} catch (SQLException e) {
				request.setAttribute("msg", "Couldn't update order");
			}
		} else if (selquantity <= avlquan) {
			try {
				ordersDB.insertCart(username, p_name, selquantity, prevquan);
				netprice = selquantity * price;
				cartquan = ordersDB.getCartQuantity(username, p_name);
				request.setAttribute("msg", selquantity + " " + p_name + " added to cart");
			} catch (SQLException e) {
				request.setAttribute("msg", "Couldn't insert order");
			}
		} else {
			request.setAttribute("msg", "Check the item quantity");
		}

		out.println("{\"p_name\":\"" + p_name + "\", " + "\"selquantity\":\"" + selquantity + "\", " + "\"avlquan\":\""
				+ avlquan + "\", " + "\"price\":\"" + price + "\", " + "\"netprice\":\"" + netprice + "\", "
				+ "\"cartquan\": \"" + cartquan + "\", " + "\"msg\":\"" + request.getAttribute("msg") + "\"}");

	}

}
