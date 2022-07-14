package com.ict.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.domain.MemberVO;
import com.ict.mapper.MemberMapper;

@Service
public class SecurityServiceImpl implements SecurityService{

	
	@Autowired
	private MemberMapper mapper;
	
	@Override
	public void insertMemberTbl(MemberVO vo) {
		
		mapper.insertMemberTbl(vo);
		mapper.insertMemberAuth(vo);
		
		
	}

	@Override
	public MemberVO read(String userId) {
		// TODO Auto-generated method stub
		return mapper.read(userId);
	}

	@Override
	public void insertAuthTbl(MemberVO vo) {
		// TODO Auto-generated method stub
		
	}

}
