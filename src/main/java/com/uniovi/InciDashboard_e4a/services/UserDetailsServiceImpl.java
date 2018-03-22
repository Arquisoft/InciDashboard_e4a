/**
 * 
 */
package com.uniovi.InciDashboard_e4a.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uniovi.InciDashboard_e4a.entities.Agent;
import com.uniovi.InciDashboard_e4a.repositories.AgentsRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AgentsRepository agentsRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Agent user = agentsRepository.findByUsername(username);
		// System.out.println(user);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("AGENT"));

		User u = new User(user.getUsername(), user.getPassword(), grantedAuthorities);
		return u;

	}
}
