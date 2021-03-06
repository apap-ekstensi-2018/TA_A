package com.siasisten;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SIAsistenApplication {
	public static void main(String[] args) {
		SpringApplication.run(SIAsistenApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public Authentication auth() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}
}
