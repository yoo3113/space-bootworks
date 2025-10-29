package com.springboot.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.springboot.dto.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data  //getter, setter, 생성자 등 모두 적용됨
@Table(name = "t_board")
@Entity
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;      //글 번호
	
	@Column(nullable=false)
	private String title; //글 제목
	
	@Column(length=4000, nullable=false)
	private String content; //글 내용
	
	@Column(columnDefinition = "Integer default 0")
	private Integer hits;  //조회수
	
	@Column(length=30, nullable=false)
	private String writer;  //글쓴이
	
	@CreationTimestamp  //자동 생성
	private Timestamp regDate; //작성일
	
	//dto를 entity로 변환 메서드
	public static Board toSaveEntity(BoardDTO dto) {
		Board board = new Board();
		board.setTitle(dto.getTitle());
		board.setContent(dto.getContent());
		board.setHits(0);
		board.setWriter(dto.getWriter());
		
		return board;
	}
	
}





