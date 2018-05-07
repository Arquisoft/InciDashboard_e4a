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
import com.uniovi.InciDashboard_e4a.entities.State;
import com.uniovi.InciDashboard_e4a.services.IncidencesService;
import com.uniovi.InciDashboard_e4a.services.OperatorServices;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration(classes = InciDashboardE4aApplication.class, loader = SpringBootContextLoader.class)
@WebAppConfiguration
@ActiveProfiles("INTEGRATION_TEST")
public class DeleteIncidenceState {

	@Autowired
	IncidencesService incidencessService;

	private Incidence incidence;
	private int size;
	private List<Incidence> incidences ;

	@Given("^a incidence with name \"([^\"]*)\"$")
	public void a_incidence_with_name(String name) throws Throwable {
		incidences = incidencessService.getAllIncidences();
		size=incidences.size();
		incidences.stream().forEach(x-> {if(x.getInciName().equals(name)) incidence=x;});
	}

	@When("^it deletes the incidence$")
	public void it_deletes_the_incidence() throws Throwable {
		incidencessService.deleteIncidenceByName(incidence.getInciName());
		incidences = incidencessService.getAllIncidences();
	}

	@Then("^there are one less$")
	public void there_are_one_less() throws Throwable {
		assertTrue(size==incidences.size()+1);
	}

}
