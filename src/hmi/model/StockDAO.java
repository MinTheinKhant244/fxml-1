package hmi.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.DBConnection;

public class StockDAO {
	
	public static ArrayList<Stock> getAllStocks() throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "select stocks.id as sid, products.id as pid, products.name as pname, stocks.price as price, stocks.qty as qty, stocks.date as date from stocks join products\r\n"
				+ "where stocks.pid = products.id;";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Stock> stockList = new ArrayList<Stock>();
		
		while (rs.next()) {
			int id = rs.getInt("sid");
			int pid = rs.getInt("pid");
			String pname = rs.getString("pname");
			int price = rs.getInt("price");
			int qty = rs.getInt("qty");
			Date date = rs.getDate("date");
			stockList.add(new Stock(id, pid, pname, price, qty, date));
		}
		
		return stockList;
	}
	
	public static boolean addStock(Stock stock) throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "INSERT INTO stocks(pid, price, qty, date) VALUES (?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, stock.getPid());
		stmt.setInt(2, stock.getPrice());
		stmt.setInt(3, stock.getQty());
		stmt.setDate(4, (Date) stock.getDate());
		int num_rows = stmt.executeUpdate();
		if (num_rows>0) {
			return true;
		}
		return false;
	}
	
	public static boolean updateStock(Stock stock) throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "update stocks set pid = ?, price = ? , qty = ?, date = ? where id = ?";
		PreparedStatement  stmt = con.prepareStatement(sql);
		stmt.setInt(1, stock.getPid());
		stmt.setInt(2, stock.getPrice());
		stmt.setInt(3, stock.getQty());
		stmt.setDate(4, (Date) stock.getDate());
		stmt.setInt(5, stock.getId());
		
		int rows = stmt.executeUpdate();
		if (rows > 0) {
			return true;
		}
		return false;
	}
	
	public static boolean deleteStock(int id) throws SQLException {
		Connection con = DBConnection.connect();
		String sql = "delete from stocks where id = ?";
		PreparedStatement  stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		int rows = stmt.executeUpdate();
		if (rows > 0) {
			return true;
		}
		return false;
	}
	
}
