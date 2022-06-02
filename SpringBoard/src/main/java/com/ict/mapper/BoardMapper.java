package com.ict.mapper;

import java.util.List;

import com.ict.persistence.BoardVO;

public interface BoardMapper {
	
	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을
	// 어노테이션을 이용해 작성할 때
	//@Select("SELECT * FROM board_tbl WHERE bno < 4")
	public List<BoardVO> getList();

}
