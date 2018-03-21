package com.uniovi.InciDashboard_e4a.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.LatLong;

@Service
public class InsertSampleDataService {
	
	@Autowired
	AgentsService agentsService;
	
	@Autowired
	IncidencesService incidencesService;
	
	@PostConstruct
	public void init() {
		
		Agent agente = new Agent("pablo", "123456", "1"); 
		agentsService.addAgent(agente);
		LatLong latlong = new LatLong("10", "20"); 
		Incidence incidencia = new Incidence("Prueba", latlong, agente); 
		incidencesService.addIncident(incidencia);
	}

}