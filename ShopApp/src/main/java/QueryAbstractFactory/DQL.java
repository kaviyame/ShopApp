package QueryAbstractFactory;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface DQL {
	void getDQL();

	void doDQLOperation(HttpServletRequest req, HttpServletResponse res) throws IOException;
}
