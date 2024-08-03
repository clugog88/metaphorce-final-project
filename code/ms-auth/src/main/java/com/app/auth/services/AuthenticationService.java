package com.app.auth.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.app.security.auth.model.AuthenticationRequest;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public interface AuthenticationService {

	public UserDetails authenticate(AuthenticationRequest requestDto);
	
}
