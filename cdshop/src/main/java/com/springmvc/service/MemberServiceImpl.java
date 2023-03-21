package com.springmvc.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
		System.out.println("서비스의 기능");
		String[] result = mr.login(email, pw);
		return result;
	}

	@Override
	public void chklogin(String[] result, HttpSession session) {
		mr.chklogin(result, session);		
	}

	@Override
	public void logout(HttpSession session) {
		mr.logout(session);		
	}
	
	
	
}