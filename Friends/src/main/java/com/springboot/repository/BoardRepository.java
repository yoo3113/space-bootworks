package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.springboot.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	//@Query(value=sql구문) 애너테이션
	//@Modifying - 삽입, 수정 등의 변경이 있을때 필수 적용
	@Modifying
	@Query(value="update Board b set b.hits=b.hits+1 where b.id=:id")
	void updateHits(Long id);
}