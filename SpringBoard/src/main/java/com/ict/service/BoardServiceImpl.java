package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.mapper.BoardMapper;
import com.ict.persistence.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getList() {
		
		return mapper.getList();
	}

	@Override
	public void insert(BoardVO vo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(long bno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoardVO getBoardDetail(long bno) {
		
		return mapper.getBoardDetail(bno);
	}

	@Override
	public void update(BoardVO vo) {
		// TODO Auto-generated method stub
		
	}

}
