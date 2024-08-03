package com.app.errors.constants;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class ErrorMessages {
	
	public static final String ERROR_MESSAGE__OK = "Succesful transaction.";
	public static final String ERROR_MESSAGE__GeneralError = "Internal error when performing the requested operation.";
	
	public static final String ERROR_MESSAGE__EntityNotFound = "Searched entity not registered.";
	public static final String ERROR_MESSAGE__UniqueEntityDuplicated = "Error when trying to duplicate an existing unique entity.";
	
	public static final String ERROR_MESSAGE__AccessDenied = "Access denied.";

	private ErrorMessages() {
		super();
	}
	
}
