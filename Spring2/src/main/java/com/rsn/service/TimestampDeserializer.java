package com.rsn.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * A custom Date Deserializer. 
 * Saves date to the database
 * Input JSON format is "yyyy-MM-d" 
 *
 * @author  Gabriel Ferrer
 * @version 1.0
 */
public class TimestampDeserializer extends JsonDeserializer<Timestamp> {
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-d");
	
	@Override
	public Timestamp deserialize(JsonParser parser, 
			DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String str = parser.getText();
		try {
			return new Timestamp( formatter.parse(str).getTime() );
		} catch (DateTimeParseException | ParseException e) {
			return null;
		} 
		
	}
	
}
