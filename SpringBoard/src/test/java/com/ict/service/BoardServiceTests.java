package com.ict.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.persistence.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( 
				"file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	private BoardService service;
	
	//@Test
	public void getList() {
		log.info(service.getList());
	}
	
	//insert 도 테스트 해보겠습니다.
	//@Test
	public void insertTest() {
		BoardVO vo = new BoardVO();
		vo.setTitle("서비스 테스트 제목");
		vo.setContent("서비스 테스트 본문");
		vo.setWriter("서비스 테스트 글쓴이");
		service.insert(vo);
	}
	
	
	//@Test
	public void deletTest() {
		service.delete(3);
		
		
	}
	
	// update는 쿼리문 특성상 글번호까지 같이 줘야합니다.
	//@Test
	public void updateTest() {
		BoardVO vo = new BoardVO();
		vo.setBno(1);
		vo.setTitle("수정된 글제목");
		vo.setContent("수정된글본문");
		vo.setWriter("수정된글쓴이");
		service.update(vo);
	}
	
	@Test
	public void getBoardDetail() {
		
		long bno = 1;
		
		service.getBoardDetail(bno);
		
	}
	
	
}
