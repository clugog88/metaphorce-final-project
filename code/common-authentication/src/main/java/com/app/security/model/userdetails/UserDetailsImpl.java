package com.app.security.model.userdetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.persistence.entities.security.CTUser;
import com.app.persistence.entities.security.support.RoleEnum;

import lombok.AllArgsConstructor;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@AllArgsConstructor
@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {
	
	private final String ROLE_PREFIX = "ROLE_";
	
	private CTUser user;
	private RoleEnum role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(user != null && role != null) {
			return List.of( new SimpleGrantedAuthority( ROLE_PREFIX + role.name() ) );
//			return List.of( new SimpleGrantedAuthority( role.name() ) );
		}
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		if(user != null) {
			return user.getPassword();
		}
		return null;
	}

	@Override
	public String getUsername() {
		if(user!=null) {
			return user.getUsername();
		}
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
