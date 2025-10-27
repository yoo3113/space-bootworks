package com.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor	//매개변수를 모두 가진 생성자
@NoArgsConstructor	//기본 생성자
@ToString
@Setter
@Getter
public class MemberDTO {
	private Long id;		//변수(순번)
	private String name;	//이름
	private String email;	//이메일
}
