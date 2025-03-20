package hmi.model;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;

import application.DBConnection;

public class CategoryDAO {

	public static boolean addCategory(Category category) throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "INSERT INTO categories(name, description) VALUES (?,?)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, category.getName());
		stmt.setString(2, category.getDescription());
		
		int num_rows = stmt.executeUpdate();
		if (num_rows>0) {
			return true;
		}
		
		return false;
	}
	
	public static ArrayList<Category> getAllCategories() throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "select * from categories";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Category> categoryList = new ArrayList<Category>();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			categoryList.add(new Category(id, name, description));
		}
		
		return categoryList;
		
	}
	
	public static Category getCategory(int cid) throws SQLException {
		
		Connection con = DBConnection.connect();
		String sql = "select * from categories where id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, cid);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Category> categoryList = new ArrayList<Category>();
		if (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String description = rs.getString("description");
			return new Category(id, name, description);
		}
		return null;
	}
	
	public static boolean updateCategory(Category category) throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "update categories set name = ?, description = ? where id = ?";
		PreparedStatement  stmt = con.prepareStatement(sql);
		stmt.setString(1, category.getName());
		stmt.setString(2, category.getDescription());
		stmt.setInt(3, category.getId());
		int rows = stmt.executeUpdate();
		if (rows > 0) {
			return true;
		}
		return false;
	}
	
	public static boolean deleteCategory(int id) throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "delete from categories where id = ?";
		PreparedStatement  stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		int rows = stmt.executeUpdate();
		if (rows > 0) {
			return true;
		}
		return false;
	}
	
}

