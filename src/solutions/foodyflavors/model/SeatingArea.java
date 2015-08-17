package solutions.foodyflavors.model;

/** SeatingArea POJO **/
public class SeatingArea {
	
	private int tableId;
	private int size;
	private String status;
	private String since;
	private int cnfCode;
	
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSince() {
		return since;
	}
	public void setSince(String since) {
		this.since = since;
	}
	public int getCnfCode() {
		return cnfCode;
	}
	public void setCnfCode(int cnfCode) {
		this.cnfCode = cnfCode;
	}
	
}
