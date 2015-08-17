package solutions.foodyflavors.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import java.util.ArrayList;
import java.util.List;

import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.BusinessHours;
import solutions.foodyflavors.model.RestaurantProfile;
import solutions.foodyflavors.utils.DBUtil;

/** Business Hours DAO **/
public class BusinessHoursDAO {
	
	/**
	 * DAO method to get All Restaurant Business Hours/Timings from the database
	 */
	public List<BusinessHours> getAllTimings() throws AppException {
		
		List<BusinessHours> timingsList = new ArrayList<BusinessHours>();
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM businesshours");
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				BusinessHours timings = new BusinessHours();
				
				timings.setDayId(rs.getInt("day_id"));
				timings.setDay(rs.getString("day"));
				timings.setFromTime(rs.getString("from_time"));
				timings.setToTime(rs.getString("to_time"));
				
				timingsList.add(timings);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching business hours/timings from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return timingsList;
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
