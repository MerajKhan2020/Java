package grades;

/**
 * 
 * @author Meraj Khan
 * ITC313 - Programming in Java 2
 * Task 1
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.*;

// InsertApp class handles the process of inserting the values in database. 
public class InsertApp {
	public static final String userName = "root";
	public static final String passWord = "";
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost/gradeprocessing";

	public void insert(int ID, String name, double quiz, double a1, double a2, double a3, double exam, Double result,
			String grade) {
		
		//Build a SQL INSERT statement
		String sql = "INSERT INTO java2 (StudentId, StudentName, Quiz, A1, A2, A3, Exam, Result, Grade) VALUES (?,?,?,?,?,?,?,?,?)";

		try {
			Class.forName(driver); // loading the driver
			Connection connection = DriverManager.getConnection(url, userName, passWord); // Connect to the local InterBase database
			System.out.println("Connected to Database!");
			
			//Prepare statement to process SQL query 
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, ID);
			pstmt.setString(2, name);
			pstmt.setDouble(3, quiz);
			pstmt.setDouble(4, a1);
			pstmt.setDouble(5, a2);
			pstmt.setDouble(6, a3);
			pstmt.setDouble(7, exam);
			pstmt.setDouble(8, result);
			pstmt.setString(9, grade);
			pstmt.executeUpdate();
			

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
