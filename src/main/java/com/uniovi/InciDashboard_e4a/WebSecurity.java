package com.uniovi.InciDashboard_e4a;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/css/*", "/img/", "/script/*", "/").permitAll().and()
				.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home")
				.failureUrl("/login.html?error=true").and().logout().permitAll();
	}
}