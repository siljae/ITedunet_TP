package com.springmvc.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
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
	
	@Override	//로그인 기능
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
		mr.updatemember(member);		
	}
	@Override	//마이페이지에서 해당 회원이 맞는지 한번 더 확인하는 작업
	public String chkuser(memberDTO member,Model model, String chkpw) {		
		if(!member.getPw().equals(chkpw)) {
			model.addAttribute("msg",-1);
			return "mypagebarrier";
		}
		return "mypage";
	}
	
	@Override	//세션에 저장된 닉네임과 일치하는 멤버객체반환하는 기능
	public void getmemberByname(memberDTO member, Model model) {
		String name = member.getName();
		member = mr.getmemberByname(name);
		model.addAttribute("member",member);
	}
	
	
	
	
}
