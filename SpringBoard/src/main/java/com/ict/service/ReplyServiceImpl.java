package com.ict.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.mapper.BoardMapper;
import com.ict.mapper.ReplyMapper;
import com.ict.persistence.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyMapper mapper;
	
	@Autowired
	private BoardMapper boardMapper;

	@Override
	//@Transactional
	public void addReply(ReplyVO vo) {
		Long bno = vo.getBno();
		
		mapper.create(vo);
		
		boardMapper.updateReplyCount(bno, 1);
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
	@Transactional
	public void removeReply(Long rno) {

		// rno를 통해 bno 정의
		Long bno = mapper.getBno(rno);
		
		// rno값의 댓글 삭제
		mapper.delete(rno);
		
		// board_tbl의 bno가 일치하는 replycount를 -1
		boardMapper.updateReplyCount(bno, -1);
		
	}




}
