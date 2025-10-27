package com.springboot.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class MemberDTO {
	private long id;//번호
	private String email;//이메일
	private String passwd;//비밀번호
	private String name;//이름
	private String gender;//성별
	private Timestamp joinDate;//가입일
}
