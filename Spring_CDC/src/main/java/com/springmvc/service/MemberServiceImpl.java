package com.springmvc.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.memberDTO;
import com.springmvc.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository mr;
	
	@Override //회원가입 기능
	public void join(memberDTO member) {
		mr.join(member);
	}

	@Override //로그인 기능
	public String[] login(String email, String pw) {
		String[] result = mr.login(email, pw);
		return result;
	}
	
	
	
}
