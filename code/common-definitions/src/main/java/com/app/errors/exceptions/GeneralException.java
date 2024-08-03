package com.app.errors.exceptions;

import com.app.errors.constants.ErrorMessages;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class GeneralException extends AppException {
	
	private static final long serialVersionUID = 1L;

	public GeneralException() {
        super( ErrorMessages.ERROR_MESSAGE__GeneralError );
    }
	
	public GeneralException(String message) {
        super(message);
    }
    
}
