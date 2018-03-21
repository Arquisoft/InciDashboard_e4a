package com.uniovi.InciDashboard_e4a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan("com.uniovi.InciDashboard_e4a")
@EnableJpaRepositories("com.uniovi.repositories")
@EntityScan("com.uniovi.entities")
@SpringBootApplication
public class InciDashboardE4aApplication {

	public static void main(String[] args) {
		SpringApplication.run(InciDashboardE4aApplication.class, args);
	}
}
