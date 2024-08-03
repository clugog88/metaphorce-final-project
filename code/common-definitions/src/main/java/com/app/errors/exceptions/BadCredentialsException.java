package com.app.errors.exceptions;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class BadCredentialsException extends AppException {
	
	private static final long serialVersionUID = 1L;

	public BadCredentialsException() {
        super("Bad credentials exception.");
    }
	
	public BadCredentialsException(String message) {
        super(message);
    }
    
}
