package com.app.errors.utils;

import com.app.errors.exceptions.AppException;
import com.app.errors.exceptions.GeneralException;

public class ExceptionUtil {
	
	public static void handleException(Exception e) throws RuntimeException {
		if(e instanceof AppException) {
			throw (AppException) e;
		}
		
		/**
		{
		    "type": "about:blank",
		    "title": "Not Found",
		    "status": 404,
		    "detail": "Error interno al realizar la operacion solicitada.",
		    "instance": "/user/test"
		}
		 */
//		throw new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessages.ERROR_MESSAGE__GeneralError, e);
		
		throw new GeneralException();
	}

}
