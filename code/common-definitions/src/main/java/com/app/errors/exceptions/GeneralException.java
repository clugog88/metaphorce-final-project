package com.app.errors.exceptions;

import com.app.errors.constants.ExceptionMessages;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class GeneralException extends RuntimeException {
	
	private static final long serialVersionUID = 7530910204862417619L;

	public GeneralException() {
        super( ExceptionMessages.EXCEPTION_MESSAGE__InternalError );
    }
	
	public GeneralException(String message) {
        super(message);
    }
    
}
