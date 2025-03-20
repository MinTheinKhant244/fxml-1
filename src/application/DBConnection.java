package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
//	static က obj မလို
	public static Connection connect() throws SQLException {
		Connection con = null;
		String db_name = "lms";
		String username = "root";
		String password = "1234";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ db_name, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
