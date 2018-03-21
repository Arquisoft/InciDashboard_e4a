package com.uniovi.InciDashboard_e4a.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.entities.Notification;
import com.uniovi.InciDashboard_e4a.repositories.NotificationsRepository;
@Service
public class NotificationsService {
	@Autowired
	private NotificationsRepository notificationsRepository;

	public List<Notification> getAllNotifications() {
		return notificationsRepository.findAll();
	}

	public void addIncident(Notification n) {
		notificationsRepository.save(n);
	}
}
