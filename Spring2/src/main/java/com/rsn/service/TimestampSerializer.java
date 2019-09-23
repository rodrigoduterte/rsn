package com.rsn.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * A custom Date Serializer. 
 * Prints date in JSON format as "MMMM d, yyyy"
 * @author  Gabriel Ferrer
 * @version 1.0
 */
public class TimestampSerializer extends JsonSerializer<Timestamp> {
	private SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy");

	@Override
	public void serialize(Timestamp value, JsonGenerator gen, 
			SerializerProvider serializers) throws IOException {
		try {
			String str = formatter.format(value);
			gen.writeString(str);
		} catch (DateTimeParseException e) {
			System.err.println(e);
			gen.writeString("");
		}
	}

}
