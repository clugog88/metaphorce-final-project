package com.app.configuration.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.persistence.entities.security.CTUser;
import com.app.persistence.entities.security.enums.UserRoleEnum;
import com.app.users.services.repositories.CTUserRepository;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Component
public class DataInitialization {
	
	private static final Logger log = LogManager.getLogger(DataInitialization.class);
	
	@Autowired
    private CTUserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	@EventListener
    public void handleSuccessful(ApplicationStartedEvent event) {
		log.info("Se cargan datos iniciales de prueba.");
        
		CTUser user = new CTUser();
        user.setName( "Christhian" );
        user.setLastname( "Lugo" );
        user.setUsername( "christhian.lugo@gmail.com" );
        user.setPassword( passwordEncoder.encode("hola_mundo") );
        user.setRole( UserRoleEnum.ADMIN );
        userRepository.save( user );
        
        user = new CTUser();
        user.setName( "Cristina" );
        user.setLastname( "Govea" );
        user.setUsername( "cristina.govea@gmail.com" );
        user.setPassword( passwordEncoder.encode("hola_mundo") );
        user.setRole( UserRoleEnum.USER );
        userRepository.save( user );
		
        user = new CTUser();
        user.setName( "Juan" );
        user.setLastname( "Lugo" );
        user.setUsername( "juan.lugo@gmail.com" );
        user.setPassword( passwordEncoder.encode("hola_mundo") );
        user.setRole( UserRoleEnum.USER );
        userRepository.save( user );
    }
	
}
