package com.app.errors.exceptions;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class UsernameNotFoundException extends AppException {
	
	private static final long serialVersionUID = 1L;

	public UsernameNotFoundException() {
        super("Username not found exception.");
    }
	
	public UsernameNotFoundException(String message) {
        super(message);
    }
    
}
