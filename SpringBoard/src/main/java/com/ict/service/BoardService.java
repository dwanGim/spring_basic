package com.ict.service;

import java.util.List;

import com.ict.persistence.BoardVO;

public interface BoardService {

	public List<BoardVO> getList();
	
	public void delete(long bno);
	
	public void insert(BoardVO vo);
	
	public void update(BoardVO vo);
	
	public BoardVO getBoardDetail(long bno);
}
