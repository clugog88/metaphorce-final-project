package com.app.persistence.entities.security.support;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Component
@Converter(autoApply = true)
public class RoleEnumAttributeConverter implements AttributeConverter<RoleEnum, String> {
	
	private static final String ADMIN = "ADMIN";
	private static final String USER  = "USER";
 
    @Override
    public String convertToDatabaseColumn(RoleEnum attribute) {
        if (attribute == null)
            return null;
        
        return attribute.name();
    }
 
    @Override
    public RoleEnum convertToEntityAttribute(String dbAttribute) {
        if (dbAttribute == null)
            return null;
        
        switch (dbAttribute) {
	        case ADMIN:
	            return RoleEnum.ADMIN;
	 
	        case USER:
	            return RoleEnum.USER;
	            
	        default:
//	            throw new IllegalArgumentException(dbAttribute + " not supported.");
        }
        
        return null;
    }
 
}
