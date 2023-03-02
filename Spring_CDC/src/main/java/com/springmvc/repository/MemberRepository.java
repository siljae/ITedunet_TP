package com.springmvc.repository;

import com.springmvc.domain.memberDTO;

public interface MemberRepository {
	//회원가입기능
	public void join(memberDTO dto);
	//로그인기능
	public String[] login(String email, String pw);
	public String chklogin(String[] result);
}
