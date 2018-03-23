package com.uniovi.InciDashboard_e4a.steps;

import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.uniovi.InciDashboard_e4a.InciDashboardE4aApplication;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.Notification;
import com.uniovi.InciDashboard_e4a.entities.Operator;
import com.uniovi.InciDashboard_e4a.entities.State;
import com.uniovi.InciDashboard_e4a.services.OperatorServices;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = InciDashboardE4aApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class ChangeIncidenceState {

	@Autowired
	OperatorServices operatorsService;

	private Operator operator;
	private Incidence incidence;

	@Given("^a operator with the email \"([^\"]*)\"$")
	public void a_operator_with_email(String email) throws Throwable {
		operator = operatorsService.findByEmail(email);
	}

	@When("^he wants to change the state of a incidence with the name \"([^\"]*)\"$")
	public void he_wants_to_change_the_state_of_a_incidence_name(String name) throws Throwable {
		for (Notification n : operator.getNotifications()) {
			if (n.getIncidencia().getInciName().equals(name)) {
				incidence = n.getIncidencia();
				break;
			}
		}
	}

	@Then("^he changes the state \"([^\"]*)\"$")
	public void the_system_will_notice_with_message(String estado) throws Throwable {
		State state = incidence.getState();
		switch (estado.toLowerCase()) {
		case "closed":
			incidence.setState(State.CLOSED);
			break;
		case "open":
			incidence.setState(State.OPEN);
			break;
		case "in_process":
			incidence.setState(State.IN_PROCESS);
			break;
		case "cancelled":
			incidence.setState(State.CANCELLED);
			break;
		}
		assertTrue(!state.equals(incidence.getState()));
	}

}
