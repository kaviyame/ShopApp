package verifchain;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Enumeration;

import database.EntryDB;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

class ValProcessor extends Processor {
	public ValProcessor(Processor nextProcessor) {
		super(nextProcessor);
	}

	public void process(Type type, HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException {
		if (type.getType().equals("validate")) {
			try {
				if (isValidated(req, res)) {
					try {
						new EntryDB().doInsert(req, res);
						createSession(req, res);
						chain.doFilter(req, res);

					} catch (SQLException e) {
						PrintWriter out = res.getWriter();
						res.setContentType("text/html");
						out.println("Username already exists");
						out.println("<a href=\"\\ShopApp\">try again</a>");
						e.printStackTrace();
					}
				} else {
					PrintWriter out = res.getWriter();
					res.setContentType("text/html");
					out.println("<a href=\"\\ShopApp\">try again</a>");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			super.process(type, req, res, chain);
		}

	}

	private boolean isValidated(HttpServletRequest req, HttpServletResponse res) throws IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		Enumeration<String> e = req.getParameterNames();
		String parameter;
		boolean result = true;
		while (e.hasMoreElements()) {
			String attribute = e.nextElement();
			parameter = req.getParameter(attribute);
			if (parameter.equals("")) {
				out.println(attribute + " should contain atleast one character<br>");
				result = false;
			} else {
				if (attribute.equals("shopownername") && !req.getParameter(attribute).matches("[a-zA-Z]+")) {
					result = false;
					out.println("ShopOwner Name should contain only alphabets<br><br>");
				}
				if (attribute.equals("name") && !req.getParameter(attribute).matches("[a-zA-Z]+")) {
					result = false;
					out.println("Name should contain only alphabets<br><br>");
				}
				if (attribute.equals("age") && !(Integer.parseInt(req.getParameter("age")) >= 0)) {
					result = false;
					out.println("Name should contain only alphabets<br><br>");
				}
				if (attribute.equals("username") && !req.getParameter("username").matches("[a-zA-Z0-9]+")) {
					result = false;
					out.println("Username should contain only digits and alphabets<br><br>");
				}
				if (attribute.equals("password") && !req.getParameter("password")
						.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")) {
					result = false;
					out.println(
							"Password should contain minimum eight characters,<br>at least one letter, one number and one special character<br><br>");
				}
				if (attribute.equals("confirmpassword")
						&& !req.getParameter("confirmpassword").equals(req.getParameter("password"))) {
					result = false;
					out.println("Password didn't match the confirm password<br><br>");
				}
				if (attribute.equals("location") && !req.getParameter("location").matches("[a-zA-Z]+")) {
					result = false;
					out.println(
							"Location should be [dindigul,chennai,coimbatore,madurai,trichy tenkasi,erode,kerala]<br><br>");
				}
				if (attribute.equals("contact") && !req.getParameter("contact").matches("[0-9]{10,}")) {
					result = false;
					out.println("Contact no should be of 10 digits<br><br>");
				}
			}
		}

		return result;
	}

	private void createSession(HttpServletRequest req, HttpServletResponse res) {
		HttpSession oldsession = req.getSession(false);
		HttpSession session;

		String userAttr = req.getParameter("userType");
		if (oldsession != null)
			oldsession.invalidate();
		session = req.getSession();
		session.setAttribute("userAttr", userAttr);
		session.setAttribute("selectedprods", null);
		session.setAttribute("location", null);
		session.setAttribute("username", req.getParameter("username"));

	}
}
