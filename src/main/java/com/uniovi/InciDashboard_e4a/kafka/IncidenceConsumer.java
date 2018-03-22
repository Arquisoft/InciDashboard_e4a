package com.uniovi.InciDashboard_e4a.kafka;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@ManagedBean
public class IncidenceConsumer {
	
    private static final Logger logger = LoggerFactory.getLogger(IncidenceConsumer.class);
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    @KafkaListener(topics="located")
    public void onGeolocatedIncident(String data) {
    		logger.info("Incidaencia localizada recibida: " + data);
    		messagingTemplate.convertAndSend("/incident/located", data);
    }
    
    @KafkaListener(topics="Operable")
    public void onOperatorIncident(String data) {
		logger.info("Incidencia con asignacion de un operador recibida: " + data);
		messagingTemplate.convertAndSend("/incident/Operable", data);
    }
    
    @KafkaListener(topics="sensor")
    public void onSensorIncident(String data) {
		logger.info("Incidencia de un sensor recibida " + data);
		messagingTemplate.convertAndSend("/incident/sensor", data);
    }
    
    @KafkaListener(topics="normal")
    public void onStandardIncident(String data) {
    		logger.info("Incidencia normal recibida: " + data);
    		messagingTemplate.convertAndSend("/incident/normal", data);
    }

}
