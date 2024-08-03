package com.app.persistence.entities.security;

import java.io.Serializable;

import com.app.persistence.entities.security.support.RoleEnum;
import com.app.persistence.entities.security.support.RoleEnumAttributeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "ct_user")
public class CTUser implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "username", unique = true, nullable = false)	
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Enumerated( EnumType.STRING )
	@Column(name = "role", nullable = false)
	@Convert(converter = RoleEnumAttributeConverter.class)
	private RoleEnum role;

	public CTUser(Long id) {
		this.id = id;
	}
	
}
