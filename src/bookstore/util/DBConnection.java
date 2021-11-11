package bookstore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private DBConnection() {
	}

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			String dbURL = "jdbc:mysql://localhost:3307/book_store";
			String username = "root";
			String password = "Manoj@3000";

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(dbURL, username, password);
				if (conn != null) {
					System.out.println("DB Connected!!");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}
