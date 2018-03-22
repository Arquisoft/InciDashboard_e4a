/**
 * 
 */
package com.uniovi.InciDashboard_e4a.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.services.AgentsService;
import com.uniovi.InciDashboard_e4a.services.IncidencesService;

@Controller
public class HomeController {
	
	@Autowired
	IncidencesService incidencesService;
	@Autowired
	AgentsService agentsService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index(Model model) {
		Agent agent = agentsService.getActiveAgent();
		
		model.addAttribute("agent", agent);
		return "/index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexBarra(Model model) {
		Agent agent = agentsService.getActiveAgent();
		
		model.addAttribute("agent", agent);
		return "/index";
	}
	
	@RequestMapping(value = "/incidences/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Incidence> incidencias = incidencesService.getAllIncidents();
		
		model.addAttribute("allIncidences" , incidencias); 
		return "/incidence_list";
	}

}
