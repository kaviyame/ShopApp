package verifchain;

import java.io.IOException;
import java.io.PrintWriter;

import database.CheckDB;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

class AuthProcessor extends Processor {
	public AuthProcessor(Processor nextProcessor) {
		super(nextProcessor);

	}

	public void process(Type type, HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException {

		if (type.getType().equals("auth")) {
			try {
				if (isAuthenticated(req, res)) {
					createSession(req, res);
					chain.doFilter(req, res);
				} else {
					PrintWriter out = res.getWriter();
					res.setContentType("text/html");
					out.println("Wrong credentials !!" + "<a href=\"\\ShopApp\">try again</a>");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			super.process(type, req, res, chain);
		}

	}

	private boolean isAuthenticated(HttpServletRequest req, HttpServletResponse res) throws IOException {
		return new CheckDB().authenticate(req, res);
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
