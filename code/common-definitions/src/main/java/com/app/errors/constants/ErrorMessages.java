package com.app.errors.constants;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class ErrorMessages {
	
	public static final String ERROR_MESSAGE__OK = "Succesful transaction.";
	public static final String ERROR_MESSAGE__AccessDenied = "Access denied.";
	public static final String ERROR_MESSAGE__GeneralError = "Internal error while executing the requested operation.";
	
	public static final String ERROR_MESSAGE__EntityNotFound = "Searched entity not registered.";
	public static final String ERROR_MESSAGE__UniqueEntityDuplicated = "Error when trying to duplicate an existing unique entity.";
	
	private ErrorMessages() {
		super();
	}
	
}
