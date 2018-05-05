package com.uniovi.InciDashboard_e4a.json;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.Notification;


public class NotificationSerializer implements JsonSerializer<Notification> {	
	
	@Override
	public JsonElement serialize(Notification src, Type typeOfSrc, JsonSerializationContext context) {
		
		JsonObject object = new JsonObject();
		object.addProperty("id", src.getId());
		object.addProperty("descripcion", src.getDescription());
		
		JsonObject incidencia = new JsonObject(); 
		Incidence incidence = src.getIncidencia();
		incidencia.addProperty("agent", incidence.getAgent());
		incidencia.addProperty("inciName", incidence.getInciName());		
		
		
		JsonObject location = new JsonObject();
		location.addProperty("lat", incidence.getLocation().latitude );
		location.addProperty("lon", incidence.getLocation().longitude );
		incidencia.add("location", location);
		
		JsonArray tags = new JsonArray(); 
		for (String tag : incidence.getTags()) {
			tags.add(tag);
		}
		incidencia.add("tags", tags);
		
		JsonArray moreInfo = new JsonArray(); 
		for (String info : incidence.getMoreInfo()) {
			moreInfo.add(info);
		}
		incidencia.add("moreInfo", moreInfo);
		
		JsonArray properties = new JsonArray(); 
		for (String key : incidence.getProperties().keySet()) {
			moreInfo.add((String)incidence.getProperties().get(key));
		}		
		incidencia.add("properties", properties);
		
		object.add("incidence", incidencia);
		
		JsonObject operator = new JsonObject(); 
		operator.addProperty("id", src.getOperator().getID());
		operator.addProperty("name", src.getOperator().getOperatorname());
		operator.addProperty("email", src.getOperator().getEmail());
		object.add("operator", operator);
		return object;
	}
	
	

}
