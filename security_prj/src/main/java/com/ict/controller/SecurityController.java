package com.ict.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ict.domain.AuthVO;
import com.ict.domain.MemberVO;
import com.ict.service.SecurityService;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/secu/*")
@Controller
public class SecurityController {

	
	
	@Autowired
	private SecurityService service;
	
	@Autowired
	private PasswordEncoder pwen;
	
	@GetMapping("/all")
	public void doAll() {
		log.info("모든 사람이 접속 가능한 all 로직");
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
	@GetMapping("/member")
	public void doMember(Principal principal) {
		
		log.info("회원들이 접속 가능한 member 로직");
		log.info("접속한 회원명 : " + principal.getName());
		
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public void doAdmin(Principal principal) {// Principal 타입을 파라미터에 선언하면 컨트롤러에서 제어가능
		
		log.info("운영자만 접속 가능한 admin 로직");
		log.info("접속한 운영자명  :" + principal.getName());
		
	} // doAdmin END
	
	@PreAuthorize("permitAll")
	@GetMapping("/join")
	public void joinForm() {
		
		log.info("회원가입창 접속");
		
	} // joinForm END
	
	@PreAuthorize("permitAll")
	@PostMapping("/join")
	public void join(MemberVO vo, String[] role) {
		
		log.info("사용자가 가입 시 받는 데이터들 : " + vo);
		log.info("사용자가 선택한 권한 : " +Arrays.toString(role));
		
		
		
		String beforeCrPw = vo.getUserPw();
		log.info("암호화 전에 받은 pw" + beforeCrPw);
		vo.setUserPw(pwen.encode(beforeCrPw));
		log.info("암호화 후 : " + vo.getUserPw());
		
		// null 상태인 authList에 빈 ArrayList를 먼저 배정
		vo.setAuthList(new ArrayList<AuthVO>());
		
		// authList 는 List<authList>이므로 권한 개수에 맞게 넣어줘야 합니다.
		for(int i = 0; i < role.length; i++) {
			vo.getAuthList().add(new AuthVO());
			vo.getAuthList().get(i).setAuth(role[i]);
			vo.getAuthList().get(i).setUserId(vo.getUserId());
		}
		
		// 향상된 for 문
		// for(String roleItem : role) {
		//		AuthVO vo =	new AuthVO;
		//		vo.setAuth(roleItem);
		// 		vo.setUserId(vo.getUserId());
		// }
		
		log.info(vo.getAuthList());
		service.insertMemberTbl(vo);
		
	} // join (POST)END
}
