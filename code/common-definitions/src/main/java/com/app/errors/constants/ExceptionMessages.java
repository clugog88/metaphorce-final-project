package com.app.errors.constants;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class ExceptionMessages {
	
	public static final String EXCEPTION_MESSAGE__InternalError = "Internal error exception.";
	
	public static final String EXCEPTION_MESSAGE__BadCredentials = "Bad credentials exception.";
	public static final String EXCEPTION_MESSAGE__UsernameNotFound = "Username not found exception";
	
	public static final String EXCEPTION_MESSAGE__EntityNotFound = "Entity not found exception.";
	public static final String EXCEPTION_MESSAGE__UniqueEntityDuplicated = "Unique entity duplicated exception";
	
	private ExceptionMessages() {
		super();
	}
	
}
