/**
 * 
 */
package com.uniovi.InciDashboard_e4a.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.Notification;
import com.uniovi.InciDashboard_e4a.entities.Operator;
import com.uniovi.InciDashboard_e4a.services.IncidencesService;
import com.uniovi.InciDashboard_e4a.services.OperatorServices;

@Controller
public class HomeController {

	@Autowired
	IncidencesService incidencesService;
	@Autowired
	OperatorServices operatorService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index(Model model) {
		Operator operator = operatorService.getActiveOperator();
		List<Notification> nots = operator.getNotifications().stream().collect(Collectors.toList());

		model.addAttribute("nots", nots);
		model.addAttribute("operator", operator);
		return "/index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexBarra(Model model) {
		Operator operator = operatorService.getActiveOperator();
		List<Notification> nots = operator.getNotifications().stream().collect(Collectors.toList());

		model.addAttribute("nots", nots);
		model.addAttribute("operator", operator);
		return "/index";
	}

	@RequestMapping(value = "/incidences/list", method = RequestMethod.GET)
	public String list(Model model) {

		List<Incidence> incidencias = incidencesService.getAllIncidences();
		Operator operator = operatorService.getActiveOperator();
		List<Notification> nots = operator.getNotifications().stream().collect(Collectors.toList());

		model.addAttribute("nots", nots);
		model.addAttribute("operator", operator);
		model.addAttribute("allIncidences", incidencias);

		return "/incidence_list";
	}

}
