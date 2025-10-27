package com.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.dto.MemberDTO;
import com.springboot.entity.Member;
import com.springboot.repository.MemberRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor //매개변수를 모두 가진 생성자
@Service //스프링에 빈(bean) 등록
public class MemberService {

	//객체(인스턴스) 생성 방식 - 생성자 주입 방식
	private MemberRepository repository;
	
	//회원 가입(추가)
	public void save(MemberDTO dto) {
		
		//dto를 entity로 변환하는 메서드를 호출
		Member member = Member.toSaveEntity(dto);
		repository.save(member);
	}
	
	//회원 목록
	public List<Member> findAll(){
		return repository.findAll();
	}
	
	//회원 정보
	public Member findById(Long id) {
		Member member = repository.findById(id).get();
		return member;
	}
	
	//로그인 처리
	public MemberDTO login(String email, String passwd) {
		//DB의 이메일과 입력 폼의 이메일을 비교
		Member member = repository.findByEmail(email)
				.orElseThrow(() -> 
				new IllegalArgumentException("존재하지 않는 이메일 입니다."));
		
		//비밀번호 체크
		if(!member.getPasswd().equals(passwd)) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}
		
		//entity를 dto로 저장
		MemberDTO dto = new MemberDTO();
		dto.setId(member.getId());
		dto.setEmail(member.getEmail());
		dto.setName(member.getName());
		dto.setGender(member.getGender());
		
		return dto;
	}
	
}
