package com.app.errors.exceptions.auth;

import com.app.errors.constants.ExceptionMessages;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class UsernameNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 4159485892217925233L;

	public UsernameNotFoundException() {
        super( ExceptionMessages.EXCEPTION_MESSAGE__UsernameNotFound );
    }
	
	public UsernameNotFoundException(String message) {
        super(message);
    }
    
}
