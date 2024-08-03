package com.app.auth.persistence.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.app.persistence.entities.security.CTUser;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public interface CTUserRepository extends CrudRepository<CTUser, Long>{
	
	public Optional<CTUser> findByUsername(String username);
	public boolean existsByUsername(String username);
	
}
