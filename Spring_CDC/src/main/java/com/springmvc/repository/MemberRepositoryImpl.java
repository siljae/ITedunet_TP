package com.springmvc.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.springmvc.domain.memberDTO;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

	private JdbcTemplate template;
	
	@Autowired
	public void setJdbcTemplate(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	
	@Override //회원가입 기능
	public void join(memberDTO member) {//회원가입 기능
		String phone = member.getPhone1()+"-"+member.getPhone2()+"-"+member.getPhone3();
		String sql = "insert into member(m_email, m_name, m_pw, m_phone, m_post, m_addr1, m_addr2) "+" values(?,?,?,?,?,?,?)";
		template.update(sql, member.getEmail(), member.getName(), member.getPw(), phone, member.getPost(), member.getAddr1(), member.getAddr2());
	}

	@Override //로그인 기능
	public String[] login(String email, String pw) {
		System.out.println("레파지토리의 기능");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		String[] result = new String[3];
		
		try {
			conn = com.springmvc.database.DBConnection.getConnection();
			sql = "select m_pw, m_name, m_level from member where m_email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(pw)) {
					result[0] = "1";
					result[1] = rs.getString("m_name");
					result[2] = rs.getString("m_level");
					
					return result; // 로그인성공
				}
				else
					System.out.println("비밀번호 불일치");
					result[0] = "0";
					return result; //비밀번호 불일치
			}
			result[0] = "-1";
			System.out.println("디비접속실패");
			return result; //DB접속 실패
		}
		catch (Exception e) {
			System.out.println("로그인프로세스 오류 : "+e);
		}
		finally {
			try {
					if(rs != null)                   
						rs.close();
					if(pstmt != null)
						pstmt.close();
					if(conn != null)
						conn.close();
			} catch (Exception e2) {
				System.out.println("로그인 프로세스 close() 예외발생 : "+e2);
			}
		}
		
		System.out.println("로그인 프로세스 오류 ");
		return result; //로그인 프로세스 오류		
	}

	@Override
	public void chklogin(String[] result, HttpSession session) {
		System.out.println("로그인 세션에 담기기능");
		String msg = result[0];
		String name = result[1];
		String level = result[2];
		session.setAttribute("msg", msg);
		session.setAttribute("name",name);
		session.setAttribute("level", level);
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate();		
	}

	

	
	
	
	
}
