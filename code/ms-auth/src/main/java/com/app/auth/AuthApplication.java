package com.app.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@SpringBootApplication
@EntityScan("com.app.persistence.entities")
@ComponentScan({"com.app.errors", "com.app.auth"})
@EnableJpaRepositories("com.app.auth.services.repositories")
public class AuthApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
