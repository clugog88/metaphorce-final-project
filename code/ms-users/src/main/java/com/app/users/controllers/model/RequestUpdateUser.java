package com.app.users.controllers.model;

import com.app.persistence.entities.security.support.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
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
public class RequestUpdateUser {
	
	@Positive(message = "El campo 'id' no es valido.")
	@NotNull(message = "El campo 'id' no puede estar vacio.")
	private Long id;
	
	@NotEmpty(message = "El campo 'nombre de usuario' no puede estar vacio.")
	private String username;
	
	@JsonIgnore
	@Null(message = "El campo 'contraseña' no puede venir en esta peticion.")
	private String password;
	
	@NotEmpty(message = "El campo 'rol' no puede estar vacio.")
	private String role;
	
	@JsonIgnore
	private RoleEnum roleEnum;
	
}
