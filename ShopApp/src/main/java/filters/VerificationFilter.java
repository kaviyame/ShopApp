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
import verifchain.*;

import java.io.IOException;

public class VerificationFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public VerificationFilter() {
		super();
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("VerificationFilter initialized");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String verify = req.getParameter("verify");

		Chain verifchain = new Chain();

		verifchain.process(new Type(verify), req, res, chain);

	}

	public void destroy() {
		System.out.println("DBEntryFilter destroyed");
	}
}
