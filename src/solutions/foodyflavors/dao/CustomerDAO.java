package solutions.foodyflavors.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.Customer;
import solutions.foodyflavors.utils.DBUtil;

/** Customer DAO **/
public class CustomerDAO {
	
	/**
	 * DAO method to get a list of all Customers from the database
	 */
	public List<Customer> getAllCustomers() throws AppException {
		
		List<Customer> custList = new ArrayList<Customer>();
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM customer");
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				Customer cust = new Customer();
				
				cust.setCustId(rs.getInt("cust_id"));
				cust.setName(rs.getString("name"));
				cust.setPhone(rs.getString("phone"));
				cust.setEmail(rs.getString("email"));
			
				custList.add(cust);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching customers from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return custList;
	}
	
	/**
	 * DAO method to get single existing Customer from the database
	 */
	public Customer getCustomer(int custId) throws AppException {
		
		Customer cust = new Customer();
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM customer WHERE cust_id=?");
			ps.setInt(1, custId);
			rs = ps.executeQuery();
			
			if(rs.next()){				
				cust.setCustId(rs.getInt("cust_id"));
				cust.setName(rs.getString("name"));
				cust.setPhone(rs.getString("phone"));
				cust.setEmail(rs.getString("email"));
			}
			
			else {
				throw new AppException("Customer with ID = " + custId + " does not exist in the system.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching customer from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return cust;
	}

		
	/**
	 * DAO method to add/create a new Customer into the database
	 */
	public Customer createCustomer(Customer cust) throws AppException {
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("INSERT INTO customer (name, phone, email) VALUES (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, cust.getName());
			ps.setString(2, cust.getPhone());
			ps.setString(3, cust.getEmail());
			
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next())
			{							
				cust.setCustId(rs.getInt(1));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in adding customer into the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return cust;
	}
	
}
