package com.uniovi.InciDashboard_e4a.kafka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.uniovi.InciDashboard_e4a.controllers.InciDashboardController;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.repositories.AgentsRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.ManagedBean;

@ManagedBean
public class IncidenceConsumer {

	@Autowired
	AgentsRepository agentRepository;

	@Autowired
	InciDashboardController controller;

	@KafkaListener(topics = "incidencia")
	public void listen(String data) {
		System.out.println(data);
		for (SseEmitter emitter : controller.emitters) {
			try {
				emitter.send(data, MediaType.APPLICATION_JSON);
			} catch (IOException e) {
				emitter.complete();
			}
		}
	}

}
