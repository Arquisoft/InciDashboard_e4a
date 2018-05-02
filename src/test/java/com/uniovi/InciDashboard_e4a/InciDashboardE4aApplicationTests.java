package com.uniovi.InciDashboard_e4a;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.Operator;
import com.uniovi.InciDashboard_e4a.entities.State;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("INTEGRATION_TEST")
@AutoConfigureMockMvc
public class InciDashboardE4aApplicationTests {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testLanding() throws Exception {
		mvc.perform(get("/")).andExpect(status().is3xxRedirection());
	}

	@Test
	public void testLogin() throws Exception {
		mvc.perform(get("/login")).andExpect(status().isOk()).andExpect(content().string(containsString("login")));
	}

	@Test
	public void testGetIncidencesByState() {
		Agent a = new Agent();
		
		Incidence i1 = new Incidence();
		i1.setState(State.OPEN);
		a.addIncidence(i1);
		
		Incidence i2 = new Incidence();
		i2.setState(State.IN_PROCESS);
		a.addIncidence(i2);
		
		Incidence i3 = new Incidence();
		i3.setState(State.OPEN);
		a.addIncidence(i3);
		
		List<Incidence> list = a.getIncByState(State.OPEN);
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), i1);
		assertEquals(list.get(1), i3);
		
		list = a.getIncByState(State.CANCELLED);
		assertEquals(list.size(), 0);
	}

}
