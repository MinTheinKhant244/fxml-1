package hmi.model;

import java.sql.*;

import application.DBConnection;

public class LoginDAO {
	public static boolean checkCredential (String username, String password) throws SQLException {
		Connection con = DBConnection.connect();
		if (con != null) {
			System.out.println("Connection Successfully Completed");
			// sql String
			String sql = "select * from users where username like ? and password like ?";
			// sql Statement
//			Statement stmt = con.createStatement();
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);

			//
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				System.out.println(username);
				System.out.println(rs.getInt("id"));
				System.out.println("Login Successful");
				return true;
			}
			else {
				return false;
//				System.out.println("Wrong Username and Password");
			}
		
		}
		return false; 
	}
}
