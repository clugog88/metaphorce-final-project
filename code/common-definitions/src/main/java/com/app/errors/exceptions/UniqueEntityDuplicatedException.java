package com.app.errors.exceptions;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class UniqueEntityDuplicatedException extends AppException {
	
	private static final long serialVersionUID = 1L;

	public UniqueEntityDuplicatedException() {
        super("Unique entity duplicated exception.");
    }
	
	public UniqueEntityDuplicatedException(String message) {
        super(message);
    }
    
}
