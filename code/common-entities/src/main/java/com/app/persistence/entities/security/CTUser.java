package com.app.persistence.entities.security;

import java.io.Serializable;

import com.app.persistence.entities.security.enums.UserRoleEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Ing. Christhian Lugo Govea.
 */
@Data
@Entity
@Table(name = "ct_user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CTUser implements Serializable {
	
	private static final long serialVersionUID = -5727362636517041946L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String username;
	private String password;
	private UserRoleEnum role;

}
