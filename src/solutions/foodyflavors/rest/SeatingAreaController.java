package solutions.foodyflavors.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import solutions.foodyflavors.dao.SeatingAreaDAO;
import solutions.foodyflavors.exceptions.AppException;
import solutions.foodyflavors.model.SeatingArea;

/** Seating Area Controller **/
@Path("/seatingarea")
public class SeatingAreaController {
	
	/**
	 * REST API method to get a list of all Tables in the Seating Area
	 */
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getAllTables() {
		
		AppResponse resp = new AppResponse();
		
		try {
			SeatingAreaDAO dao = new SeatingAreaDAO();
			List<SeatingArea> tableList = dao.getAllTables();
			resp.setPayload(tableList);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}

	/**
	 *  API method to get single Table already assigned to an existing Reservation
	 */
	@GET
	@Path("/get/{cnfCode}")
	@Produces(MediaType.APPLICATION_JSON)
	public AppResponse getReservedTable(@PathParam("cnfCode") int resId) {
		
		AppResponse resp = new AppResponse();
		
		try {
			SeatingAreaDAO dao = new SeatingAreaDAO();
			SeatingArea table = dao.getReservedTable(resId);
			resp.setPayload(table);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}
	
		
	/**
	 * API method to add or update single Table for an existing Reservation
	 */
	@PUT
	@Path("/put/{tableId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AppResponse updateReservation(@PathParam("tableId") int tabId, SeatingArea updatedTable) {
		
		AppResponse resp = new AppResponse();
		
		try {
			SeatingAreaDAO dao = new SeatingAreaDAO();
			SeatingArea table = dao.updateTable(tabId, updatedTable);
			resp.setMessage("Table has been successfully added/updated into the system.");
			resp.setPayload(table);	
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
			resp.setStatus(AppResponse.ERROR);
			resp.setMessage(e.getMessage());
		}
		return resp;
	}

}
