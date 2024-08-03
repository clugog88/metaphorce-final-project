package com.app.errors.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.auth.controllers.models.GenericResponse;
import com.app.errors.constants.ErrorMessages;
import com.app.errors.exceptions.BadCredentialsException;
import com.app.errors.exceptions.EntityNotFoundException;
import com.app.errors.exceptions.GeneralException;
import com.app.errors.exceptions.InvalidParametersException;
import com.app.errors.exceptions.UniqueEntityDuplicatedException;
import com.app.errors.exceptions.UsernameNotFoundException;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@RestControllerAdvice
public class ResponseEntityExceptionHandlerImpl extends ResponseEntityExceptionHandler {
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED )
	@ExceptionHandler({ BadCredentialsException.class })
    public GenericResponse<Object> handleBadCredentialsException(Exception ex, WebRequest request) {
    	return GenericResponse.error( ErrorMessages.ERROR_MESSAGE__AccessDenied );
    }
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED )
	@ExceptionHandler({ UsernameNotFoundException.class })
    public GenericResponse<Object> handleUsernameNotFoundException(Exception ex, WebRequest request) {
    	return GenericResponse.error( ErrorMessages.ERROR_MESSAGE__AccessDenied );
    }
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler({ EntityNotFoundException.class })
    public GenericResponse<Object> handleEntityNotFoundException(Exception ex, WebRequest request) {
    	return GenericResponse.error( ErrorMessages.ERROR_MESSAGE__EntityNotFound );
    }
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST )
	@ExceptionHandler({ UniqueEntityDuplicatedException.class })
    public GenericResponse<Object> handleUniqueEntityDuplicatedException(Exception ex, WebRequest request) {
    	return GenericResponse.error( ErrorMessages.ERROR_MESSAGE__UniqueEntityDuplicated );
    }
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST )
	@ExceptionHandler({ InvalidParametersException.class })
    public GenericResponse<Object> handleInvalidParametersException(Exception ex, WebRequest request) {
    	return GenericResponse.error( ex.getMessage() );
    }
	
	@ResponseStatus(value = HttpStatus.FORBIDDEN )
	@ExceptionHandler({ AccessDeniedException.class })
    public GenericResponse<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
    	return GenericResponse.error( ErrorMessages.ERROR_MESSAGE__AccessDenied );
    }
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR )
	@ExceptionHandler({ NullPointerException.class })
    public GenericResponse<Object> handleNullPointerExceptions(Exception e) {
		return GenericResponse.error( ErrorMessages.ERROR_MESSAGE__GeneralError );
    }
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR )
    @ExceptionHandler(GeneralException.class)
    public GenericResponse<Object> handleGeneralExceptions(Exception e, WebRequest request) {
    	return GenericResponse.error( ErrorMessages.ERROR_MESSAGE__GeneralError );
    }
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR )
    @ExceptionHandler(Exception.class)
    public GenericResponse<Object> handleExceptions(Exception e) {
    	return GenericResponse.error( ErrorMessages.ERROR_MESSAGE__GeneralError );
    }
    
}
