package com.uniovi.InciDashboard_e4a.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.State;

public interface IncidencesRepository extends CrudRepository<Incidence, Long> {

	public List<Incidence> findAll();

	public void deleteByInciName(String inciName);
	
	public Optional<Incidence> findById(Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Incidence SET state = ?2 WHERE id = ?1")
	public void updateStatus(Long id, State state);
  
  public Incidence findByInciName(String name);


}