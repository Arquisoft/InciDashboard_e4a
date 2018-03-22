/**
 * 
 */
package com.uniovi.InciDashboard_e4a.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.services.AgentsService;
import com.uniovi.InciDashboard_e4a.services.SecurityService;

@Controller
public class LoginController {

	@Autowired
	private AgentsService agentsService;
	@Autowired
	private SecurityService securityService;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "/login/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model,@ModelAttribute Agent u) {
		String username = u.getUsername();
		String passwd = u.getPassword();
		System.out.println(u);
		Agent intento = agentsService.findAgentByUsername(username);
		if(intento == null )
			return "redirect:/login";
		securityService.autoLogin(username, passwd);
		return "redirect:/home";
	}

	@RequestMapping(value = "/login/error", method = RequestMethod.GET)
	public String loginError(Model model) {
		return "/login/error";
	}

}
