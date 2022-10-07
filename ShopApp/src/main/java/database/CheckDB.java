package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckDB {
	public boolean authenticate(HttpServletRequest req, HttpServletResponse res) {
		String userAttr = req.getParameter("userType");

		String SQL = "select * from public.\"" + userAttr + "\" where username= ? and password=?;";
		ResultSet result;

		ConnectDB dao = ConnectDB.getInstance();

		try (Connection connection = dao.connection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, req.getParameter("username"));
			preparedStatement.setString(2, req.getParameter("password"));

			System.out.println(preparedStatement);

			result = preparedStatement.executeQuery();
			if (result.next())
				return true;
			else {

				System.out.println("Authentication result : false ");
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
}
