package com.uniovi.InciDashboard_e4a.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.InciDashboard_e4a.repositories.OperatorRepository;

public class OperatorServices {
	@Autowired
	private OperatorRepository repo;
	
	public void getAll() {
		repo.findAll();

	}
}
