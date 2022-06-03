package com.ict.mapper;

import java.util.List;

import com.ict.persistence.BoardVO;

public interface BoardMapper {
	
	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을
	// 어노테이션을 이용해 작성할 때
	//@Select("SELECT * FROM board_tbl WHERE bno < 4")
	public List<BoardVO> getList();

	// insert구문 실행용으로 메서드 선언
	// vo내부에 적혀있는 정보를 이용해 insert를 합니다.
	public void insert(BoardVO vo);
	
	public void delete(long bno);
	
	public void update(BoardVO vo);
}
