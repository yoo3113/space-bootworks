package com.springboot.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	//save(), findById(), findAll(), delete()
	
	//쿼리 메서드 - 글 제목 그 자체로 검색
	List<Board> findByTitle(String searchKeyword);
	
	//특정 단어가 포함된 글 제목 검색
	List<Board> findByTitleContaining(String serchKeyword);
	
	//특정 단어가 포함된 글 제목을 내림차순으로 정렬
	List<Board> findByTitleContainingOrderByIdDesc(String seachKeyword);
	
	//제목 검색어가 포함된 글 목록을 페이지 처리하여 조회
	//List<Board> findByTitleContaining(String searchKeyword, Pageable paging);
	
	//제목 검색어가 포함된 글 목록을 페이지 처리하여 조회
	Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
}

