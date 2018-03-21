package com.uniovi.InciDashboard_e4a.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.repositories.IncidencesRepository;

@Service
public class IncidencesService {
	@Autowired
	private IncidencesRepository incidentsRepository;

	public List<Incidence> getAllIncidents() {
		return incidentsRepository.findAll();
	}

	public void addIncident(Incidence incident) {
		incidentsRepository.save(incident);
	}

	public void deleteIncidentByName(String inciName) {
		incidentsRepository.deleteByInciName(inciName);
	}
}
