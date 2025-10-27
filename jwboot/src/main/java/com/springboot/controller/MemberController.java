package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.dto.MemberDTO;
import com.springboot.repository.MemberRepository;

import ch.qos.logback.core.model.Model;
import lombok.AllArgsConstructor;

@RequestMapping("/members")
@AllArgsConstructor //생성자 주입(new와 동일)
@Controller
public class MemberController {

	private MemberRepository repository;
	
	//회원 가입 화면요청
	@GetMapping("/add")
	public String addForm() {
		return "member/addForm"; //addForm.
	}
	
	//회원 등록 처리
	@PostMapping("/add")
	public String add(@ModelAttribute MemberDTO member) {
		System.out.println("member: " + member);
		repository.save(member);
		return "redirect:/members";
	}
	
	//회원 목록
	@GetMapping
	public String list(Model model) {
		return "member/members";
	}
	
}
