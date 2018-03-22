package com.uniovi.InciDashboard_e4a.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.repositories.AgentsRepository;
@Service
public class AgentsService {
	@Autowired
	private AgentsRepository agentsRepository;

	public void addAgent(Agent agent) {
		this.agentsRepository.save(agent);
	}

	public Agent findAgentByUsername(String string) {
		return agentsRepository.findByUsername(string);
	}

	public List<Agent> findAll() {
		List<Agent> agentes = new ArrayList<Agent>(); 
		agentsRepository.findAll().forEach(p -> agentes.add(p));
		return agentes;
	}
}
