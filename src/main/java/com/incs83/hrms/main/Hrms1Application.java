package com.incs83.hrms.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.incs83.hrms")
@EnableJpaRepositories("com.incs83.hrms")
@EntityScan("com.incs83.hrms")
public class Hrms1Application {

	public static void main(String[] args) {
		SpringApplication.run(Hrms1Application.class, args);
	}
}
