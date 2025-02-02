package com.app.auth.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.auth.controller.model.ResponseLogin;
import com.app.auth.controllers.models.GenericResponse;
import com.app.auth.services.AuthenticationService;
import com.app.auth.services.JwtService;
import com.app.errors.exceptions.global.InvalidParametersException;
import com.app.security.auth.model.AuthenticationRequest;

import jakarta.validation.Valid;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
	
	private static final Logger log = LogManager.getLogger(AuthenticationController.class);
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private JwtService jwtService;
    
	@PostMapping(value = "/login")
	@ResponseStatus(HttpStatus.OK)
	public GenericResponse<ResponseLogin> login(
			@Valid @RequestBody AuthenticationRequest authRequest, 
			BindingResult bindingResult){
		
		log.info("Validating the received parameters.");
		if(bindingResult.hasErrors()) {
			throw InvalidParametersException.parseToException( bindingResult );
		}
		
		log.info("Launching user authentication.");
		UserDetails authUser = authenticationService.authenticate( authRequest );
		String jwtToken = jwtService.generateToken( authUser );
        long expirationTime = jwtService.getExpirationTime();
        
        ResponseLogin responseDto = new ResponseLogin();
        responseDto.setToken( jwtToken );
        responseDto.setExpiresIn( expirationTime );

        return GenericResponse.ok( responseDto );
	}
	
}
