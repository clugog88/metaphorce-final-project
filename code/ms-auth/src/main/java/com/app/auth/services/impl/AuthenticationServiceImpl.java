package com.app.auth.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.app.auth.services.AuthenticationService;
import com.app.errors.exceptions.AppException;
import com.app.errors.exceptions.BadCredentialsException;
import com.app.security.models.auth.AuthenticationRequest;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	
	private static final Logger log = LogManager.getLogger(AuthenticationServiceImpl.class);
	
	@Autowired
	private AuthenticationManager authManager;
    
	@Override
    public UserDetails authenticate(AuthenticationRequest requestDto) {
    	log.debug("Atenticando las credenciales del usuario.");
    	
    	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
    			requestDto.getUsername(),
    			requestDto.getPassword()
        );
    	
    	try {
    		authToken = (UsernamePasswordAuthenticationToken) authManager.authenticate( authToken );
    	}
    	catch (InternalAuthenticationServiceException e) {
    		if( e.getCause() instanceof AppException ) {
    			throw (AppException) e.getCause();
			}
    		throw new BadCredentialsException();
    	}
    	catch (Exception e) {
    		if( e instanceof AppException ) {
    			throw e;
			}
    		throw new BadCredentialsException();
    	}
        
    	SecurityContextHolder.getContext().setAuthentication( authToken );
    	
    	return (UserDetails) authToken.getPrincipal();
    }
    
}
