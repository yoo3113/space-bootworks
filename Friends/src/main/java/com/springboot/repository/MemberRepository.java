package com.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	//지원 메서드-save(), findAll(), findById(), deleteById()
	
	//이메일로 회원을 검색하는 메서드
	public Optional<Member> findByEmail(String email);
}
