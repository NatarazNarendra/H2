package in.niraj.spring.springbootjpah2.controller.exceptions;



public class CustomerNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5924745325157656168L;

	public CustomerNotFoundException(String message) {
		super(message);
	}
	
}