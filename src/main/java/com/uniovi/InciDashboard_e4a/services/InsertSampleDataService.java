package com.uniovi.InciDashboard_e4a.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.Location;
import com.uniovi.InciDashboard_e4a.entities.State;

@Service
public class InsertSampleDataService {
	
	@Autowired
	AgentsService agentsService;
	
	@Autowired
	IncidencesService incidencesService;
	
	@PostConstruct
	public void init() {
		
		Agent agente1 = new Agent("pablo", "123456", "1"); 
		Agent agente2 = new Agent("antonio", "123456", "1"); 
		Agent agente3 = new Agent("hugo", "123456", "1"); 
		Agent agente4 = new Agent("mirza", "123456", "1"); 
		Agent agente5 = new Agent("pasadores", "123456", "1"); 
		
		agentsService.addAgent(agente1);
		agentsService.addAgent(agente2);
		agentsService.addAgent(agente3);
		agentsService.addAgent(agente4);
		agentsService.addAgent(agente5);
		
		Location latlong1 = new Location("11", "20"); 
		Location latlong2 = new Location("12", "20"); 
		Location latlong3 = new Location("13", "20"); 
		Location latlong4 = new Location("14", "20"); 
		Location latlong5 = new Location("15", "20"); 
		Location latlong6 = new Location("16", "20"); 
		Location latlong7 = new Location("17", "20"); 
		Location latlong8 = new Location("18", "20"); 
		Location latlong9 = new Location("19", "20"); 
		Location latlong10 = new Location("20", "20"); 
		
		Incidence incidencia1 = new Incidence("Prueba1", latlong1, agente1); 
		incidencia1.setState(State.CANCELLED);
		agente1.addIncidence(incidencia1);
		Incidence incidencia2 = new Incidence("Prueba2", latlong2, agente1); 
		incidencia2.setState(State.IN_PROCESS);
		agente1.addIncidence(incidencia2);
		Incidence incidencia3 = new Incidence("Prueba3", latlong3, agente1); 
		incidencia3.setState(State.CLOSED);
		agente1.addIncidence(incidencia3);
		Incidence incidencia4 = new Incidence("Prueba4", latlong4, agente2); 
		incidencia4.setState(State.OPEN);
		agente2.addIncidence(incidencia4);
		Incidence incidencia5 = new Incidence("Prueba5", latlong5, agente2); 
		incidencia5.setState(State.IN_PROCESS);
		agente2.addIncidence(incidencia5);
		Incidence incidencia6 = new Incidence("Prueba6", latlong6, agente2); 
		incidencia6.setState(State.IN_PROCESS);
		agente2.addIncidence(incidencia6);
		Incidence incidencia7 = new Incidence("Prueba7", latlong7, agente3); 
		incidencia7.setState(State.CLOSED);
		agente3.addIncidence(incidencia7);
		Incidence incidencia8 = new Incidence("Prueba8", latlong8, agente4); 
		incidencia8.setState(State.CLOSED);
		agente4.addIncidence(incidencia8);
		Incidence incidencia9 = new Incidence("Prueba9", latlong9, agente4); 
		incidencia9.setState(State.IN_PROCESS);
		agente4.addIncidence(incidencia9);
		Incidence incidencia10 = new Incidence("Prueba10", latlong10, agente3); 
		incidencia10.setState(State.OPEN);
		agente3.addIncidence(incidencia10);
		
		incidencesService.addIncidence(incidencia1);
		incidencesService.addIncidence(incidencia2);
		incidencesService.addIncidence(incidencia3);
		incidencesService.addIncidence(incidencia4);
		incidencesService.addIncidence(incidencia5);
		incidencesService.addIncidence(incidencia6);
		incidencesService.addIncidence(incidencia7);
		incidencesService.addIncidence(incidencia8);
		incidencesService.addIncidence(incidencia9);
		incidencesService.addIncidence(incidencia10);
		
		agentsService.addAgent(agente1);
		agentsService.addAgent(agente2);
		agentsService.addAgent(agente3);
		agentsService.addAgent(agente4);
		agentsService.addAgent(agente5);
	}

}