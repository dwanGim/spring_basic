package com.ict.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/secu/*")
@Controller
public class SecurityController {

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
		
	}
}
