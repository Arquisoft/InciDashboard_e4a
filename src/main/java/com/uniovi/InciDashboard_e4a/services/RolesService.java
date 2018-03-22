package com.uniovi.InciDashboard_e4a.services;

import org.springframework.stereotype.Service;

 
@Service
public class RolesService {
	String[] roles = {"ROLE_AGENT","ROLE_OPER","ROLE_ADMIN"};
	
	public String[] getRoles() {
		return roles;
	}
}
