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
import com.uniovi.InciDashboard_e4a.entities.LatLong;
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
		List<LatLong> incis = nots.stream().map(x -> x.getIncidencia().getLocation()).collect(Collectors.toList());
		List<String> incisInfo = nots.stream().map(x -> x.getIncidencia().getInciName()).collect(Collectors.toList());
		
		model.addAttribute("incis", incis);
		model.addAttribute("incisInfo", incisInfo);
		model.addAttribute("nots", nots);
		model.addAttribute("operator", operator);
		return "/index";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexBarra(Model model) {
		return "redirect:/home";
	}

	

}
