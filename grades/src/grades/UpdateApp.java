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

//InsertApp class handles the process of updating the values in database. 
public class UpdateApp {
	public static final String userName = "root";
	public static final String passWord = "";
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://localhost/gradeprocessing";

	public void update(String name, double quiz, double a1, double a2, double a3, double exam, Double result,
			String grade, int ID) {
		
		//Build a SQL UPDATE statement
		String sql = "Update java2 set StudentName = ?, Quiz = ?, A1 = ?, A2 = ?, A3 = ?, Exam = ?, Result = ?, Grade = ? Where StudentId = ?";

		try {
			Class.forName(driver); // loading the driver
			Connection connection = DriverManager.getConnection(url, userName, passWord); // Connect to the local InterBase database
			System.out.println("Connected to Database!");
			
			//Prepare statement to process SQL query 
			PreparedStatement pstmt1 = connection.prepareStatement(sql);
			pstmt1.setString(1, name);
			pstmt1.setDouble(2, quiz);
			pstmt1.setDouble(3, a1);
			pstmt1.setDouble(4, a2);
			pstmt1.setDouble(5, a3);
			pstmt1.setDouble(6, exam);
			pstmt1.setDouble(7, result);
			pstmt1.setString(8, grade);
			pstmt1.setInt(9, ID);
			pstmt1.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

}
