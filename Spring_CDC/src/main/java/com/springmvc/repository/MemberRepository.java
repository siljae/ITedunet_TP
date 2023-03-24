package com.springmvc.repository;


import com.springmvc.domain.memberDTO;

public interface MemberRepository {
	//회원가입기능
	public void join(memberDTO dto);
	//DB에서 전체회원가져와서 List에 담아놓음
	public void getmemberlist();
	//로그인기능
	public memberDTO chkmember(String email, String pw);
	//회원정보수정기능
	public void updatemember(memberDTO member);
	//세션에 저장된 닉네임과 일치하는 멤버객체반환하는 기능
	public memberDTO getmemberByname(String name);
}
