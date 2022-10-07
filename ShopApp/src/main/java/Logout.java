
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Logout() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession currentsession = request.getSession(false);
		if (currentsession != null && currentsession.getAttribute("userAttr").equals("ShopOwner")) {
			currentsession.invalidate();
			response.getWriter().println("Thanks for selling !! <a href=\"\\ShopApp\">login/register</a>");
		} else if (currentsession != null && currentsession.getAttribute("userAttr").equals("Customer")) {
			currentsession.invalidate();
			response.getWriter().println("Thanks for shopping !! <a href=\"\\ShopApp\">Shop Again</a>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
