package com.app.errors.support;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.app.auth.controllers.models.GenericResponse;
import com.app.errors.constants.ErrorMessages;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private String requestBodyMsg = null;
	
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException authException) 
    		throws IOException, ServletException {

    	if( requestBodyMsg == null) {
    		final GenericResponse<Object> objToJson = GenericResponse.error( ErrorMessages.ERROR_MESSAGE__AccessDenied );
        	final ObjectMapper objectMapper = new ObjectMapper();
        	requestBodyMsg = objectMapper.writeValueAsString( objToJson );
    	}
    	
    	res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.setStatus( HttpStatus.FORBIDDEN.value() );
        res.getWriter()
        	.write( requestBodyMsg );
    }
    
}
