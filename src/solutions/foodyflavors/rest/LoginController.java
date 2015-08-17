package solutions.foodyflavors.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.foodyflavors.dao.LoginDAO;
import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.Login;

/** Login Controller **/
@Path("/login")
public class LoginController {
	
	/**
	 * API method to get owner's existing Login Credentials
	 */
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getLoginCredentials() {
		
		AppResponse resp = new AppResponse();
		
		try {
			LoginDAO dao = new LoginDAO();
			Login loginCredentials = dao.getLoginCredentials();
			resp.setPayload(loginCredentials);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
}
