package com.jwbook2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping("/auth")
	public String method1() {
		return "auth/viewPage";
	}
	//홈페이지
	@GetMapping("/home/main")
	public String method2(Model model) {
		model.addAttribute("data", "homePage.html");
		return "auth/homePage";
	}
	
	//회원 전용 페이지
	@GetMapping("/user/main")
	public String method3(Model model) {
		model.addAttribute("data", "userPage.html");
		return "auth/userPage";
	}
		
	//관리자 전용 페이지
	@GetMapping("/admin/main")
	public String method4(Model model) {
		model.addAttribute("data", "adminPage.html");
		return "auth/adminPage";
	}
	
	//로그인 페이지
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
}
