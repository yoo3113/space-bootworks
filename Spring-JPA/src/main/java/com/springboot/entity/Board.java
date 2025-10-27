package com.springboot.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor //기본 생성자
//@Builder
@ToString
@Setter
@Getter
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //번호
	
	@Column(nullable=false)//필수 입력(not null)
	private String title;//제목
	
	@Column(length=20, nullable=false)
	private String writer;//글쓴이
	
	@Column(length=4000, nullable=false)
	private String content;//내용
	
	@CreationTimestamp //자동 저장
	private Timestamp createDate;//작성일
}




