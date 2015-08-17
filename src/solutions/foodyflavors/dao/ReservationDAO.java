package solutions.foodyflavors.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.Customer;
import solutions.foodyflavors.model.Reservation;
import solutions.foodyflavors.utils.DBUtil;

/** Reservation DAO **/
public class ReservationDAO {
	
	/**
	 * DAO method to get a list of all Reservations from the database
	 */
	public List<Reservation> getAllReservations() throws AppException {
		
		List<Reservation> resList = new ArrayList<Reservation>();
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM reservation NATURAL JOIN customer WHERE reservation.cust_id = customer.cust_id");
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				Reservation res = new Reservation();
				
				res.setCnfCode(rs.getInt("cnf_code"));
				res.setDate(rs.getDate("date"));
				res.setTime(rs.getString("time"));
				res.setPartySize(rs.getInt("partysize"));
				res.setSpecialEvent(rs.getString("special_event"));
				
				Customer cust = new Customer();
				
				cust.setCustId(rs.getInt("cust_id"));
				cust.setName(rs.getString("name"));
				cust.setPhone(rs.getString("phone"));
				cust.setEmail(rs.getString("email"));
				
				res.setCustomer(cust);

				resList.add(res);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching reservations from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return resList;
	}
	
	
	/**
	 * DAO to get all Past Reservations of an existing Customer from the database
	 */
	public List<Reservation> getPastReservations(int custId) throws AppException {
		
		
		List<Reservation> pastResList = new ArrayList<Reservation>();
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM reservation NATURAL JOIN customer WHERE reservation.cust_id = customer.cust_id AND cust_id=? AND DATE(reservation.date) < DATE(NOW());");
			ps.setInt(1, custId);
			rs = ps.executeQuery();
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				Reservation res = new Reservation();
				
				res.setCnfCode(rs.getInt("cnf_code"));
				res.setDate(rs.getDate("date"));
				res.setTime(rs.getString("time"));
				res.setPartySize(rs.getInt("partysize"));
				res.setSpecialEvent(rs.getString("special_event"));
				
				Customer cust = new Customer();
				
				cust.setCustId(rs.getInt("cust_id"));
				cust.setName(rs.getString("name"));
				cust.setPhone(rs.getString("phone"));
				cust.setEmail(rs.getString("email"));
				
				res.setCustomer(cust);

				pastResList.add(res);

			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching the past reservations for the customer from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return pastResList;
	}

		
	/**
	 * DAO method to get single Existing Reservation from the database
	 */
	public Reservation getReservation(int resId) throws AppException {
		
		
		Reservation res = new Reservation();
		Customer cust = new Customer();
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM reservation NATURAL JOIN customer WHERE reservation.cust_id = customer.cust_id AND cnf_code=?");
			ps.setInt(1, resId);
			rs = ps.executeQuery();
			
			if(rs.next()){				
				res.setCnfCode(rs.getInt("cnf_code"));
				res.setDate(rs.getDate("date"));
				res.setTime(rs.getString("time"));
				res.setPartySize(rs.getInt("partysize"));
				res.setSpecialEvent(rs.getString("special_event"));
				
				cust.setCustId(rs.getInt("cust_id"));
				cust.setName(rs.getString("name"));
				cust.setPhone(rs.getString("phone"));
				cust.setEmail(rs.getString("email"));
				
				res.setCustomer(cust);
			}
			
			else {
				throw new AppException("Reservation with Confirmation Code = " + resId + " does not exist in the system.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching the reservation from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return res;
	}

	
	/**
	 * DAO method to add/create a new Reservation into the database
	 */
	public Reservation createReservation(Reservation res) throws AppException {
		
		
		//Customer cust = new Customer();
		//cust.setName(name);
		CustomerDAO dao = new CustomerDAO();
		Customer cust = dao.createCustomer(res.getCustomer());			
				
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		
		
		try {
			ps = con.prepareStatement("INSERT INTO reservation (date, time, partysize, special_event, cust_id) VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setDate(1, res.getDate());
			ps.setString(2, res.getTime());
			ps.setInt(3, res.getPartySize());
			ps.setString(4, res.getSpecialEvent());
			ps.setInt(5, cust.getCustId());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
			{							
				res.setCnfCode(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in adding reservation into the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return res;
	}
	
	
	
	/**
	 * DAO method to update single existing Reservation into the database
	 */
	public Reservation updateReservation(int resId, Reservation updatedRes) throws AppException {				
				
				
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		
		try {
			ps = con.prepareStatement("UPDATE reservation set date=?, time=?, partysize=? where cnf_code=?");
			ps.setDate(1, updatedRes.getDate());
			ps.setString(2, updatedRes.getTime());
			ps.setInt(3, updatedRes.getPartySize());
			ps.setInt(4, resId);
	
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in updating reservation into the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return updatedRes;
	}


}
