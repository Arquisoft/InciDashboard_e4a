package com.uniovi.InciDashboard_e4a.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uniovi.InciDashboard_e4a.entities.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Long> {

	List<Operator> findByOperatorname(String operatorName);

}