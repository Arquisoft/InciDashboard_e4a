package utils;

import java.util.Map;

import javax.persistence.AttributeConverter;

public class IncidentProperties2Json implements AttributeConverter<Map<String, Object>, String>{
	
private JSONFormatter json = new JSONFormatter();
	
	@Override
	public String convertToDatabaseColumn(Map<String, Object> properties) {
		return this.json.map2Json(properties);
	}

	@Override
	public Map<String, Object> convertToEntityAttribute(String json) {
		return this.json.json2Map(json);
	}
}
