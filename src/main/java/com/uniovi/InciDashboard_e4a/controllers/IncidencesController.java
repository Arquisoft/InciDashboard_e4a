package com.uniovi.InciDashboard_e4a.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.Notification;
import com.uniovi.InciDashboard_e4a.entities.Operator;
import com.uniovi.InciDashboard_e4a.services.IncidencesService;
import com.uniovi.InciDashboard_e4a.services.OperatorServices;

@Controller
public class IncidencesController {
	 
	@Autowired
	IncidencesService incidencesService;
	@Autowired
	OperatorServices operatorService;
	
	@RequestMapping(value = "/incidences/list", method = RequestMethod.GET)
	public String list(Model model) {

		
		Operator operator = operatorService.getActiveOperator();
		List<Incidence> incidencias = new ArrayList<Incidence>();
		operator.getNotifications().forEach(p-> incidencias.add(p.getIncidencia()));
		List<Notification> nots = operator.getNotifications().stream().collect(Collectors.toList());
		
		model.addAttribute("nots", nots);
		model.addAttribute("operator", operator);
		model.addAttribute("allIncidences", incidencias);

		return "/incidences/list";
	}
	
	@RequestMapping(value = "/incidences/{id}", method = RequestMethod.GET)
	public String info(Model model, @PathVariable Long id) {
		
		Incidence incidencia = incidencesService.getIncidence(id).get();
		Operator operator = operatorService.getActiveOperator();
		List<Notification> nots = operator.getNotifications().stream().collect(Collectors.toList());

		model.addAttribute("nots", nots);
		model.addAttribute("operator", operator);
		model.addAttribute("incidence", incidencia);
		
		return "/incidences/info";
	}
	
	@RequestMapping(value = "/incidences/{id}/{status}", method = RequestMethod.GET)
	public String statusChange(Model model, @PathVariable Long id, @PathVariable Long status) {
		
		Incidence incidencia = incidencesService.getIncidence(id).get();
		Operator operator = operatorService.getActiveOperator();
		List<Notification> nots = operator.getNotifications().stream().collect(Collectors.toList());
		
		model.addAttribute("nots", nots);
		model.addAttribute("operator", operator);
		model.addAttribute("incidence", incidencia);
		
		incidencesService.changeStatus(id, status);
		
		return "redirect:/incidences/list";
	}
}
