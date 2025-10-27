package com.springboot.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.springboot.dto.MemberDTO;

@Repository		//스프링 빈으로 등록
public class MemberRepository {
	
	private List<MemberDTO> list = new ArrayList<>();
	private long sequence = 0L;
	//회원 가입(등록)
	public MemberDTO save(@ModelAttribute MemberDTO member) {
		member.setId(++sequence); //자동 순번(1증가)
		list.add(member);
		return member;
	}
}
