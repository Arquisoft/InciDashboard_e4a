package com.uniovi.InciDashboard_e4a.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.repositories.IncidencesRepository;

import utils.StateChecker;

@Service
public class IncidencesService {
	@Autowired
	private IncidencesRepository incidencesRepository;

	public List<Incidence> getAllIncidences() {
		return incidencesRepository.findAll();
	}

	public Incidence addIncidence(Incidence incident) {
		return incidencesRepository.save(incident);
	}

	public void deleteIncidenceByName(String inciName) {
		incidencesRepository.deleteByInciName(inciName);
	}

	public Optional<Incidence> getIncidence(Long id) {
		return incidencesRepository.findById(id);
	}

	public void changeStatus(Long id, Long status) {		
		incidencesRepository.updateStatus(id, StateChecker.getState(status)); 
		
	}

	public Incidence findByInciName(String name){ 
		return incidencesRepository.findByInciName(name);
	}
}
