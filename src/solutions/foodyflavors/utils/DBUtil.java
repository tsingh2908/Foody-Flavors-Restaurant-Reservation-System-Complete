package solutions.foodyflavors.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** Custom Database Utility File for handling database connections for backend MySQL database using JDBC driver **/
public class DBUtil {
	
	public final static String URL = "jdbc:mysql://localhost:3306/foody_flavors_db";
	public final static String USER = "root";
	public final static String PASSWORD = "MomIsGr8";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//connect to database using JDBC driver
	public static Connection connectToDB(){
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection is successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Error establishing the connection" + e.getMessage());
		}
				
		return con;
	}
	
	//close all the open database resources when not in use
	public static void closeResources(PreparedStatement ps, ResultSet rs, Connection con) {
		try {
			if(ps != null){
				ps.close();
			}
			if(rs != null){
				rs.close();
			}
			if(con != null){
				con.close();
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		DBUtil.connectToDB();
	}

}
