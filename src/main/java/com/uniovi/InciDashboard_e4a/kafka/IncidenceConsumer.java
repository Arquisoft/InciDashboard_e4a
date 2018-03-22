package com.uniovi.InciDashboard_e4a.kafka;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

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

		SseEventBuilder event = SseEmitter.event().name("newIncidence").data(data);
		sendData(event);

	}

	void sendData(SseEventBuilder event) {
		synchronized (this.controller.emitters) {
			for (SseEmitter sseEmitter : this.controller.emitters) {
				try {
					sseEmitter.send(event);
				} catch (IOException e) {
					sseEmitter = new SseEmitter(Long.MAX_VALUE);
					// Application.logger.error("Se ha cerrado el stream actual");
				}
			}
		}
	}

}
