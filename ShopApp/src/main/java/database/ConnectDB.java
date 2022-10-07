package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {

	private static volatile ConnectDB dbobj = null;

	private ConnectDB() {
	}

	public static ConnectDB getInstance() {
		if (dbobj == null) {
			synchronized (ConnectDB.class) {
				if (dbobj == null)
					dbobj = new ConnectDB();
			}
		}
		return dbobj;
	}

	public Connection connection() {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection("jdbc:postgresql:ShopAppDb", "postgres", "1234");
			if (conn != null) {
				System.out.println("Connection established");
				return conn;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}