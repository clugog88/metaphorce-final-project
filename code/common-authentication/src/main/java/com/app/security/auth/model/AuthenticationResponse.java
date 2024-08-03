package com.app.security.auth.model;

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
public class AuthenticationResponse {
	
	private String jwt;
	
}
