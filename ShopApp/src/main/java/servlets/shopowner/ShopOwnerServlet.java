package servlets.shopowner;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import QueryAbstractFactory.*;

public class ShopOwnerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShopOwnerServlet() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {

		System.out.println("ShopOwnerServlet initialized");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/shopowner_view/orders.html").forward(request, response);

	}

}
