package com.app.errors.exceptions;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.validation.BindingResult;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class InvalidParametersException extends RuntimeException {
	
	private static final long serialVersionUID = 2467582393696771710L;

	public InvalidParametersException(String message) {
		 super( message );
    }
	
	public static InvalidParametersException parseToException(BindingResult bindingResult) {
		Stream<String> stream = bindingResult.getAllErrors()
				.stream()
				.map(o ->o.getDefaultMessage());
		List<String> errors = stream.toList();
		String message = String.join(", ", errors);
		return new InvalidParametersException( message );
	}
	
}
