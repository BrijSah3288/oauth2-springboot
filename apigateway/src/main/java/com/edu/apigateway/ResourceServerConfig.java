package com.edu.apigateway;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class ResourceServerConfig {
	
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange()
			.anyExchange()
			.authenticated()
			.and()
			.oauth2ResourceServer()
			.jwt();
		return http.build();
	}
	
	@Bean
	public ReactiveJwtDecoder jwtDecoder() {
		byte[] key = "123456789012345678901234567890AB".getBytes();
		SecretKeySpec originalKey = new SecretKeySpec(key, 0, key.length, "AES");
		
		return NimbusReactiveJwtDecoder.withSecretKey(originalKey).build();
	}

}
