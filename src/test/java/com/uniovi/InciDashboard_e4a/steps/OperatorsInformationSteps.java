package com.uniovi.InciDashboard_e4a.steps;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniovi.InciDashboard_e4a.InciDashboardE4aApplication;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.Notification;
import com.uniovi.InciDashboard_e4a.entities.Operator;
import com.uniovi.InciDashboard_e4a.services.OperatorServices;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;;

@ContextConfiguration(classes = InciDashboardE4aApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class OperatorsInformationSteps {

	@Autowired
	OperatorServices operatorsService;

	private Operator operator;
	private List<Incidence> incidences;

	@Given("^a operator with email \"([^\"]*)\"$")
	public void a_operator_with_email(String email) throws Throwable {
		operator = operatorsService.findByEmail(email);
	}

	@When("^he wants control their incidences$")
	public void he_wants_control_their_incicendes() throws Throwable {
		for (Notification n : operator.getNotifications()) {
			incidences.add(n.getIncidencia());
		}
	}

	@Then("^the system will give his (\\d+) incidences$")
	public void the_system_will_notice_with_message(int numero) throws Throwable {
		assertTrue(numero == incidences.size());
	}
}
