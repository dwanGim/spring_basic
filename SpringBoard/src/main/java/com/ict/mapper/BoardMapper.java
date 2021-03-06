package com.ict.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ict.persistence.BoardVO;
import com.ict.persistence.SearchCriteria;

public interface BoardMapper {
	
	// board_tbl에서 글번호 3번 이하만 조회하는 쿼리문을
	// 어노테이션을 이용해 작성해주세요.
	// @Select("SELECT * FROM board_tbl WHERE bno < 4")]
	public List<BoardVO> getList(SearchCriteria cri);
	
	// insert구문 실행용으로 메서드를 선언합니다.
	// VO내부에 적혀있는 정보를 이용해 insert를 합니다.
	// BoardVO를 매개로 insert 정보를 전달받음.
	public void insert(BoardVO vo);
	
	// delete를 만들어주세요.
	// 글번호(Long타입) 을 입력받아 해당 글번호를 삭제해줍니다.
	public void delete(Long bno);
	
	// update
	public void update(BoardVO vo);
	
	// detail
	public BoardVO getDetail(Long bno);
	
	public Long getBoardCount(SearchCriteria cri);
	
	public void updateReplyCount(@Param("bno") Long bno,
									@Param("amount") int amount);
	
}
