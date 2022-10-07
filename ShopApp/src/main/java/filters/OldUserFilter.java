package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class OldUserFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public OldUserFilter() {
		super();
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Olduser filter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession oldsession = req.getSession(false);

		String userType = req.getParameter("userType");
		String userAttr = oldsession != null ? (String) oldsession.getAttribute("userAttr") : null;

		if (oldsession != null && userAttr != null && userAttr.equals(userType)) {

			if (userAttr.equals("Customer")) {
				req.setAttribute("username", oldsession.getAttribute("username"));

				oldsession.setAttribute("selectedprods", null);
				oldsession.setAttribute("location", null);
				req.getRequestDispatcher("/CustomerServlet").forward(req, res);

			} else if (userAttr.equals("ShopOwner")) {

				req.getRequestDispatcher("/ShopOwnerServlet").forward(req, res);
			}
			return;
		}

		chain.doFilter(req, res);

	}

	public void destroy() {
		System.out.println("Old user filter destroyed");
	}

}
