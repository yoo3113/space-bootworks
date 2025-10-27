package com.springboot;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.springboot.entity.Board;
import com.springboot.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class QueryMethodTest {

	@Autowired //repository 객체를 생성해줌
	private BoardRepository repository;
	
	/*@BeforeEach
	public void dataPrepare() {
		for(int i=1; i<=200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목" + i);
			board.setWriter("테스트");
			board.setContent("테스트 내용" + i);
			
			repository.save(board);
		}
	}*/
	
	/*@Test
	public void testFindByTitle() {
		List<Board> boardList
				= repository.findByTitle("테스트 제목 4");
				
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("==>" + board.toString());
		}
	}*/
	
	/*@Test
	public void testFindByTitleContaining() {
		List<Board> boardList =
				repository.findByTitleContaining("10");
		
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("==>" + board.toString());
		}
	}*/
	
	/*@Test
	public void testFindByTitleConainingOrderByIdDesc() {
		List<Board> boardList =
				repository.findByTitleContainingOrderByIdDesc("10");
		
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("==>" + board.toString());
		}
	}*/
	
	/*@Test
	public void testFindByTitleConainingPage() {
		//0-> 첫 페이지 번호(pageNumber),
		//10 -> 페이지당 데이터수(pageSize)
		//Pageable paging = PageRequest.of(0,10); //오름차순
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "id");//내림차순 
		
		List<Board> boardList =
		repository.findByTitleContaining("제목", paging);
		
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("==>" + board.toString());
		}
	}*/
	
	@Test
	public void testFindByTitleConainingPage() {
		//0-> 첫 페이지 번호(pageNumber),
		//10 -> 페이지당 데이터수(pageSize)
		//Pageable paging = PageRequest.of(0,10); //오름차순
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "id");//내림차순 
		
		//페이지 정보 객체 생성
		Page<Board> pageInfo = 
				repository.findByTitleContaining("제목", paging);
		
		log.info("PAZE SIZE: " + pageInfo.getSize()); //10(페이지당 데이터수)
		log.info("TOTAL PAGES: " + pageInfo.getTotalPages()); //20(전체 페이지수)
		log.info("TOTAL COUNT: " + pageInfo.getTotalElements()); //200(전체데이터수)
		log.info("CONTENTS: " + pageInfo.getContent()); //저장된 데이터
		
		List<Board> boardList = pageInfo.getContent();
		
		log.info("검색 결과");
//		for(Board board : boardList) {
//			log.info("==>" + board.toString());
//		}
		
		//람다식으로 출력
		boardList.forEach(board -> log.info("--->" + board));
	}
}


