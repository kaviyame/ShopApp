package QueryAbstractFactory;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface DML {
	void getDML();

	void doDMLOperation(HttpServletRequest req, HttpServletResponse res) throws IOException;
}
