package com.uniovi.InciDashboard_e4a.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String description;

	@ManyToOne
	private Operator operator;

	@OneToOne
	private Incidence incidencia;

	/**
	 * Default constructor
	 */
	public Notification() {

	}

	public Notification(Long id, String description, Operator operator) {
		super();
		this.id = id;
		this.description = description;
		this.operator = operator;
	}
	
	public Notification(String description, Operator operator,Incidence i) {
		super();
		this.description = description;
		this.operator = operator;
		this.incidencia = i;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
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
		Notification other = (Notification) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		return true;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Incidence getIncidencia() {
		return incidencia;
	}

	public void setIncidencia(Incidence incidencia) {
		this.incidencia = incidencia;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String tagList() {
		return this.incidencia.tagList();
	}
}
