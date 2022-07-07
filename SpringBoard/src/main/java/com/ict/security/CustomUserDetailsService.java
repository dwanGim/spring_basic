package com.ict.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ict.domain.CustomUser;
import com.ict.domain.MemberVO;
import com.ict.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("유저 이름 확인 : " + username);
		
		MemberVO vo = mapper.read(username);
		
		log.warn("확인된 username으로부터의 정보 디버깅 : " + vo);
		
		return vo == null? null : new CustomUser(vo);
	}

	
}
