package com.jwbook2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity //웹 보안기능
@Configuration //스프링 Bean 인식(클래스로 등록)
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/","/auth", "/home/**").permitAll() // 해당 경로 접근 허용
					.requestMatchers("/user/**").hasRole("USER") //USER 권한 허용
					.requestMatchers("/admin/**").hasRole("ADMIN") //ADMIN 권한 허용
					.anyRequest().authenticated() // 나머지는 인증 필요
					
			)
			.formLogin(form -> form
					.loginPage("/login") //사용자 로그인 페이지 요청
					.permitAll()
			)
			.logout(logout -> logout
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
					.logoutSuccessUrl("/auth")
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONDID")
			);
			
			
			
			
		return http.build();
	}//SecurityFilterChain 닫기
	
	//비밀번호 암호화
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//사용자 계정 생성
	@Bean
	UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
				.username("guest")
				.password(passwordEncoder().encode("g1234"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder().encode("a1234"))
				.roles("ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
}
