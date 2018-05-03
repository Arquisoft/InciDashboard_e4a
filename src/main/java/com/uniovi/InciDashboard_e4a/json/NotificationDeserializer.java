package com.uniovi.InciDashboard_e4a.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.LatLong;
import com.uniovi.InciDashboard_e4a.entities.NotificationPOJO;
import com.uniovi.InciDashboard_e4a.entities.Operator;


public class NotificationDeserializer implements JsonDeserializer<NotificationPOJO>{
	
	
	@Override
	public NotificationPOJO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject obj = json.getAsJsonObject();
		
		String id = obj.get("id").getAsString();
		String descripcion = obj.get("descripcion").getAsString();
		JsonObject incidencia = obj.get("incidence").getAsJsonObject(); 
		
		String agent = incidencia.get("agent").getAsString();
		String inciname = incidencia.get("inciName").getAsString();		
		LatLong location = new LatLong(incidencia.get("location").getAsJsonObject().get("lat").getAsString(), incidencia.get("location").getAsJsonObject().get("lon").getAsString());
		
		Incidence incidence = new Incidence(inciname, location, agent); 
		System.out.println("he creado la incidencia");
		
		JsonObject operatorjson = obj.get("operator").getAsJsonObject();
		String email = operatorjson.get("email").getAsString();
		Operator operator = new Operator(email);
		NotificationPOJO noti = new NotificationPOJO(id, descripcion, incidence, operator);
		return noti;
	}

}
