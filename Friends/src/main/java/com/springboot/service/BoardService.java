package com.springboot.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.dto.BoardDTO;
import com.springboot.entity.Board;
import com.springboot.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor  //생성자 주입(final 사용)
@Service  //빈 등록
public class BoardService {

	//@RequiredArgsConstructor 경우에 final 상수 키워드 붙임
	private final BoardRepository repository;
	
	//글쓰기
	public void save(BoardDTO dto) {
		//dto를 entity로 변환 메서드 호출
		Board board = Board.toSaveEntity(dto);
		repository.save(board);
	}

	//글 목록
	public List<Board> findAll() {
		return repository.findAll();
	}

	//글 상세보기
	public Board findById(Long id) {
		//id에 해당하는 게시글이 없는 경우도 구현한다.
		Board board = repository.findById(id)
				.orElseThrow(() -> 
				new IllegalArgumentException("해당 글이 존재하지 않습니다."));
		return board;
	}

	//조회수 증가
	@Transactional //트랜잭션(조회수, 상세보기 2개의 기능을 구현)
	public void updateHits(Long id) {
		repository.updateHits(id);
	}

	//글 삭제
	public void delete(Long id) {
		//제공된 deleteById() 사용
		repository.deleteById(id);
	}

	//글 수정
	public void update(BoardDTO dto) {
		//id에 해당하는 게시글이 가져오기
		Board board = repository.findById(dto.getId())
				.orElseThrow(() -> 
				new IllegalArgumentException("해당 글이 존재하지 않습니다."));
		
		//제목, 내용 수정
		board.setTitle(dto.getTitle());
		board.setContent(dto.getContent());
		repository.save(board);  //수정후 다시 저장
	}

	//글 목록(페이지 처리)
	public Page<Board> findAll(Pageable pageable) {
		//0-> 첫페이지, 10->페이지당 개수
		//pageable = PageRequest.of(0, 10); //오름차순
		int page = pageable.getPageNumber() - 1;
		int pageSize = 10;
		
		log.info("pageable.getPageNumber(): " + pageable.getPageNumber());
		
		//http://localhost:8080/boards/pages?page=3 ->3페이지
		pageable = PageRequest.of(page, pageSize); //오름차순
		//pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"); //내림차순
		
		
		Page<Board> boardList = repository.findAll(pageable);
		return boardList;
	}
	
}




