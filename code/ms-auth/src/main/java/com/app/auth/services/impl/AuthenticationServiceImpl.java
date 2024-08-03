package com.app.auth.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.app.auth.services.AuthenticationService;
import com.app.errors.exceptions.global.GeneralException;
import com.app.security.auth.model.AuthenticationRequest;

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
    	catch (InternalAuthenticationServiceException | BadCredentialsException e) {
    		if( e instanceof AuthenticationException ex ) {
    			throw new com.app.errors.exceptions.auth.BadCredentialsException( ex );
			}
    		if( e.getCause() instanceof AuthenticationException ex ) {
    			throw new com.app.errors.exceptions.auth.BadCredentialsException( ex );
			}
    		throw new com.app.errors.exceptions.auth.BadCredentialsException( e );
    	}
    	catch (Exception ex) {
    		throw new GeneralException( ex );
    	}
        
    	SecurityContextHolder.getContext().setAuthentication( authToken );
    	
    	return (UserDetails) authToken.getPrincipal();
    }
    
}
