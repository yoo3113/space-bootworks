package com.springboot.controller.exam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/exam02") //1차 url
@RestController	//문자열를 화면에 보내준다.
public class Exam02Controller {
	
	@GetMapping("/rest")		//2차 url(하위)
	public String requestMethod() {
		return "<h2>@restController 예제입니다.</h2>";
	}
}
