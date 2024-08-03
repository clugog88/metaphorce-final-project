package com.app.users.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.errors.exceptions.EntityNotFoundException;
import com.app.errors.exceptions.UniqueEntityDuplicatedException;
import com.app.persistence.entities.security.CTUser;
import com.app.users.controllers.model.RequestAddUser;
import com.app.users.controllers.model.RequestUpdateUser;
import com.app.users.controllers.model.RequestUpdateUserPassword;
import com.app.users.persistence.repositories.CTUserRepository;
import com.app.users.services.UserServices;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Service
public class UserServicesImpl implements UserServices {
	
	private static final Logger log = LogManager.getLogger(UserServicesImpl.class);

	@Autowired 
	private CTUserRepository ctUserRepository;
	
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<RequestAddUser> getList() {
		log.info("Buscando la informacion de un usuario existente.");
		Iterable<CTUser> iterable = ctUserRepository.findAll();
		if(iterable == null) {
			return Collections.emptyList();
		}
		
		RequestAddUser dto;
		List<RequestAddUser> list = new ArrayList<>();
	    for (CTUser dbUser : iterable) {
	    	dto = new RequestAddUser();
	    	
	    	dto.setId( dbUser.getId() );
	    	dto.setUsername( dbUser.getUsername() );
	    	dto.setRole( dbUser.getRole().name() );
	    	
	    	list.add( dto );
	    }
		
		return list;
	}
	
	@Override
	public CTUser getById(Long id) {
		log.info("Buscando la informacion de un usuario existente.");
		
		Optional<CTUser> opt = ctUserRepository.findById( id );
		if(opt.isEmpty()) {
			throw new EntityNotFoundException();
		}
		
		return opt.get();
	}
	
	@Override
	public Long save(RequestAddUser dto) {
		log.info("Registrando un nuevo usuario.");
		
		boolean exist = ctUserRepository.existsByUsername( dto.getUsername() );
		if(exist) {
			throw new UniqueEntityDuplicatedException();
		}
		
		CTUser entity = new CTUser();
		entity.setUsername( dto.getUsername() );
		entity.setRole( dto.getRoleEnum() );
		
		String passEncoded = passwordEncoder.encode( dto.getPassword() );
		entity.setPassword( passEncoded );
		
		ctUserRepository.save( entity );
		
		return entity.getId();
	}
	
	@Override
	public void update(RequestUpdateUser dto) {
		log.info("Actualizando un usuario existente.");
		
		Optional<CTUser> opt = ctUserRepository.findById( dto.getId() );
		if(opt.isEmpty()) {
			throw new EntityNotFoundException();
		}
		CTUser entity = opt.get();
		entity.setUsername( dto.getUsername() );
		entity.setRole( dto.getRoleEnum() );
		
		ctUserRepository.save( entity );
	}
	
	@Override
	public void updatePassword(RequestUpdateUserPassword dto) {
		log.info("Actualizando un usuario existente.");
		
		Optional<CTUser> opt = ctUserRepository.findById( dto.getId() );
		if(opt.isEmpty()) {
			throw new EntityNotFoundException();
		}
		CTUser entity = opt.get();
		
		String passEncoded = passwordEncoder.encode( dto.getPassword() );
		entity.setPassword( passEncoded );
		
		ctUserRepository.save( entity );
	}
	
	@Override
	public void delete(Long id) {
		log.info("Eliminando un usuario existente.");
		
		boolean exist = ctUserRepository.existsById( id );
		if(!exist) {
			throw new EntityNotFoundException();
		}
		
		ctUserRepository.deleteById( id );
	}
	
}
