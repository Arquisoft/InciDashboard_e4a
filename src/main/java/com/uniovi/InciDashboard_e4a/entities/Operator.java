package com.uniovi.InciDashboard_e4a.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Operator {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String email;
	private String password;

	private String operatorname;
	private int isAdmin;

	private String role;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "operator_id")
	private Set<Notification> notifications = new HashSet<>();

	public Operator() {
	}

	public Operator(Long id, String email, String operatorname, int isAdmin) {
		super();
		this.id = id;
		this.email = email;
		this.operatorname = operatorname;
		this.isAdmin = isAdmin;
	}
	
	public Operator(String email,String operatorname,String passwd, int isAdmin) {
		super();
		this.password = passwd;
		this.email = email;
		this.operatorname = operatorname;
		this.isAdmin = isAdmin;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Operator other = (Operator) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Operator [email=" + email + ", operatorname=" + operatorname + ", isAdmin=" + isAdmin + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOperatorname() {
		return operatorname;
	}

	public void setOperatorname(String operatorname) {
		this.operatorname = operatorname;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Set<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<Notification> notifications) {
		this.notifications = notifications;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Incidence> getIncByState(State s) {
		List<Incidence> inc = notifications.stream().map(x -> x.getIncidencia()).collect(Collectors.toList());
		return inc.stream().filter(x -> x.getState().equals(s)).collect(Collectors.toList());
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
