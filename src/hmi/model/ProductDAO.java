package hmi.model;

import java.sql.*;
import java.util.ArrayList;

import application.DBConnection;

public class ProductDAO {

	public static boolean addUser(Product product) throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "INSERT INTO products(name, price, category_id, description) VALUES (?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getName());
		stmt.setInt(2, product.getPrice());
		stmt.setInt(3, product.getCid());
		stmt.setString(4, product.getDescription());
		int num_rows = stmt.executeUpdate();
		if (num_rows>0) {
			return true;
		}
		
		return false;
	}
	
	public static ArrayList<Product> getAllProducts() throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "select * from products";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Product> productList = new ArrayList<Product>();
		
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int cid = rs.getInt("category_id");
			String description = rs.getString("description");
			Category cat = CategoryDAO.getCategory(cid);
			productList.add(new Product(id, name, price, cid, description, cat.getName()));
		}
		
		return productList;
		
	}
	
	public static boolean updateProduct(Product product) throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "update products set name = ?, price = ? , category_id = ?, description = ? where id = ?";
		PreparedStatement  stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getName());
		stmt.setInt(2, product.getPrice());
		stmt.setInt(3, product.getCid());
		stmt.setString(4, product.getDescription());
		stmt.setInt(5, product.getId());
		
		int rows = stmt.executeUpdate();
		if (rows > 0) {
			return true;
		}
		return false;
	}
	
	public static boolean deleteProduct(int id) throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "delete from products where id = ?";
		PreparedStatement  stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		int rows = stmt.executeUpdate();
		if (rows > 0) {
			return true;
		}
		return false;
	}
	
	public static Product getProduct(int pid) throws SQLException {
		
		Connection con = DBConnection.connect();
		String sql = "select * from products where id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, pid);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Product> categoryList = new ArrayList<Product>();
		if (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int price = rs.getInt("price");
			int cid = rs.getInt("category_id");
			String description = rs.getString("description");
			return new Product(id, name, price, cid, description);
		}
		return null;
	}
	
}

