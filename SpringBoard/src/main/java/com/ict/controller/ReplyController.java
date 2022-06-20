package com.ict.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ict.persistence.ReplyVO;
import com.ict.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	// consumes는 이 메서드의 파라미터를 넘겨줄 때 어떤 형식으로 넘길 지 설정합니다.
	// 기본적으로 rest 방식에서는 json을 사용합니다.
	// produces는 입력받은 데이터를 토대로 로직을 실행한 다음
	// 사용자에게 결과로 보여줄 데이터의 형식을 나타냅니다.
	// jsom-datebind를 추가해야 정상적으로 작동합니다.
	@PostMapping(value="", consumes = "application/json", produces= {MediaType.TEXT_PLAIN_VALUE})
	// produces에 TEXT_PLAIN_VALUE를 줬으므로, 결과 코드와 문자열을 넘기도록 합니다.
	public ResponseEntity<String> register(@RequestBody ReplyVO vo) {
		
		// rest 컨트롤러에서 받는 파라미터 앞에 @RequestBody 어노테이션이 붙어야 consumes와 연결됩니다.
		// 깡통 Entity를 먼저 생성
		ResponseEntity<String> entity = null;
		
		try {
			// 먼저 글쓰기 로직 실행 후 에러가 없다면
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			// error 가 생겼을 때
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	@GetMapping(value="/all/{bno}",
	// 단일 숫자데이터 bno만 넣기도 하고
	// PathVariable 어노테이션으로 이미 입력데이터가
	// 명시되었으므로 consumes는 따로 주지 않아도 됩니다.
	// produces는 댓글 목록이 XML로도, JSON으로도 표현될 수 있도록
	// 아래와 같이 2개를 모두 작성합니다.
	// jackson-dataformal-xml을 추가해야 xml에서도 정상 작동합니다.
			produces = {MediaType.APPLICATION_ATOM_XML_VALUE ,
						MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<ReplyVO>> list (@PathVariable("bno") Long bno) {
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			
			entity = new ResponseEntity<>(service.listReply(bno), HttpStatus.OK);
			
			} catch (Exception e) {
			
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
					
		}
		return entity;
	}
	
	@DeleteMapping(value="/{rno}", 
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
		
		ResponseEntity<String> entity = null;
		
		try {
			
			service.removeReply(rno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch(Exception e) {
			
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;

	} //REMOVE END
	
	@RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH},
					value = "/{rno}",
					consumes = "application/json",
					produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modify (
									@PathVariable("rno") Long rno, 
									@RequestBody ReplyVO vo) {
		// VO는 우선 payload에 적힌 데이터를 받아옵니다.
		// @RequestBody가 붙은 VO는
		// payload에 적힌 데이터를 VO로 환산해서 가져옵니다.
		// 댓글번호rno는 주소에 기입된 숫자를 자원으로 받아옵니다.
		
		
		
		ResponseEntity<String> entity = null;
		
		
		try {
			
			// payload에는 reply만 넣어도 되는데 그 이유는
			// rno는 요청주소로 받아오기 때문입니다.
			// 단, rno를 주소로 받아오는 경우는 아직
			// replyVO에 rno가 세팅이 되지 않은 상태이므로
			// setter로 rno를 지정해줍니다.
			
			vo.setRno(rno);
			service.modifyReply(vo);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		} catch(Exception e) {
			
			e.printStackTrace();
			entity =  new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	} // modify END
	
	
	
	
	
	
}
