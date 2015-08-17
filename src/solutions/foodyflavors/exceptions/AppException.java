package solutions.foodyflavors.exceptions;

/** Customer Exception Handling File for REST API **/
public class AppException extends Exception {
	
	private static final long serialVersionUID = 6634430340553192957L;

	public AppException(String msg) {
		super(msg);
	}
	
	public AppException(String msg, Throwable cause){
		super(msg, cause);
	}

}
