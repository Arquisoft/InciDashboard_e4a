package com.uniovi.InciDashboard_e4a.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.uniovi.InciDashboard_e4a.json.Deserializer;
import com.uniovi.InciDashboard_e4a.json.Serializer;

import utils.IncidentProperties2Json;

@JsonDeserialize(using = Deserializer.class)
@JsonSerialize(using = Serializer.class)
@Entity
public class Incidence {

	@Id
	@GeneratedValue
	private Long id;

	private String inciName;
	private LatLong location;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "agent_id")
	private Agent agent;

	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	private Set<String> tags = new HashSet<String>();

	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	private List<String> moreInfo = new ArrayList<String>();

	@Convert(converter = IncidentProperties2Json.class)
	private Map<String, Object> properties = new HashMap<String, Object>();
	
	@Enumerated(EnumType.STRING)
	private State state;

	@OneToOne(mappedBy = "incidencia")
	private Notification notification;

	public Incidence() {
		
	}

	public Incidence(String name, LatLong location) {
		if (name.equals("") || location == null)
			throw new IllegalArgumentException("Incident fields cannot be empty");

		this.inciName = name;
		this.location = location;
	}

	public Incidence(String name, LatLong latLng, Agent agent) {
		this(name, latLng);
		this.setAgent(agent);
	}

	public void addMoreInfo(String info) {
		this.moreInfo.add(info);
	}

	public void addProperty(String property, Object value) {
		this.properties.put(property, value);
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public void setInciName(String inciName) {
		this.inciName = inciName;
	}

	public void setLocation(LatLong location) {
		this.location = location;
	}

	public Long getId() {
		return id;
	}

	public String getInciName() {
		return inciName;
	}

	public LatLong getLocation() {
		return location;
	}

	public Set<String> getTags() {
		return tags;
	}

	public List<String> getMoreInfo() {
		return moreInfo;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public void setMoreInfo(List<String> moreInfo) {
		this.moreInfo = moreInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((inciName == null) ? 0 : inciName.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Incidence other = (Incidence) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Incidence [id=").append(id).append(", inciName=").append(inciName).append(", location=")
				.append(location).append(", tags=").append(tags).append(", moreInfo=").append(moreInfo)
				.append(", properties=").append(properties).append("]");
		return builder.toString();
	}

	public void addNot(Notification n1) {
		this.notification = n1;
	}
	
	public String tagList() {
		String cadena = "";
		for (String s : tags) {
			cadena +=s+",";
		}
		return cadena;
	}
	
	public boolean isOpen() {
		return this.state.equals(State.OPEN);
	}
	
	public boolean isInProg() {
		return this.state.equals(State.IN_PROCESS);
	}
	
	public boolean isClosed() {
		return this.state.equals(State.CLOSED);
	}
	
	public boolean isCancelled() {
		return this.state.equals(State.CANCELLED);
	}

}
