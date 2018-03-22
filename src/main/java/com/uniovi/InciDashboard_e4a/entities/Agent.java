package com.uniovi.InciDashboard_e4a.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String username;
	private String password;
	private String kind;
	private String role;

	public Agent() {
	}

	@OneToMany
	private Set<Incidence> incidencias = new HashSet<Incidence>();

	public Agent(String username, String password, String kind) {
		this.username = username;
		this.password = password;
		this.kind = kind;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Incidence> getIncidencias() {
		return incidencias;
	}

	public void setIncidencias(Set<Incidence> incidencias) {
		this.incidencias = incidencias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Agent other = (Agent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return username + " Kind: " + kind;
	}

	public void addIncidence(Incidence incidence) {
		this.incidencias.add(incidence);
	}

	public List<Incidence> getIncByState(State s) {
		return this.incidencias.stream().filter(x -> x.getState().equals(s)).collect(Collectors.toList());
	}

	public List<Incidence> getIncOPEN() {
		return this.getIncByState(State.OPEN);
	}

	public List<Incidence> getIncProg() {
		return this.getIncByState(State.IN_PROCESS);
	}

	public List<Incidence> getIncCLOSED() {
		return this.getIncByState(State.CLOSED);
	}

	public List<Incidence> getIncCANCEL() {
		return this.getIncByState(State.CANCELLED);
	}
}
