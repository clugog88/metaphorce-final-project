package com.app.errors.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.auth.controllers.models.GenericResponse;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Controller
public class ErrorControllerImpl implements ErrorController  {

	@RequestMapping("/error")
	public ResponseEntity<?> handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    if (status != null) {
	        Integer statusCode = Integer.valueOf(status.toString());
	        HttpStatus httpStatus = HttpStatus.valueOf( statusCode );
	        if(httpStatus!=null) {
	        	return new ResponseEntity<>(GenericResponse.error(), httpStatus);
	        }
	    }
	    return new ResponseEntity<>(GenericResponse.error(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
    
}
