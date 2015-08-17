package solutions.foodyflavors.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



import solutions.foodyflavors.dao.ReservationDAO;
import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.Reservation;

/** Reservation Controller **/
@Path("/reservation")
public class ReservationController {
	
	/**
	 * REST API method to get a list of all Reservations
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAllReservations() {
		
		AppResponse resp = new AppResponse();
		
		try {
			ReservationDAO dao = new ReservationDAO();
			List<Reservation> resList = dao.getAllReservations();
			resp.setPayload(resList);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	
	/**
	 * REST API method to add/create a new Reservation 
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse createReservation(Reservation res){
		
		AppResponse resp = new AppResponse();
		
		try {
			ReservationDAO dao = new ReservationDAO();
			res = dao.createReservation(res);
			resp.setMessage("Reservation has been added into the system.");
			resp.setPayload(res);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
	/**
	 * API method to get all Past Reservations of an existing Customer
	 */
	@GET
	@Path("/get/past/{custId}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getPastReservations(@PathParam("custId") int custId) {
		
		AppResponse resp = new AppResponse();
		
		try {
			ReservationDAO dao = new ReservationDAO();
			List<Reservation> pastResList = dao.getPastReservations(custId);
			if(pastResList.isEmpty()){
				resp.setMessage("No Past Reservations exist for this customer.");
			}
			resp.setPayload(pastResList);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
		
	/**
	 *API method to get single existing Reservation
	 */
	@GET
	@Path("/get/{cnfCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getReservation(@PathParam("cnfCode") int resId) {
		
		AppResponse resp = new AppResponse();
		
		try {
			ReservationDAO dao = new ReservationDAO();
			Reservation res = dao.getReservation(resId);
			resp.setPayload(res);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	

	/**
	 *API method to edit/update single existing Reservation
	 */
	@PUT
	@Path("/put/{cnfCode}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse updateReservation(@PathParam("cnfCode") int resId, Reservation updatedRes) {
		
		AppResponse resp = new AppResponse();
		
		try {
			ReservationDAO dao = new ReservationDAO();
			Reservation res = dao.updateReservation(resId, updatedRes);
			resp.setMessage("Reservation has been successfully updated into the system.");
			resp.setPayload(res);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
		
}
