package org.security.JWTSpring.exp.exception;


public class RestAuthenticationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -207520099207042193L;

	
	public RestAuthenticationException(String message) {
		super(message);
	}
}
