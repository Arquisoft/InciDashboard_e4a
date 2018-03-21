package com.uniovi.InciDashboard_e4a.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.repositories.AgentsRepository;

public class AgentsService {
	@Autowired
	private AgentsRepository agentsRepository;

	public void addAgent(Agent agent) {
		this.agentsRepository.save(agent);
	}
}
