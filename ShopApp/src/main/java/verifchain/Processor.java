package verifchain;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

abstract class Processor {
	private Processor nextProcessor;

	public Processor(Processor nextProcessor) {
		this.nextProcessor = nextProcessor;
	}

	public void process(Type type, HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException {
		if (nextProcessor != null)
			nextProcessor.process(type, req, res, chain);

	}
}