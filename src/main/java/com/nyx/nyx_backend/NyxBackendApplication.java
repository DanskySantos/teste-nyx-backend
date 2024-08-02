package com.nyx.nyx_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class NyxBackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(NyxBackendApplication.class, args);
	}

}
