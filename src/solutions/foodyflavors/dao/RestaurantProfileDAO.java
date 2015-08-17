package solutions.foodyflavors.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.RestaurantProfile;
import solutions.foodyflavors.utils.DBUtil;

/** Restaurant Profile DAO **/
public class RestaurantProfileDAO {
	
	/**
	 * DAO method to get existing Restaurant Profile from the database
	 */
	public RestaurantProfile getProfile() throws AppException {
		
		
		RestaurantProfile profile = new RestaurantProfile();
	
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM restaurantprofile WHERE rest_id=?");
			ps.setInt(1, 1);
			rs = ps.executeQuery();
			
			if(rs.next()){				
				
				profile.setName(rs.getString("name"));
				profile.setPhone(rs.getString("phone"));
				profile.setEmail(rs.getString("email"));
				profile.setAddress(rs.getString("address"));
				
			}
			
			else {
				throw new AppException("Restaurant Profile does not exist in the system.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching the restaurant profile from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return profile;
	}

	
	/**
	 * DAO method to update Restaurant Profile into the database
	 */
	public RestaurantProfile updateProfile(RestaurantProfile updatedProfile) throws AppException {				
				
				
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		
		try {
			ps = con.prepareStatement("UPDATE restaurantprofile set name=?, phone=?, email=?, address=? WHERE rest_id=?");
			ps.setString(1, updatedProfile.getName());
			ps.setString(2, updatedProfile.getPhone());
			ps.setString(3, updatedProfile.getEmail());
			ps.setString(4, updatedProfile.getAddress());
			ps.setInt(5, 1);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in updating restaurant profile into the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return updatedProfile;
	}


}
