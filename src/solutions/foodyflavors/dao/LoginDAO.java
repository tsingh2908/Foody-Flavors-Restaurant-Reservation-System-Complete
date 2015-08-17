package solutions.foodyflavors.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.Login;
import solutions.foodyflavors.utils.DBUtil;

/** Login DAO **/
public class LoginDAO {

	/**
	 * DAO method to get owner's existing Login Credentials from the database
	 */
	public Login getLoginCredentials() throws AppException {
				
		Login loginCredentials = new Login();
	
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM login");
			
			rs = ps.executeQuery();
			
			if(rs.next()){				
				
				loginCredentials.setOwnerEmail(rs.getString("email"));
				loginCredentials.setOwnerPassword(rs.getString("password"));				
			}
			
			else {
				throw new AppException("Login Credentials does not exist in the system.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching the Login Credentials from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return loginCredentials;
	}

}
