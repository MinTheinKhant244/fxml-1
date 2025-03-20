package hmi.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import application.DBConnection;

public class DiscountDAO {
	public static boolean addDiscounts(ArrayList<Product> selections, int percentage, LocalDate sdate, LocalDate edate) throws SQLException {
		
		boolean success = true;
		Connection con = DBConnection.connect();
		String sql = "insert into discounts (product_id, percentage, start_date, end_date) values (?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		for (Product p:selections) {
			stmt.setInt(1, p.getId());
			stmt.setInt(2, percentage);
			stmt.setDate(3, Date.valueOf(sdate));
			stmt.setDate(4, Date.valueOf(edate));
			int rows = stmt.executeUpdate();
			if (rows<=0) {
				success = false;
			}
		}
		
		return success;
		
	}
}
