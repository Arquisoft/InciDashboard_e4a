package com.uniovi.InciDashboard_e4a.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.repositories.OperatorRepository;
@Service
public class OperatorServices {
	@Autowired
	private OperatorRepository repo;
	
	public void getAll() {
		repo.findAll();

	}
}
