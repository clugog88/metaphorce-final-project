package com.app.auth.controllers.models;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@SuppressWarnings("serial")
public class CustomDateSerializer extends StdSerializer<LocalDateTime> {

	private static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	public CustomDateSerializer() {
		this( null );
	}
	
	public CustomDateSerializer(Class<LocalDateTime> classType) {
		super( classType );
	}

	@Override
	public void serialize(LocalDateTime dateTime, JsonGenerator jsonGen, SerializerProvider serProv) throws IOException {
		jsonGen.writeString( formatter.format( dateTime ) );
	}

}
