package com.app.errors.exceptions;

import lombok.NoArgsConstructor;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@NoArgsConstructor
public class AppException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public AppException(String message) {
        super( message );
    }
    
}
