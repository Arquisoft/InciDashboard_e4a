package com.uniovi.InciDashboard_e4a.steps;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniovi.InciDashboard_e4a.InciDashboardE4aApplication;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.services.IncidencesService;
import com.uniovi.InciDashboard_e4a.services.NotificationsService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;;

@ContextConfiguration(classes = InciDashboardE4aApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class NotificationsSteps {

	@Autowired
	IncidencesService incidencesService;

	@Autowired
	NotificationsService notificationService;

	private Incidence incidence;

	@Given("^a incidence \"([^\"]*)\"$")
	public void a_incidence(String name) throws Throwable {
		incidence = incidencesService.findByInciName(name);
	}

	@Then("^the system will notice with message \"([^\"]*)\"$")
	public void the_system_will_notice_with_message(String message) throws Throwable {
		assertTrue(incidence.getNotification().getDescription().equals(message));
	}
}
