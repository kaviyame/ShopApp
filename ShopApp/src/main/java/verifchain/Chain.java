package verifchain;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Chain {
	Processor vchain;

	public Chain() {
		buildChain();
	}

	private void buildChain() {
		vchain = new AuthProcessor(new ValProcessor(null));
	}

	public void process(Type type, HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException {
		vchain.process(type, req, res, chain);
	}

}
