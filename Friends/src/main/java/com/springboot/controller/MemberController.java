package com.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.dto.MemberDTO;
import com.springboot.entity.Member;
import com.springboot.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor //생성자 주입
@Slf4j
@RequestMapping("/members")
@Controller
public class MemberController {
	
	//서비스 인스턴스 생성
	private MemberService service;
	
	//회원 가입 페이지
	@GetMapping("/join")
	public String joinForm() {
		return "member/join";
	}
	
	//회원 가입 처리
	@PostMapping("/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		log.info("member: " + memberDTO);
		service.save(memberDTO); //서비스에 있는 save() 호출
		return "redirect:/";
	}
	
	//회원 목록
	@GetMapping
	public String getMemberList(Model model) {
		List<Member> memberList = service.findAll();//서비스의 findAll() 호출
		//모델로 저장해서 보내기
		model.addAttribute("memberList", memberList);
		return "member/list"; //member/list.html
	}
	
	//회원 정보(1명 보기)
	@GetMapping("/{id}") //id -경로 변수
	public String getMember(@PathVariable Long id,
			Model model) {
		Member member = service.findById(id);
		model.addAttribute("member", member);
		return "member/info";
	}
	
	//로그인 페이지
	@GetMapping("/login")
	public String loginForm() {
		
		return "member/login";
	}
	
	//로그인 처리
	@PostMapping("/login")
	public String login(@RequestParam String email,
			@RequestParam String passwd,
			HttpSession session,
			RedirectAttributes ra) {
		try {
			//로그인 체크 메서드 호출
			MemberDTO dto = service.login(email, passwd);
			
			//로그인 성공시 - 세션 발급
			session.setAttribute("loginEmail", dto.getEmail());
			return "redirect:/";	
		}catch(Exception e) {
			//로그인 실패, RedirectAttributes는 redirect 상태에서 데이터 전송
			ra.addFlashAttribute("error", e.getMessage());
			return "redirect:/members/login";
		}
	}
	
	//로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//세션 삭제
		session.invalidate();
		return "redirect:/";
	}
	
}
