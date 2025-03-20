package hmi.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import application.DBConnection;

public class UserDAO {

	public static boolean saveUser (String name, String nrc, String email, String phone, String dob_value, String address, String gender) throws SQLException {
	    Connection con=DBConnection.connect();
	    if(con!=null)
	    {
	    	String sql = "insert into students (username, nrc, email, phone_number, date_of_birth, address, gender) values ('"+name+"', '"+nrc+"', '"+email+"', '"+phone+"', '"+dob_value+"', '"+address+"', '"+gender+"')";
	    	System.out.println(sql);
	    	Statement stmt=con.createStatement();
	      
	//      PreparedStatement stmt=con.prepareStatement(sql);
	//      stmt.setString(1,name);
	//      stmt.setString(2,nrc);
	//      stmt.setString(3,dob);
	//      stmt.setString(4,phone);
	//      stmt.setString(5,email);
	//      stmt.setString(6,gender);
	//      stmt.setString(7,address);
	      
	      
	      if(stmt.executeUpdate(sql)>0)
	      {
	        return true;
	      }
	  }
	    
	    return false;
		}
	}


