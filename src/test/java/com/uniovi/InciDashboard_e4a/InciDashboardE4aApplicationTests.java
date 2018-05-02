package com.uniovi.InciDashboard_e4a;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.uniovi.InciDashboard_e4a.entities.LatLong;
import com.uniovi.InciDashboard_e4a.entities.Notification;
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
	public void testGetAgentIncidences() {
		Agent a = new Agent();

		Incidence i1 = new Incidence("i1", new LatLong());
		i1.setState(State.OPEN);
		a.addIncidence(i1);

		Incidence i2 = new Incidence("i2", new LatLong());
		i2.setState(State.IN_PROCESS);
		a.addIncidence(i2);

		Incidence i3 = new Incidence("i3", new LatLong());
		i3.setState(State.OPEN);
		a.addIncidence(i3);

		List<Incidence> list = a.getIncByState(State.OPEN);
		assertEquals(list.size(), 2);
		assertEquals(list.get(0), i1);
		assertEquals(list.get(1), i3);

		List<Incidence> list2 = a.getIncOPEN();
		assertEquals(list.size(), list2.size());

		list = a.getIncByState(State.CANCELLED);
		assertEquals(list.size(), 0);

		list2 = a.getIncCANCEL();
		assertEquals(list.size(), list2.size());

		list = a.getIncByState(State.IN_PROCESS);
		assertEquals(list.size(), 1);

		list2 = a.getIncProg();
		assertEquals(list.size(), list2.size());

	}
	
	@Test
	public void testGetOperatorIncidences() {
		Operator o = new Operator();
		
		Incidence i1 = new Incidence("i1", new LatLong());
		Notification n1 = new Notification(1l,"n1",o);
		i1.setState(State.OPEN);

		Incidence i2 = new Incidence("i2", new LatLong());
		Notification n2 = new Notification(2l,"n2",o);
		i2.setState(State.IN_PROCESS);

		Incidence i3 = new Incidence("i3", new LatLong());
		Notification n3 = new Notification(3l,"n3",o);
		i3.setState(State.OPEN);
		
		Set<Notification> set = new HashSet<Notification>();
		set.add(n1);
		set.add(n2);
		set.add(n3);
		
		o.setNotifications(set);

		List<Incidence> list = o.getIncByState(State.OPEN);
		assertEquals(list.size(), 2);

		List<Incidence> list2 = o.getIncOPEN();
		assertEquals(list.size(), list2.size());

		list = o.getIncByState(State.CANCELLED);
		assertEquals(list.size(), 0);

		list2 = o.getIncCANCEL();
		assertEquals(list.size(), list2.size());

		list = o.getIncByState(State.IN_PROCESS);
		assertEquals(list.size(), 1);

		list2 = o.getIncProg();
		assertEquals(list.size(), list2.size());

	}
	
	@Test
	public void testIncidenceTags() {
		Incidence i = new Incidence();
		
		Set<String> tags = new HashSet<String>();
		tags.add("tag1");
		tags.add("tag2");
		tags.add("tag3");
		
		i.setTags(tags);
		
		assertEquals("tag1,tag2,tag3", i.tagList());
	}

}
