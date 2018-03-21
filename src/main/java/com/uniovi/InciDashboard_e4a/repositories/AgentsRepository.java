package com.uniovi.InciDashboard_e4a.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uniovi.InciDashboard_e4a.entities.Agent;

public interface AgentsRepository extends CrudRepository<Agent, Long> {

}