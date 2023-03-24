package com.springmvc.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springmvc.domain.memberDTO;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	private JdbcTemplate template;
	private List<memberDTO> listOfmember;
	
	@Autowired
	public void setJdbcTemplate(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	
	@Override 	//회원가입 기능
	public void join(memberDTO member) {
		String phone = member.getPhone1()+"-"+member.getPhone2()+"-"+member.getPhone3();
		String sql = "insert into member(m_email, m_name, m_pw, m_phone, m_post, m_addr1, m_addr2) "+" values(?,?,?,?,?,?,?)";
		template.update(sql, member.getEmail(), member.getName(), member.getPw(), phone, member.getPost(), member.getAddr1(), member.getAddr2());
	}	
	
	@Override	//전체 회원을 DB에서 다 가져옴
	public void getmemberlist() {		
		String sql = "select*from member";
		List<memberDTO> memberlist = template.query(sql, new MemberMapper());
		this.listOfmember = memberlist;
	}
	
	@Override	//회원정보 일치하는지 확인하는 기능
	public memberDTO chkmember(String email, String pw) {
		memberDTO memberinfo = new memberDTO();
		getmemberlist();
		for(memberDTO member : listOfmember) {
			if(member.getEmail().equals(email) && member.getPw().equals(pw)) {
				memberinfo = member;					
			}
		}
		return memberinfo;
	}
	
	@Override	//세션에 저장된 닉네임과 일치하는 멤버객체반환하는 기능
	public memberDTO getmemberByname(String name) {
		memberDTO memberinfo = new memberDTO();		
		getmemberlist();
		for(memberDTO member : listOfmember) {
			if(member.getName().equals(name)) {
				memberinfo = member;					
			}
		}
		return memberinfo; 
	}

	@Override	//회원정보 수정
	public void updatemember(memberDTO member) {
		String phone = member.getPhone1()+"-"+member.getPhone2()+"-"+member.getPhone3();
		String sql = "update member set m_name=?, m_pw=?, m_phone=?, m_post=?, m_addr1=?, m_addr2=? where m_email=?";
		template.update(sql, member.getName(), member.getPw(), phone, member.getPost(), member.getAddr1(), member.getAddr2(), member.getEmail());		
	}

	

	

	
	
	
	
}
