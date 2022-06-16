package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.mapper.ReplyMapper;
import com.ict.persistence.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyMapper mapper;

	@Override
	public void addReply(ReplyVO vo) {
		// TODO Auto-generated method stub
		mapper.create(vo);
	}

	@Override
	public List<ReplyVO> listReply(Long bno) {
		// TODO Auto-generated method stub
		return mapper.getList(bno);
	}

	@Override
	public void modifyReply(ReplyVO vo) {
		// TODO Auto-generated method stub
		mapper.update(vo);
	}

	@Override
	public void removeReply(Long rno) {
		// TODO Auto-generated method stub
		mapper.delete(rno);
	}




}
