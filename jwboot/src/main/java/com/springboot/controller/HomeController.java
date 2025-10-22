package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller //스프링 컨테이너에 빈(Bean) - 객체로 등록한다.
public class HomeController {

	
	@RequestMapping("/")
	public String home() {
		return "home"; //home.html
	}
	
	@GetMapping("/time")
	public String timeMethod() {
		return "pages/time"; //pages/time.html
	}
}
