package solutions.foodyflavors.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.foodyflavors.dao.BusinessHoursDAO;
import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.BusinessHours;

/** Restaurant Business Hours Controller **/
@Path("/timings")
public class BusinessHoursController {
	
		
	/**
	 * API method to get all Restaurant Business Hours/Timings
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAllTimings() {
		
		AppResponse resp = new AppResponse();
		
		try {
			BusinessHoursDAO dao = new BusinessHoursDAO();
			List<BusinessHours> timingsList = dao.getAllTimings();
			resp.setPayload(timingsList);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
			
}
