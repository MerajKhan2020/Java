package grades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 * @author Meraj Khan
 * ITC313 - Programming in Java 2
 * Task 1
 */

public class DBConnect {

	//// Connect to the local InterBase database
	private static Connection conn;
	private static String url = "jdbc:mysql://localhost/gradeprocessing";
	private static String user = "root";
	private static String pass = "";

	public static Connection connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException cnfe) {
			System.err.println("Error: " + cnfe.getMessage());
		} catch (InstantiationException ie) {
			System.err.println("Error: " + ie.getMessage());
		} catch (IllegalAccessException iae) {
			System.err.println("Error: " + iae.getMessage());
		}

		conn = DriverManager.getConnection(url, user, pass);
		return conn;
	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (conn != null && !conn.isClosed())
			return conn;
		connect();
		return conn;

	}
}