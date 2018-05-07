package com.uniovi.InciDashboard_e4a;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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
import com.uniovi.InciDashboard_e4a.entities.IncidencePOJO;
import com.uniovi.InciDashboard_e4a.entities.LatLong;
import com.uniovi.InciDashboard_e4a.entities.Notification;
import com.uniovi.InciDashboard_e4a.entities.Operator;
import com.uniovi.InciDashboard_e4a.entities.State;
import com.uniovi.InciDashboard_e4a.services.IncidencesService;

import utils.Incidence2Pojo;
import utils.StateChecker;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("INTEGRATION_TEST")
@AutoConfigureMockMvc
public class InciDashboardE4aApplicationTests {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private IncidencesService incidencesService;
	
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
		Notification n1 = new Notification(1l, "n1", o);
		n1.setIncidencia(i1);
		i1.setState(State.OPEN);

		Incidence i2 = new Incidence("i2", new LatLong());
		Notification n2 = new Notification(2l, "n2", o);
		n2.setIncidencia(i2);
		i2.setState(State.IN_PROCESS);

		Incidence i3 = new Incidence("i3", new LatLong());
		Notification n3 = new Notification(3l, "n3", o);
		n3.setIncidencia(i3);
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
	public void testIncidences() {
		Incidence i = new Incidence();

		Set<String> tags = new HashSet<String>();
		tags.add("tag1");
		tags.add("tag2");
		tags.add("tag3");

		i.setTags(tags);

		assertEquals("tag1,tag2,tag3", i.tagList());

		i.setState(State.OPEN);
		assertTrue(i.isOpen());
		i.setState(State.IN_PROCESS);
		assertTrue(i.isInProg());
		i.setState(State.CLOSED);
		assertTrue(i.isClosed());
		i.setState(State.CANCELLED);
		assertTrue(i.isCancelled());

		i.setInciName("i1");
		assertEquals(i.getInciName(), "i1");

		LatLong l1 = new LatLong();
		i.setLocation(l1);
		assertEquals(i.getLocation(), l1);

		String a = "123AP";
		i.setAgent(a);
		assertEquals(i.getAgent(), a);

		Notification n1 = new Notification();
		i.setNotification(n1);
		assertEquals(i.getNotification(), n1);

		List<String> list = new ArrayList<String>();
		list.add("info1");
		list.add("info2");
		i.setMoreInfo(list);
		assertEquals(i.getMoreInfo(), list);
	}

	@Test
	public void testOperators() {
		Operator o = new Operator();

		o.setEmail("email");
		assertEquals(o.getEmail(), "email");

		o.setIsAdmin(0);
		assertEquals(o.getIsAdmin(), 0);

		o.setOperatorname("name");
		assertEquals(o.getOperatorname(), "name");

		o.setPassword("passwd");
		assertEquals(o.getPassword(), "passwd");

		o.setRole("role");
		assertEquals(o.getRole(), "role");
	}

	@Test
	public void testAgents() {
		Agent a = new Agent();
		
		a.setId(1l);
		assertEquals(a.getId(), 1l, 0.1);
		
		a.setKind("kind");
		assertEquals(a.getKind(), "kind");
		
		a.setPassword("passwd");
		assertEquals(a.getPassword(), "passwd");
		
		a.setRole("role");
		assertEquals(a.getRole(), "role");
		
		a.setUsername("user");
		assertEquals(a.getUsername(), "user");
	}
	
	@Test 
	public void testLatLong() {
		LatLong ll = new LatLong("10.1", "11.156");
		
		assertEquals(ll.getLatitude(), "10.1");
		assertEquals(ll.getLongitude(), "11.156");
		
		ll.setLatitude("-10");
		assertEquals(ll.getLatitude(), "-10");
		
		ll.setLongitude("-56.10");
		assertEquals(ll.getLongitude(), "-56.10");
	}
	@Test (expected=IllegalArgumentException.class)
	public void testLatLongNull() {
		LatLong ll = new LatLong("10.1", null);
		ll.setLatitude("10.256548");
	}
	@Test
	public void testStateChecher() {
		assertEquals(StateChecker.getState((long) 1), State.OPEN);
		assertEquals(StateChecker.getState((long) 2), State.IN_PROCESS);
		assertEquals(StateChecker.getState((long) 3), State.CLOSED);
		assertEquals(StateChecker.getState((long) 4), State.CANCELLED);
		assertEquals(StateChecker.getState((long) 5), State.OPEN);
	}

}
