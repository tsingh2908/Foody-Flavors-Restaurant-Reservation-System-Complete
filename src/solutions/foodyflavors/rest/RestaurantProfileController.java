package solutions.foodyflavors.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.foodyflavors.dao.RestaurantProfileDAO;
import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.RestaurantProfile;

/** Restaurant Profile Controller **/
@Path("/profile")
public class RestaurantProfileController {
		
	/**
	 * API method to get existing Restaurant Profile
	 */
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getProfile() {
		
		AppResponse resp = new AppResponse();
		
		try {
			RestaurantProfileDAO dao = new RestaurantProfileDAO();
			RestaurantProfile profile = dao.getProfile();
			resp.setPayload(profile);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	/**
	 * API method to update Restaurant Profile
	 */
	@PUT
	@Path("/put")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse updateProfile(RestaurantProfile updatedProfile) {
		
		AppResponse resp = new AppResponse();
		
		try {
			RestaurantProfileDAO dao = new RestaurantProfileDAO();
			RestaurantProfile profile = dao.updateProfile(updatedProfile);
			resp.setMessage("Restaurant Profile has been successfully updated into the system.");
			resp.setPayload(profile);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
		
}
