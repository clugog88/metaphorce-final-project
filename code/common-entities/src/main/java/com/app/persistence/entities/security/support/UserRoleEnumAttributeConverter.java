package com.app.persistence.entities.security.support;

import org.springframework.stereotype.Component;

import com.app.persistence.entities.security.enums.UserRoleEnum;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Component
@Converter(autoApply = true)
public class UserRoleEnumAttributeConverter implements AttributeConverter<UserRoleEnum, String> {
	
	private static final String ADMIN = "ADMIN";
	private static final String USER  = "USER";
 
    @Override
    public String convertToDatabaseColumn(UserRoleEnum attribute) {
        if (attribute == null)
            return null;
        
        return attribute.name();
    }
 
    @Override
    public UserRoleEnum convertToEntityAttribute(String dbAttribute) {
        if (dbAttribute == null)
            return null;
        
        switch (dbAttribute) {
	        case ADMIN:
	            return UserRoleEnum.ADMIN;
	 
	        case USER:
	            return UserRoleEnum.USER;
	            
	        default:
//	            throw new IllegalArgumentException(dbAttribute + " not supported.");
        }
        
        return null;
    }
 
}
