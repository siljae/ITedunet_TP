package com.springmvc.service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.memberDTO;

public interface MemberService {
	//회원가입기능
	public void join(memberDTO dto);
	//로그인기능	
	public void chklogin(String email, String pw,HttpSession session,ModelAndView mav);
	public void logout(HttpSession session);
	public void updatemember(memberDTO member);
	
}
