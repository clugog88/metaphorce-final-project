package com.app.errors.exceptions;

import com.app.errors.constants.ExceptionMessages;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class EntityNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 4021471828998921835L;

	public EntityNotFoundException() {
        super( ExceptionMessages.EXCEPTION_MESSAGE__EntityNotFound );
    }
	
	public EntityNotFoundException(String message) {
        super(message);
    }
    
}
