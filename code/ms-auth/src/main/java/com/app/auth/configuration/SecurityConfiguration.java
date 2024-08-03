package com.app.auth.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.auth.configuration.filters.JwtAuthenticationFilter;
import com.app.auth.configuration.support.CustomAuthenticationEntryPoint;

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
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests( auth -> auth
            		.requestMatchers("/auth/login").permitAll()
            		.anyRequest().denyAll()
            )
            .sessionManagement( session -> session
            		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider( authenticationProvider )
            .addFilterBefore( jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class )
            .exceptionHandling( handler -> handler
            		.authenticationEntryPoint( new CustomAuthenticationEntryPoint() )
            );
        return http.build();
    }
    
}
