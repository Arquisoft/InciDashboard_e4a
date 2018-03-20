package com.uniovi.InciDashboard_e4a.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.uniovi.InciDashboard_e4a.entities.Incidence;

public class Serializer extends JsonSerializer<Incidence> {

	@Override
	public void serialize(Incidence incidence, JsonGenerator generator,
			SerializerProvider provider) throws IOException {
		
		generator.writeStartObject();
		
		// agent info
		generator.writeObjectFieldStart("agent");
		generator.writeStringField("username", incidence.getAgent().getUsername());
		generator.writeStringField("password", incidence.getAgent().getPassword());
		generator.writeStringField("kind", incidence.getAgent().getKind());
		generator.writeEndObject();
		
		generator.writeStringField("inciName", incidence.getInciName());
		
		// location
		generator.writeObjectFieldStart("location");
		generator.writeStringField("lat", incidence.getLocation().latitude);
		generator.writeStringField("lon", incidence.getLocation().longitude);
		generator.writeEndObject();
		
		// tags
		generator.writeArrayFieldStart("tags");
		for (String tag : incidence.getTags()) {
			generator.writeString(tag);
		}
		generator.writeEndArray();
		
		// more info
		generator.writeArrayFieldStart("moreInfo");
		for (String info : incidence.getMoreInfo()) {
			generator.writeString(info);
		}
		generator.writeEndArray();
		
		// properties
		generator.writeObjectFieldStart("properties");
		for (String key : incidence.getProperties().keySet()) {
			generator.writeObjectField(key, incidence.getProperties().get(key));
		}
		generator.writeEndObject();
		
		generator.writeEndObject();
	}

}
