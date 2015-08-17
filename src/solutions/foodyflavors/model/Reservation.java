package solutions.foodyflavors.model;

import java.sql.Date;

/** Reservation POJO **/
public class Reservation {
	
	private int cnfCode;
	private Date date;
	private String time;
	private int partySize;
	private String specialEvent;
	private Customer customer;
	
	public int getCnfCode() {
		return cnfCode;
	}
	public void setCnfCode(int cnfCode) {
		this.cnfCode = cnfCode;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getPartySize() {
		return partySize;
	}
	public void setPartySize(int partySize) {
		this.partySize = partySize;
	}
	public String getSpecialEvent() {
		return specialEvent;
	}
	public void setSpecialEvent(String specialEvent) {
		this.specialEvent = specialEvent;
	}
		
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
