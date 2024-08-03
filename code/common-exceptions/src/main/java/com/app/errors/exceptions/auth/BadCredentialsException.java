package com.app.errors.exceptions.auth;

import com.app.errors.constants.ExceptionMessages;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class BadCredentialsException extends RuntimeException {
	
	private static final long serialVersionUID = -5381203305453991918L;

	public BadCredentialsException() {
        super( ExceptionMessages.EXCEPTION_MESSAGE__BadCredentials );
    }
	
	public BadCredentialsException(String message) {
        super(message);
    }
    
}
