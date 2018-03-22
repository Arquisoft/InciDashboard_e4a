package mocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.entities.Incidence;
import com.uniovi.InciDashboard_e4a.entities.LatLong;

@Component
public class IncidenceGenerator {

	private List<Agent> agents;

	private Random generator;

	private final static String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	private int size;

	public IncidenceGenerator() {
		this.agents = new ArrayList<Agent>();
		this.generator = new Random();
		this.size = 10;
	}

	public void setPossibleAgents(List<Agent> possibleAgents) {
		this.agents = possibleAgents;
	}

	public Incidence generateRandomIncident() {
		Incidence incident = new Incidence();
		incident.setInciName(this.createRandomString(this.size));
		incident.setAgent(this.pickRandomAgent());
		incident.setLocation(this.createRandomLocation());
		this.createRandomPropertiesFor(incident);
		return incident;
	}

	private void createRandomPropertiesFor(Incidence incident) {
		Map<String, Object> properties = new HashMap<String, Object>();

		properties.put("priority", generator.nextInt(4));

		if (incident.getAgent().getKind().equals("Sensor")) {
			double temperature = generator.nextDouble() * 80 - 30;
			properties.put("temperature", temperature);
		}

		incident.setProperties(properties);
	}

	private LatLong createRandomLocation() {
		Double lat = (generator.nextDouble() - 0.5) * 180;
		Double lon = (generator.nextDouble() - 0.5) * 360;
		return new LatLong(lat.toString(), lon.toString());
	}

	private Agent pickRandomAgent() {
		if (this.agents.size() == 0) {
			return null;
		}
		int agentIndex = this.generator.nextInt(this.agents.size());
		return this.agents.get(agentIndex);
	}

	private String createRandomString(int length) {
		String randomStr = "";
		for (int i = 0; i < length; i++) {
			int charIndex = generator.nextInt(ABC.length());
			randomStr += ABC.charAt(charIndex);
		}
		return randomStr;
	}

}
