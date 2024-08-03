package com.app.configuration.security.services.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.app.configuration.properties.JwtSecurityProperties;
import com.app.configuration.security.services.JwtService;
import com.app.errors.exceptions.auth.BadCredentialsException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtBuilder.BuilderHeader;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;

/**
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Service
public class JwtServiceImpl implements JwtService {

	private static final Logger log = LogManager.getLogger(JwtServiceImpl.class);

	@Autowired
	private JwtSecurityProperties jwtSecurityProperties;

	/**
	 * Public method to build token.
	 * 
	 * @param userDetails User details.
	 * @return Token.
	 */
	@Override
	public String generateToken(UserDetails userDetails) {
		HashMap<String, Object> claims = new HashMap<>();

		Set<String> roles = AuthorityUtils.authorityListToSet(userDetails.getAuthorities());
		roles = roles.stream()
				.map(c -> c.replaceFirst("^ROLE_", ""))
				.collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
		claims.put("authorities", roles);
		return generateToken(claims, userDetails);
	}

	/**
	 * Public method to build token.
	 * 
	 * @param extraClaims Extra claims.
	 * @param userDetails User details.
	 * @return Token.
	 */
	@Override
	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		return buildToken(extraClaims, userDetails, jwtSecurityProperties.getJwtExpiration());
	}

	/**
	 * Private method to build token.
	 * 
	 * @param extraClaims Extra claims.
	 * @param userDetails User details.
	 * @param expiration  Expiration time.
	 * @return
	 */
	private String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
		JwtBuilder jwtBuilder = Jwts.builder();
		BuilderHeader headerBuilder = jwtBuilder.header();
		headerBuilder.type("JWT");
		return jwtBuilder.subject(userDetails.getUsername())
				.claims(extraClaims)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(getSignInKey(), Jwts.SIG.HS384)
				.compact();
	}
	
//	private boolean verify(PublicKey publicKey, String header, String payload, String signature) {
//        try {
//            String data = header + "." + payload;
//            Signature sig = Signature.getInstance("SHA256withRSA");
//            sig.initVerify(publicKey);
//            sig.update(data.getBytes());
//            byte[] decodedSignature = Base64.getUrlDecoder().decode( signature );
//            return sig.verify(decodedSignature);
//        } 
//        catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

	@Override
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	/**
	 * Method to create SecretKey.
	 * 
	 * @return Secret key.
	 */
	private SecretKey getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSecurityProperties.getSecretKey());
		return new SecretKeySpec( keyBytes , jwtSecurityProperties.getAlgorithm() );
	}

	/**
	 * Method to share expiration time.
	 * 
	 * @return Expiration time.
	 */
	@Override
	public long getExpirationTime() {
		return jwtSecurityProperties.getJwtExpiration();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		SecretKey secretKey = getSignInKey();
		
		JwtParserBuilder parserBuilder = Jwts.parser();
		parserBuilder.verifyWith( secretKey );
		parserBuilder.decryptWith( secretKey );
		
		JwtParser parser = parserBuilder.build();

		Claims claims = null;
		try {
			Jws<Claims> jwt = parser.parseSignedClaims( token );
			claims = jwt.getPayload();
		} 
		catch (SignatureException e) {
			log.warn("Error validating received JWT.", e);
			throw new BadCredentialsException("The JWT is not correct.");
		}
		catch (UnsupportedJwtException e) {
			log.warn("Error parsing received JWT.", e);
			throw new BadCredentialsException("The JWT is not correct.");
		}
		catch (Exception e) {
			log.error("Error processing received JWT.", e);
			throw new BadCredentialsException("The JWT is not correct.");
		}
		return claims;
	}

}
