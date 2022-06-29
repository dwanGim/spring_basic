package com.ict.mapper;

import java.util.List;

import com.ict.persistence.ReplyVO;

public interface ReplyMapper {

	public List<ReplyVO> getList(Long bno);
	
	public void create(ReplyVO vo);
	
	public void update(ReplyVO vo);
	
	public void delete(Long rno);
	
	// 댓글번호로 글번호를 유추하는 로직
	public Long getBno(Long rno);
}
