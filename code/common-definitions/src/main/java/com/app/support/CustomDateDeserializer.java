package com.app.support;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/** 
 * 
 * @Author Ing. Christhian Lugo Govea.
 */
@SuppressWarnings("serial")
public class CustomDateDeserializer extends StdDeserializer<LocalDateTime> {

	private static DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	public CustomDateDeserializer() {
		this( null );
	}

	public CustomDateDeserializer(Class<?> classType) {
		super( classType );
	}

	@Override
	public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
		String date = jsonparser.getText();
		return LocalDateTime.parse(date, formatter);
	}
	
}
