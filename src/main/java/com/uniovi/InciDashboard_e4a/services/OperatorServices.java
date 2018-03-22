package com.uniovi.InciDashboard_e4a.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.entities.Operator;
import com.uniovi.InciDashboard_e4a.repositories.OperatorRepository;

@Service
public class OperatorServices {
	
	@Autowired
	private OperatorRepository repo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public void getAll() {
		repo.findAll();
	}

	public void addOperator(Operator o) {
		o.setPassword(bCryptPasswordEncoder.encode(o.getPassword()));
		this.repo.save(o);
	}

	public void updateOperator(Operator u) {
		repo.save(u);
	}
	
	public Operator findByEmail(String email){
		return repo.findByEmail(email);
	}
	
	public Operator getActiveOperator() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Operator activeUser = findByEmail(username);
		return activeUser;
	}

}
