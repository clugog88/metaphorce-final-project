package com.app.auth.controllers.models;

import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonSerialize; 
import org.springframework.lang.Nullable;

import com.app.errors.constants.ErrorMessages;
import com.app.support.CustomDateSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class GenericResponse<T> {
	
	@JsonSerialize(using = CustomDateSerializer.class)
	private LocalDateTime timestamp = LocalDateTime.now();
	
    private CodeEnum code;
    private String message;
    private T data;
    
	private GenericResponse(CodeEnum code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	private GenericResponse(CodeEnum code, T data) {
		super();
		this.code = code;
		this.data = data;
	}
	
	/**
	 * Available code options.
	 */
	private enum CodeEnum {
	    OK, 
	    ERROR;
	}
	
	public static <T> GenericResponse<T> ok(@Nullable String message) {
		return new GenericResponse<>(CodeEnum.OK, message);
	}
	
	public static <T> GenericResponse<T> ok() {
		return new GenericResponse<>(CodeEnum.OK, ErrorMessages.ERROR_MESSAGE__OK );
	}
	
	public static <T> GenericResponse<T> ok(@Nullable T data) {
		return new GenericResponse<>(CodeEnum.OK, data);
	}
	
	public static <T> GenericResponse<T> error(@Nullable String message) {
		return new GenericResponse<>(CodeEnum.ERROR, message);
	}
	
	public static <T> GenericResponse<T> error() {
		return new GenericResponse<>(CodeEnum.ERROR, ErrorMessages.ERROR_MESSAGE__GeneralError );
	}
	
	public static <T> GenericResponse<T> error(@Nullable T data) {
		return new GenericResponse<>(CodeEnum.ERROR, data);
	}
	
}
