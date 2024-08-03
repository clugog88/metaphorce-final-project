package com.app.users.controllers.model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class RequestUpdateUserPassword {
	
	@Positive(message = "El campo 'id' no es valido.")
	@NotNull(message = "El campo 'id' no puede estar vacio.")
	private Long id;
	
	@NotEmpty(message = "El campo 'contraseña' no puede estar vacio.")
	@Length(min = 12,message = "La 'contraseña' debe tener al menos 12 caractares.")
	@Pattern(regexp = "^\\S*$", message = "La 'contraseña' no puede contener espacios.")
	private String password;
	
}
