package com.app.users.controllers.model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserAuthentication {
	
	@NotEmpty(message = "El nombre no puede estar vacio")
	private String nombre;
	
	private String nombreArtistico; 
	
	@Email(message = "El campo email no es valido ")
	@NotEmpty(message = "El email no puede ser nulo")
	private String email;
	
	@Length(min = 12,message = "La clave debe tener al menos 12 caractares")
	@NotEmpty(message = "La clave no puede estar vacia")
	@Pattern(regexp = "^\\S*$",message = "La clave no puede contener espacios")
	private String password;
	
	@Min(value = 1,message = "El campo idRol no es valido")
	@Max(value = 2,message = "El campo idRol no es valido")
	@NotNull(message = "El rol no puede estar vacio")
	private Long idRol;
	
}
