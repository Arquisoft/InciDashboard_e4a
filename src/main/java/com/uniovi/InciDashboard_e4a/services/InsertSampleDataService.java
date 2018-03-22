package com.uniovi.InciDashboard_e4a.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.LatLong;
import com.uniovi.InciDashboard_e4a.entities.Notification;
import com.uniovi.InciDashboard_e4a.entities.Operator;
import com.uniovi.InciDashboard_e4a.entities.State;

@Service
public class InsertSampleDataService {
	
	@Autowired
	AgentsService agentsService;
	
	@Autowired
	IncidencesService incidencesService;
	
	@Autowired
	OperatorServices operatorService;
	
	@Autowired
	NotificationsService notService;
	
	@PostConstruct
	public void init() {
		
		Agent agente1 = new Agent("a1", "123456", "1"); 
		Agent agente2 = new Agent("a2", "123456", "1"); 
		Agent agente3 = new Agent("a3", "123456", "1"); 
		Agent agente4 = new Agent("a4", "123456", "1"); 
		Agent agente5 = new Agent("a5", "123456", "1"); 
		Operator op1 = new Operator("ivan@gmail.com","ivan", "123456", 1); 
		Operator op2 = new Operator("antonio@gmail.com","antonio", "123456", 1); 
		Operator op3 = new Operator("hugo@gmail.com","hugo", "123456", 1); 
		Operator op4 = new Operator("mirza@gmail.com","mirza", "123456", 1); 
		Operator op5 = new Operator("pasadores@gmail.com","pasadores", "123456", 1);
		agentsService.addAgent(agente1);
		agentsService.addAgent(agente2);
		agentsService.addAgent(agente3);
		agentsService.addAgent(agente4);
		agentsService.addAgent(agente5);
		operatorService.addOperator(op1);
		operatorService.addOperator(op2);
		operatorService.addOperator(op3);
		operatorService.addOperator(op4);
		operatorService.addOperator(op5);
		LatLong latlong1 = new LatLong("11", "20"); 
		LatLong latlong2 = new LatLong("12", "20"); 
		LatLong latlong3 = new LatLong("13", "20"); 
		LatLong latlong4 = new LatLong("14", "20"); 
		LatLong latlong5 = new LatLong("15", "20"); 
		LatLong latlong6 = new LatLong("16", "20"); 
		LatLong latlong7 = new LatLong("17", "20"); 
		LatLong latlong8 = new LatLong("18", "20"); 
		LatLong latlong9 = new LatLong("19", "20"); 
		LatLong latlong10 = new LatLong("20", "20"); 
		Incidence incidencia1 = new Incidence("Prueba1", latlong1, agente1); 
		incidencia1.setState(State.CANCELLED);
		Incidence incidencia2 = new Incidence("Prueba2", latlong2, agente1); 
		incidencia2.setState(State.IN_PROCESS);
		Incidence incidencia3 = new Incidence("Prueba3", latlong3, agente1); 
		incidencia3.setState(State.CLOSED);
		Incidence incidencia4 = new Incidence("Prueba4", latlong4, agente2); 
		incidencia4.setState(State.OPEN);
		Incidence incidencia5 = new Incidence("Prueba5", latlong5, agente2); 
		incidencia5.setState(State.IN_PROCESS);
		Incidence incidencia6 = new Incidence("Prueba6", latlong6, agente2); 
		incidencia6.setState(State.IN_PROCESS);
		Incidence incidencia7 = new Incidence("Prueba7", latlong7, agente3); 
		incidencia7.setState(State.CLOSED);
		Incidence incidencia8 = new Incidence("Prueba8", latlong8, agente4); 
		incidencia8.setState(State.CLOSED);
		Incidence incidencia9 = new Incidence("Prueba9", latlong9, agente4); 
		incidencia9.setState(State.IN_PROCESS);
		Incidence incidencia10 = new Incidence("Prueba10", latlong10, agente3); 
		incidencia10.setState(State.OPEN);
	
		incidencesService.addIncident(incidencia1);
		incidencesService.addIncident(incidencia2);
		incidencesService.addIncident(incidencia3);
		incidencesService.addIncident(incidencia4);
		incidencesService.addIncident(incidencia5);
		incidencesService.addIncident(incidencia6);
		incidencesService.addIncident(incidencia7);
		incidencesService.addIncident(incidencia8);
		incidencesService.addIncident(incidencia9);
		incidencesService.addIncident(incidencia10);
		
		Notification n1 = new Notification("La hemos cagao",op2,incidencia1);
		Notification n2 = new Notification("Se ha roto",op2,incidencia2);
		Notification n3 = new Notification("Pa cuenca a reparar",op2,incidencia3);
		Notification n4 = new Notification("La hemos cagao x2",op2,incidencia4);
		Notification n5 = new Notification("Notificacion de incidencia 5",op3,incidencia5);
		Notification n6 = new Notification("Notificacion de incidencia 6",op1,incidencia6);
		Notification n7 = new Notification("Notificacion de incidencia 7",op3,incidencia7);
		Notification n8 = new Notification("Notificacion de incidencia 8",op4,incidencia8);
		Notification n9 = new Notification("Notificacion de incidencia 9",op1,incidencia9);
		Notification n10 = new Notification("Notificacion de incidencia 10",op3,incidencia10);
		
		notService.addIncident(n1);
		notService.addIncident(n2);
		notService.addIncident(n3);
		notService.addIncident(n4);
		notService.addIncident(n5);
		notService.addIncident(n6);
		notService.addIncident(n7);
		notService.addIncident(n8);
		notService.addIncident(n9);
		notService.addIncident(n10);
		
		incidencia1.addNot(n1);
		incidencia1.addNot(n2);
		incidencia1.addNot(n3);
		incidencia1.addNot(n4);
		incidencia1.addNot(n5);
		incidencia1.addNot(n6);
		incidencia1.addNot(n7);
		incidencia1.addNot(n8);
		incidencia1.addNot(n9);
		incidencia1.addNot(n10);
		
		incidencesService.addIncident(incidencia1);
		incidencesService.addIncident(incidencia2);
		incidencesService.addIncident(incidencia3);
		incidencesService.addIncident(incidencia4);
		incidencesService.addIncident(incidencia5);
		incidencesService.addIncident(incidencia6);
		incidencesService.addIncident(incidencia7);
		incidencesService.addIncident(incidencia8);
		incidencesService.addIncident(incidencia9);
		incidencesService.addIncident(incidencia10);
		
		agente1.addIncidence(incidencia1);
		agente1.addIncidence(incidencia2);
		agente1.addIncidence(incidencia3);
		agente2.addIncidence(incidencia4);
		agente2.addIncidence(incidencia5);
		agente2.addIncidence(incidencia6);
		agente3.addIncidence(incidencia7);
		agente4.addIncidence(incidencia8);
		agente4.addIncidence(incidencia9);
		agente3.addIncidence(incidencia10);
		
		agentsService.updateAgent(agente1);
		agentsService.updateAgent(agente2);
		agentsService.updateAgent(agente3);
		agentsService.updateAgent(agente4);
		agentsService.updateAgent(agente5);
		operatorService.updateOperator(op1);
		operatorService.updateOperator(op2);
		operatorService.updateOperator(op3);
		operatorService.updateOperator(op4);
		operatorService.updateOperator(op5);
	}

}