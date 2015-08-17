package solutions.foodyflavors.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.foodyflavors.dao.CustomerDAO;
import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.Customer;

/** Customer Controller **/
@Path("/customer")
public class CustomerController {
	
	/**
	 * API method to get a list of all Customers
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAllCustomers() {
		
		AppResponse resp = new AppResponse();
		
		try {
			CustomerDAO dao = new CustomerDAO();
			List<Customer> custList = dao.getAllCustomers();
			resp.setPayload(custList);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	
	/**
	 * API method to get single existing Customer
	 */
	@GET
	@Path("/get/{custId}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getCustomer(@PathParam("custId") int custId) {
		
		AppResponse resp = new AppResponse();
		
		try {
			CustomerDAO dao = new CustomerDAO();
			Customer cust = dao.getCustomer(custId);
			resp.setPayload(cust);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	/**
	 * API method to add/create a new Customer 
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse createCustomer(Customer cust){
		
		AppResponse resp = new AppResponse();
		
		try {
			CustomerDAO dao = new CustomerDAO();
			cust = dao.createCustomer(cust);
			
			resp.setMessage("Customer has been added into the system.");
			resp.setPayload(cust);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
}
