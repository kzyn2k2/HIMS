package com.hims.app.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.hims.app.converter.KeycloakJwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(c -> c.anyRequest().authenticated());
		http.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter())));
		http.sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.cors(c -> {
			CorsConfigurationSource source = req -> {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(List.of("*"));
				config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
				config.setAllowedHeaders(List.of("*"));
				return config;
			};
			c.configurationSource(source);
		});
		return http.build();
	}
	
}
