package com.ict.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.persistence.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Autowired
	private BoardService service;
	
	//@Test
	//public void getList() {
	//	Long pageNum = 1L;
	//	log.info(service.getList(pageNum));
	//}
	
	// insert 도 테스트 한 번 해주세요.
	//@Test
	public void insertTest() {
		BoardVO vo = new BoardVO();
		vo.setTitle("서비스 테스트 제목");
		vo.setContent("서비스 테스트 본문");
		vo.setWriter("서비스 테스트 글쓴이");
		service.insert(vo);
	}
	
	// delete를 테스트해보겠습니다.
	//@Test
	public void deleteTest() {
		service.delete(3L);
	}
	
	// update는 쿼리문 특성상 글번호까지 같이 줘야합니다.
	//@Test
	public void updateTest() {
		BoardVO vo = new BoardVO();
		vo.setBno(1L);
		vo.setTitle("수정된글제목");
		vo.setContent("수정된글본문");
		vo.setWriter("수정된글쓴이");
		service.update(vo);
	}
	
	//@Test
	public void detailTest() {
		service.getDetail(1L);
	}
	
}
