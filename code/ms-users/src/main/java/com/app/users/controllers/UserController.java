package com.app.users.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.auth.controllers.models.GenericResponse;
import com.app.errors.exceptions.global.InvalidParametersException;
import com.app.persistence.entities.security.CTUser;
import com.app.persistence.entities.security.enums.UserRoleEnum;
import com.app.persistence.entities.security.support.UserRoleEnumAttributeConverter;
import com.app.users.controllers.model.RequestAddUser;
import com.app.users.controllers.model.RequestUpdateUser;
import com.app.users.controllers.model.RequestUpdateUserPassword;
import com.app.users.services.UserServices;

import jakarta.validation.Valid;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	private static final Logger log = LogManager.getLogger(UserController.class);
	
	@Autowired
	private UserServices userServices;
	
	@Autowired 
	private UserRoleEnumAttributeConverter roleEnumConverter;
	
	@GetMapping(value = "/list")
	@ResponseStatus(HttpStatus.OK)
	public GenericResponse< List<RequestAddUser> > list(Authentication authentication) {
		log.info("Lanzando la busqueda de la informacion.");
		List<RequestAddUser> list = userServices.getList();
		return GenericResponse.ok( list );
	}
	
	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public GenericResponse<CTUser> getById(@PathVariable("id") Long id, Authentication authentication) {
		
		log.info("Validando los parametros recibidos.");
		if(id == null) {
			throw new InvalidParametersException( "El campo 'id' no puede estar vacio." );
		}
		
		log.info("Lanzando la busqueda de la informacion.");
		CTUser user = userServices.getById( id );
		return GenericResponse.ok( user );
	}
	
	@PostMapping(value = "/add")
	@ResponseStatus(HttpStatus.CREATED)
	public GenericResponse<Void> add(@Valid @RequestBody RequestAddUser dto, BindingResult bindingResult, Authentication authentication){
		
		log.info("Validando los parametros recibidos.");
		if(bindingResult.hasErrors()) {
			throw InvalidParametersException.parseToException( bindingResult );
		}
		
		UserRoleEnum role = roleEnumConverter.convertToEntityAttribute( dto.getRole() );
		if(role == null) {
			throw new InvalidParametersException( "El valor del campo 'rol' no es valido." );
		}
		dto.setRoleEnum( role );
		
		log.info("Lanzando el guardado de la informacion.");
		userServices.save( dto );
		return GenericResponse.ok();
	}
	
	@PutMapping(value = "/update")
	@ResponseStatus(HttpStatus.OK)
	public GenericResponse<Void> update(@Valid @RequestBody RequestUpdateUser dto, BindingResult bindingResult, Authentication authentication){
		
		log.info("Validando los parametros recibidos.");
		if(bindingResult.hasErrors()) {
			throw InvalidParametersException.parseToException( bindingResult );
		}
		
		UserRoleEnum role = roleEnumConverter.convertToEntityAttribute( dto.getRole() );
		if(role == null) {
			throw new InvalidParametersException( "El valor del campo 'rol' no es valido." );
		}
		dto.setRoleEnum( role );
		
		log.info("Lanzando la actualizacion de la informacion.");
		userServices.update( dto );
		return GenericResponse.ok();
	}
	
	@PutMapping(value = "/updatePassword")
	@ResponseStatus(HttpStatus.OK)
	public GenericResponse<Void> updatePassword(@Valid @RequestBody RequestUpdateUserPassword dto, BindingResult bindingResult, Authentication authentication){
		
		log.info("Validando los parametros recibidos.");
		if(bindingResult.hasErrors()) {
			throw InvalidParametersException.parseToException( bindingResult );
		}
		
		log.info("Lanzando la actualizacion de la informacion.");
		userServices.updatePassword( dto );
		return GenericResponse.ok();
	}
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
    public GenericResponse<Void> delete(@PathVariable("id") Long id, Authentication authentication){
		
		log.info("Validando los parametros recibidos.");
		if(id == null) {
			throw new InvalidParametersException( "El campo 'id' no puede estar vacio." );
		}
		
		log.info("Lanzando la eliminacion de la informacion.");
		userServices.delete( id );
		return GenericResponse.ok();
	}
	
}
