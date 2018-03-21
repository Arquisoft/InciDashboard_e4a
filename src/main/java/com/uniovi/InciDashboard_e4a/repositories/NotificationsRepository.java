package com.uniovi.InciDashboard_e4a.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.InciDashboard_e4a.entities.Notification;

public interface NotificationsRepository extends CrudRepository<Notification, Long>{

	public List<Notification> findAll();
}