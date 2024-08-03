package com.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.configuration.components.JwtAuthenticationFilter;
import com.app.errors.support.CustomAuthenticationEntryPoint;
import com.app.persistence.entities.security.support.RoleEnum;

/**
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	static GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults("ROLE_");
	}
	
	@Bean
	@Order(1)
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			
//			.securityMatcher("/user/**")
			.authorizeHttpRequests( auth -> auth
					.requestMatchers( HttpMethod.GET,    "/user/list"           ).hasAnyRole( RoleEnum.ADMIN.name(), RoleEnum.USER.name()  )
					.requestMatchers( HttpMethod.GET,    "/user/**"             ).hasAnyRole( RoleEnum.ADMIN.name(), RoleEnum.USER.name()  )
					.requestMatchers( HttpMethod.POST,   "/user/add"            ).hasAnyRole( RoleEnum.ADMIN.name()  )
					.requestMatchers( HttpMethod.PUT,    "/user/update"         ).hasAnyRole( RoleEnum.ADMIN.name()  )
					.requestMatchers( HttpMethod.PUT,    "/user/updatePassword" ).hasAnyRole( RoleEnum.ADMIN.name()  )
					.requestMatchers( HttpMethod.DELETE, "/user/**"             ).hasAnyRole( RoleEnum.ADMIN.name()  )
//					.anyRequest().authenticated() // It allow every request to any end point while is authenticated. 
			)
			
			.sessionManagement(session -> session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.authenticationProvider(authenticationProvider)
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling(handler -> handler
					.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
			);
		return http.build();
	}

	@Bean
	@Order(2)
	SecurityFilterChain restPathsApplicationFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.httpBasic(AbstractHttpConfigurer::disable)
			
			.authorizeHttpRequests(auth -> auth
					.anyRequest().denyAll()
			)
			
			.sessionManagement(session -> session
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.exceptionHandling(handler -> handler
					.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
			);
		return http.build();
	}

}
