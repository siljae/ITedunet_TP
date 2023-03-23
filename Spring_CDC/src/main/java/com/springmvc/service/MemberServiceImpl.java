package com.springmvc.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
	
	@Override	//새로운 로그인 기능
	public void chklogin(String email, String pw,HttpSession session,ModelAndView mav) {
		memberDTO member = mr.chkmember(email, pw);
		if(member.getName() != null) {
			session.setAttribute("name", member.getName());
			session.setAttribute("level", member.getLevel());
			mav.addObject("msg",1);
			mav.setViewName("index");
		}
		else {
			mav.addObject("msg", -1);
			mav.setViewName("login");
		}		
	}

	@Override	//로그아웃 기능
	public void logout(HttpSession session) {
		session.invalidate();
	}

	@Override	//회원정보수정
	public void updatemember(memberDTO member) {
		memberDTO mb =mr.chkmember(member.getEmail(),member.getPw());
		mr.updatemember(mb);		
	}
		//마이페이지에서 해당 회원이 맞는지 한번 더 확인하는 작업
	public void chkuser(memberDTO member) {
		
	}
	
	
	
	
}
