package com.uniovi.InciDashboard_e4a.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder;

import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.State;
import com.uniovi.InciDashboard_e4a.services.AgentsService;
import com.uniovi.InciDashboard_e4a.services.IncidencesService;

import mocks.IncidenceGenerator;
import utils.Incidence2Pojo;

@Controller
public class InciDashboardController {

	@Autowired
	IncidencesService incidenceService;
	
	@Autowired
	AgentsService agentsService; 
	
	public List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<SseEmitter>());
	
	@CrossOrigin(origins = "http://localhost:8090")
	@RequestMapping("/getEmitter")
	public SseEmitter getEmitter() {
		return nuevoEmitter();
	}

	public SseEmitter nuevoEmitter() {
		SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

		synchronized (this.emitters) {
			this.emitters.add(sseEmitter);
			sseEmitter.onCompletion(() -> {
				synchronized (this.emitters) {
					this.emitters.remove(sseEmitter);
				}
			});
		}
		return sseEmitter;

	}
	@RequestMapping("/randomPetition")
	public String randomPetition() {
		IncidenceGenerator incidenceGenerator = new IncidenceGenerator();
		incidenceGenerator.setPossibleAgents(agentsService.findAll());
		Incidence incidence = incidenceGenerator.generateRandomIncident();
		incidence.setState(State.IN_PROCESS);
		Incidence i2 = incidenceService.addIncidence(incidence);
		SseEventBuilder event = SseEmitter.event().name("newIncidence").data(Incidence2Pojo.convert(i2));
		sendData(event);		
		return "redirect:"; 
	}
	
	void sendData(SseEventBuilder event) {
		synchronized (this.emitters) {
			for (SseEmitter sseEmitter : this.emitters) {
				try {
					sseEmitter.send(event);
				} catch (IOException e) {
					sseEmitter = new SseEmitter(Long.MAX_VALUE);
					//Application.logger.error("Se ha cerrado el stream actual");
				}
			}
		}
	}

}