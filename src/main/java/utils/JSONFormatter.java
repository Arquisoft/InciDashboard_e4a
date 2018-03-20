package utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JSONFormatter {
	private static final Logger LOG = LoggerFactory.getLogger(JSONFormatter.class);

	public <K, V> String map2Json(Map<String, Object> map) {
		Gson gson = new GsonBuilder().create();
		String jsonString = gson.toJson(map).toString();
		LOG.info("Formating MAP 2 JSON. From: {} to: {}", map, jsonString);
		return jsonString;
	}

	public <K, V> Map<K, V> json2Map(String json) {
		Gson gson = new Gson();
		Map<K, V> map = gson.fromJson(json, new TypeToken<Map<K, V>>() {
		}.getType());
		LOG.info("Formating JSON 2 MAP. From: {} to: {}", json, map);
		return map;
	}
}
