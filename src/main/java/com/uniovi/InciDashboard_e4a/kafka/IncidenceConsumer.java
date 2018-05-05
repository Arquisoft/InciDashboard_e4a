package com.uniovi.InciDashboard_e4a.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uniovi.InciDashboard_e4a.controllers.InciDashboardController;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.Notification;
import com.uniovi.InciDashboard_e4a.entities.NotificationPOJO;
import com.uniovi.InciDashboard_e4a.entities.Operator;
import com.uniovi.InciDashboard_e4a.json.NotificationDeserializer;
import com.uniovi.InciDashboard_e4a.json.NotificationSerializer;
import com.uniovi.InciDashboard_e4a.repositories.AgentsRepository;
import com.uniovi.InciDashboard_e4a.repositories.IncidencesRepository;
import com.uniovi.InciDashboard_e4a.repositories.OperatorRepository;
import com.uniovi.InciDashboard_e4a.services.NotificationsService;

import utils.Incidence2Pojo;

import java.io.IOException;
import javax.annotation.ManagedBean;

@ManagedBean
public class IncidenceConsumer {

	@Autowired
	AgentsRepository agentRepository;
	
	@Autowired 
	IncidencesRepository incidenceRepository;
	
	@Autowired
	OperatorRepository operatorRepository;
	
	@Autowired
	NotificationsService notificationService;
	
	@Autowired
	InciDashboardController controller;
	GsonBuilder builder = new GsonBuilder(); 
	Gson gson; 
	NotificationDeserializer serializer = new NotificationDeserializer();
	public IncidenceConsumer() {
		builder.registerTypeAdapter(NotificationPOJO.class, serializer);
		
		gson = builder.create();
	}
	
	
	@KafkaListener(topics = "vcxbg6wt-incidencia")
	public void listen(String data) {
		System.out.println(data);
		
		
		NotificationPOJO notificationPOJO = gson.fromJson(data, NotificationPOJO.class);		
		Incidence incidence = notificationPOJO.incidencia;
		if(incidence == null) {
			System.out.println("cabum");
			return;
		}
		incidence = incidenceRepository.save(incidence);
		
		Operator operator = notificationPOJO.operator;
		operator = operatorRepository.findByEmail(operator.getEmail());
		
		
		Notification notification = new Notification(notificationPOJO.id, notificationPOJO.descripcion, incidence, operator );
		notification = notificationService.addIncident(notification);
		System.out.println(notification);
		SseEventBuilder event = SseEmitter.event().name("newIncidence").data(Incidence2Pojo.convert(notification.getIncidencia()));
		sendData(event);
		System.out.println("Acabé");
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
