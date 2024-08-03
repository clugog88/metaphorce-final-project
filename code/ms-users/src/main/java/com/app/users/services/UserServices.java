package com.app.users.services;

import java.util.List;

import com.app.persistence.entities.security.CTUser;
import com.app.users.controllers.model.RequestAddUser;
import com.app.users.controllers.model.RequestUpdateUser;
import com.app.users.controllers.model.RequestUpdateUserPassword;

import jakarta.validation.Valid;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public interface UserServices {
	
	public List<RequestAddUser> getList();
	
	public CTUser getById(Long id);

	public Long save(@Valid RequestAddUser user);

	public void update(@Valid RequestUpdateUser user);

	public void updatePassword(@Valid RequestUpdateUserPassword dto);
	
	public void delete(Long id);
	
}
