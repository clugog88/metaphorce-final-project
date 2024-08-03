package com.app.security.auth.userdetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.persistence.entities.security.CTUser;
import com.app.persistence.entities.security.enums.UserRoleEnum;

import lombok.AllArgsConstructor;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@AllArgsConstructor
@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {
	
	private CTUser user;
	private UserRoleEnum role;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(user != null && role != null) {
			return List.of( role );
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
