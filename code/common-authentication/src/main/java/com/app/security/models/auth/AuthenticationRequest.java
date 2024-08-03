package com.app.security.models.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
	
	private String username;
	private String password;
	
}
