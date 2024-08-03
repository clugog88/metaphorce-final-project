package com.app.configuration.security.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.app.errors.exceptions.auth.UsernameNotFoundException;
import com.app.persistence.entities.security.CTUser;
import com.app.security.auth.userdetails.UserDetailsImpl;
import com.app.users.services.repositories.CTUserRepository;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CTUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<CTUser> userOpt = userRepository.findByUsername( username );
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException( username );
        }
        
        CTUser user = userOpt.get();
        return new UserDetailsImpl( user, user.getRole() );
    }
    
}
