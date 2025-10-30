package com.jwbook2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity //웹 보안기능
@Configuration //스프링 Bean 인식(클래스로 등록)
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/").permitAll() // 해당 경로 접근 허용
					.anyRequest().authenticated() // 나머지는 인증 필요
			);
		return http.build();
	}
	
}
