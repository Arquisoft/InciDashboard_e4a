package com.uniovi.InciDashboard_e4a.entities;

public class NotificationPOJO {
	public String id;
	public String descripcion;
	public Incidence incidencia;
	public Operator operator;
	
	public NotificationPOJO(String id, String descripcion, Incidence incidencia, Operator operator) {
		this.id = id; 
		this.descripcion = descripcion;
		this.incidencia = incidencia;
		this.operator = operator;
	}
}
