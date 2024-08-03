package com.app.errors.exceptions;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class EntityNotFoundException extends AppException {
	
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException() {
        super("Entity not found exception.");
    }
	
	public EntityNotFoundException(String message) {
        super(message);
    }
    
}
