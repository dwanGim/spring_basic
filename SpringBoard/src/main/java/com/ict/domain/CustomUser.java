package com.ict.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

// DB에서 보안적인 검사없이 바로 데이터를 가져올 때는 VO를 그대로 활용할 수 있었으나
// 시큐리티에서는 인가된 자료만 취급할 수 있습니다.
// 아래와 같이 User를 상속한 클래스를 만들고
// 멤버변수로 VO를 선언해준 다음
// 생성자에서 username, password, auth 최소 3항목을 입력해줘야
// 추후 VO대용으로 User의 자식클래스를 활용할 수 있습니다.

// private가 걸린 member를 외부에서 꺼내쓰기 위해 @Getter를 걸어줍니다.
@Getter
public class CustomUser extends User {


	private MemberVO member;
	
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> auth) {
		super(username, password, auth);
	}
	
	// CustomUser가 상속한 User클래스를 기준으로 생성자를 설정해주면
	// VO와 시큐리티를 연동할 수 있습니다.
	public CustomUser(MemberVO vo) {
		super(vo.getUserId(),

				vo.getUserPw(),
				
				vo.getAuthList().stream().map(author -> 
				
				new SimpleGrantedAuthority(author.getAuth()))
				.collect(Collectors.toList()));
		
		this.member = vo;
	
	}
	

}
