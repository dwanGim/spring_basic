package com.ict.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ict.persistence.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		"file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	// 이 테스트 코드 내에서는 BoardMapper의 테스트를 담당합니다.
	// 그래서 먼저 선언하고 의존성 주입까지 마쳐야 해당 기능을 이 클래스 내에서 쓸 수 있습니다.
	@Autowired
	private BoardMapper mapper;
	
	
	//@Test
	public void testGetList() {
		log.info("getList 실행");
		log.info(mapper.getList());
	}
	
	//@Test
	public void insertTest() {
		// 글 입력을 위해서 BoardVO 타입을 매개로 사용함
		// 따라서 BoardVO를 만들어 놓고
		// setter로 글제목, 글본문, 글쓴이만 저장해둔 채로
		// mapper.insert(vo);를 호출해서 실행여부를 확인하면 됨.
				
		BoardVO vo = new BoardVO();
		
		vo.setTitle("새로운제목");
		vo.setContent("새로운본문");
		vo.setWriter("새로운글쓴이");
		
		mapper.insert(vo);
	}
	
	//@Test
	public void deleteTest() {
		
		long bno = 21;
		
		mapper.delete(bno);
	}
	
	//@Test
	public void updateTest() {
		
		BoardVO vo = new BoardVO();
		
		vo.setBno(3);
		vo.setTitle("update Title");
		vo.setContent("update content");
		vo.setWriter("update writer");
		
		mapper.update(vo);
	}
	
	@Test
	public void getBoardDetail() {
		
		mapper.getBoardDetail(1);
		
		
	}
	
	
}
