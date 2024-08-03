package com.app.errors.exceptions;

import com.app.errors.constants.ExceptionMessages;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class UniqueEntityDuplicatedException extends RuntimeException {
	
	private static final long serialVersionUID = -5681588653352141673L;

	public UniqueEntityDuplicatedException() {
        super( ExceptionMessages.EXCEPTION_MESSAGE__UniqueEntityDuplicated );
    }
	
	public UniqueEntityDuplicatedException(String message) {
        super(message);
    }
    
}
