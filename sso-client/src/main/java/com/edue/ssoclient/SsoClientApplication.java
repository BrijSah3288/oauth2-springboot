package com.edue.ssoclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@Controller
public class SsoClientApplication extends WebSecurityConfigurerAdapter {

	@GetMapping("/home")
	public String home() {
		return "home.html";
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.oauth2Login();
		http.authorizeRequests()
			.anyRequest()
			.authenticated();
	}
	
	@Bean
	public ClientRegistrationRepository clientRepository() {
		ClientRegistration clientRegistration = clientRegistration();
		return new InMemoryClientRegistrationRepository(clientRegistration);
	}
	
	
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SsoClientApplication.class, args);
	}
	
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	
	private ClientRegistration clientRegistration() {
		return CommonOAuth2Provider.GITHUB
					.getBuilder("github")
					.clientId("clientId")
					.clientSecret("clientSecret")
					.build();
		
	}
	
	

}
