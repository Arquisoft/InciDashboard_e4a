package com.uniovi.InciDashboard_e4a.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.uniovi.InciDashboard_e4a.services.IncidencesService;

@Controller
public class InciDashboardController {

	@Autowired
	IncidencesService incidenceService;

	public List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<SseEmitter>());

	@RequestMapping("/getEmitter")
	public SseEmitter getEmitter() {
		return nuevoEmitter();
	}

	public SseEmitter nuevoEmitter() {
		SseEmitter emitter = new SseEmitter(0L);

		emitter.onTimeout(() -> emitters.remove(emitter));
		emitter.onCompletion(() -> emitters.remove(emitter));

		emitters.add(emitter);

		return emitter;
	}

}