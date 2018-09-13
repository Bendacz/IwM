package networking.rmi.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DatabaseConnection {

	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				DriverManager.registerDriver(new Driver());
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafxrmi", "javafx", "javafx");
			} catch (SQLException | ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		return connection;
	}

}
