package com.springmvc.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.springmvc.domain.memberDTO;

public interface MemberService {
	//회원가입기능
	public void join(memberDTO dto);
	//로그인기능
	public String[] login(String email, String pw);
	public void chklogin(String[] result,HttpSession session);
	public void logout(HttpSession session);
}
