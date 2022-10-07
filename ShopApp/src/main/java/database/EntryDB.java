package database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EntryDB {
	public void doInsert(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {

		HttpSession session = req.getSession(false);

		String userAttr = req.getParameter("userType");

		String SQL = null;

		ConnectDB dao = ConnectDB.getInstance();

		if (userAttr.equals("Customer")) {
			SQL = "INSERT INTO public.\"Customer\"(\r\n" + "	name, username, password, contact, gender, age)\r\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?);";
		} else if (userAttr.equals("Shop Owner")) {
			SQL = "INSERT INTO public.\"ShopOwner\"(\r\n"
					+ "	name, username, password, contact, shopownername, location)\r\n"
					+ "	VALUES (?, ?, ?, ?, ?, ?);";
		}

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, req.getParameter("name"));
			preparedStatement.setString(2, req.getParameter("username"));
			preparedStatement.setString(3, req.getParameter("password"));
			preparedStatement.setString(4, req.getParameter("contact"));

			if (userAttr.equals("Customer")) {
				preparedStatement.setString(5, req.getParameter("gender"));
				preparedStatement.setString(6, req.getParameter("age"));
			} else if (userAttr.equals("Shop Owner")) {
				preparedStatement.setString(5, req.getParameter("shopownername"));
				preparedStatement.setString(6, req.getParameter("location"));
			}

			preparedStatement.executeUpdate();

			HttpSession oldsession = req.getSession(false);

			if (oldsession != null)
				oldsession.invalidate();

			session = req.getSession();
			session.setAttribute("userAttr", userAttr);

			session.setAttribute("username", req.getParameter("username"));
			session.setAttribute("list", null);

		}

	}
}
