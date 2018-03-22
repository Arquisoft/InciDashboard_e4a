package com.uniovi.InciDashboard_e4a.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.InciDashboard_e4a.entities.Incidence;

public interface IncidencesRepository extends CrudRepository<Incidence, Long> {

	public List<Incidence> findAll();

	public void deleteByInciName(String inciName);
	
	public Optional<Incidence> findById(Long id);

}