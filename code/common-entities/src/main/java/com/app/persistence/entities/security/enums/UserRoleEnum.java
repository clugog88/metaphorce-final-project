package com.app.persistence.entities.security.enums;

import org.springframework.security.core.GrantedAuthority;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public enum UserRoleEnum implements GrantedAuthority {
	
	ADMIN, 
	USER; 
	
	@Override
	public String getAuthority() {
		return ROLE_PREFIX + this.name();
	}
	
	private static final String ROLE_PREFIX = "ROLE_";
	
}
