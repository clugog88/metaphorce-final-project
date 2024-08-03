package com.app.users.controllers.model;

import org.hibernate.validator.constraints.Length;

import com.app.persistence.entities.security.support.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAddUser {
	
	private Long id;
	
	@NotEmpty(message = "El campo 'nombre de usuario' no puede estar vacio.")
	private String username;
	
	@NotEmpty(message = "El campo 'contraseña' no puede estar vacio.")
	@Length(min = 12,message = "La 'contraseña' debe tener al menos 12 caractares.")
	@Pattern(regexp = "^\\S*$", message = "La 'contraseña' no puede contener espacios.")
	private String password;
	
	@NotEmpty(message = "El campo 'rol' no puede estar vacio.")
	private String role;
	
	@JsonIgnore
	private RoleEnum roleEnum;
	
}
