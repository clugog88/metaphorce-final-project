package com.app.configuration.security.services;

import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
public interface JwtService {
	
	public String generateToken(UserDetails userDetails);
	
	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
	
	public String extractUsername(String token);
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
	
	public boolean isTokenValid(String token, UserDetails userDetails) ;
	
	public long getExpirationTime();
	
}
