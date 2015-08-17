package solutions.foodyflavors.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.SeatingArea;
import solutions.foodyflavors.utils.DBUtil;

/** Seating Area DAO **/
public class SeatingAreaDAO {
		
	/**
	 * DAO method to get a list of all Tables from the database
	 */
	public List<SeatingArea> getAllTables() throws AppException {
		
		List<SeatingArea> tableList = new ArrayList<SeatingArea>();
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM seatingarea");
			
			rs = ps.executeQuery();
			
			while(rs.next()){
				
				SeatingArea table = new SeatingArea();
				
				table.setTableId(rs.getInt("table_id"));
				table.setSize(rs.getInt("size"));
				table.setStatus(rs.getString("status"));
				table.setSince(rs.getString("since"));
				table.setCnfCode(rs.getInt("cnf_code"));
				
				tableList.add(table);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching seating area (tables) from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return tableList;
	}

	/**
	 * DAO method to get single Existing Table from the database
	 */
	public SeatingArea getTable(int tabId) throws AppException {
		
		
		SeatingArea table = new SeatingArea();
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM seatingarea WHERE table_id=?");
			ps.setInt(1, tabId);
			rs = ps.executeQuery();
			
			if(rs.next()){				
				table.setTableId(rs.getInt("table_id"));
				table.setSize(rs.getInt("size"));
				table.setStatus(rs.getString("status"));
				table.setSince(rs.getString("since"));
				table.setCnfCode(rs.getInt("cnf_code"));
				
			}
			
			else {
				throw new AppException("Table with Table ID = " + tabId + " does not exist in the system.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching the table from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return table;
	}

	
	/**
	 * DAO method to get single Table already assigned to a Reservation from the database
	 */
	public SeatingArea getReservedTable(int resId) throws AppException {
		
		
		SeatingArea table = new SeatingArea();
		
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * FROM seatingarea WHERE cnf_code=?");
			ps.setInt(1, resId);
			rs = ps.executeQuery();
			
			if(rs.next()){				
				table.setTableId(rs.getInt("table_id"));
				table.setSize(rs.getInt("size"));
				table.setStatus(rs.getString("status"));
				table.setSince(rs.getString("since"));
				table.setCnfCode(rs.getInt("cnf_code"));
				
			}
			
			else {
				throw new AppException("Table for Confirmation Code = " + resId + " does not exist in the system.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in fetching the table from the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return table;
	}

	
	/**
	 * DAO method to add or update single Table for an existing Reservation into the database
	 */
	public SeatingArea updateTable(int tabId, SeatingArea updatedTable) throws AppException {				
				
				
		SeatingArea changedTable = new SeatingArea();
		Connection con = DBUtil.connectToDB();
		
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		
		try {
			
			//release Reserved Table
			if(updatedTable.getStatus().equals("Occupied")){
				
				ps = con.prepareStatement("UPDATE seatingarea set status=?, since=?, cnf_code=? where table_id=?");
				ps.setString(1, "Available");
				ps.setString(2, null);
				ps.setString(3, null);
				ps.setInt(4, tabId);
				
				ps.executeUpdate();
				
				changedTable = this.getTable(updatedTable.getTableId());
		
			}
			else{
				
				//assign New Table
				ps = con.prepareStatement("UPDATE seatingarea set status=?, since=?, cnf_code=? where table_id=?");
				ps.setString(1, "Occupied");
				ps.setString(2, updatedTable.getSince());
				ps.setInt(3, updatedTable.getCnfCode());
				ps.setInt(4, tabId);
				
				ps.executeUpdate();
				
				changedTable = this.getTable(updatedTable.getTableId());
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AppException("Error in adding/updating table into the database.", e.getCause());
		}
		
		finally {
			
			DBUtil.closeResources(ps, rs, con);
		}
		
		return changedTable;
	}

}
