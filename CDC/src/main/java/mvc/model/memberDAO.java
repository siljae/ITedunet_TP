package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

import mvc.database.DBConnection;

public class memberDAO {
	private static memberDAO instance = new memberDAO();
	private memberDAO() {}
	public static memberDAO getinstance() {
		return instance;
	}
	
	//로그인 기능
	public String[] checklogin(String email, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		String[] result = new String[3];
		
		try {
			conn = DBConnection.getConnection();
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
					result[0] = "0";
					return result; //비밀번호 불일치
			}
			result[0] = "-1";
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
		
		
		return result; //로그인 프로세스 오류
		
	}

	//회원 가입 기능
	public void insertmember(memberDTO dto) {
		System.out.println("memberDAO:회원추가하러옴");
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			conn = DBConnection.getConnection();
			sql = "insert into member(m_email, m_name, m_pw, m_phone, m_post, m_addr1, m_addr2) values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPw());
			pstmt.setString(4, dto.getPhone1()+"-"+dto.getPhone2()+"-"+dto.getPhone3());
			pstmt.setString(5, dto.getPost());
			pstmt.setString(6, dto.getAddr1());
			pstmt.setString(7, dto.getAddr2());
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("회원가입 예외발생 : "+e);
		}
		finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				System.out.println("회원가입 close 예외발생 : "+e2);
			}
		}
	}
	
	//회원가입시 이메일 중복 체크
	public boolean checkemail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			conn = DBConnection.getConnection();
			sql = "select m_email from member where m_email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(email)) {
					return true;
				}
			}
			else
				return false;
		}
		catch (Exception e) {
			System.out.println("이메일 중복체크 오류: "+e);
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
				System.out.println("이메일 중복체크 close() 오류: "+e2);
			}
		}
		return false;
	}
	//회원가입시 닉네임 중복 체크
	public boolean checkname(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		try {
			conn = DBConnection.getConnection();
			sql = "select m_name from member where m_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(name)) {
					return true;
				}
			}
			else
				return false;
		}
		catch (Exception e) {
			System.out.println("닉네임 중복체크 오류: "+e);
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
				System.out.println("닉네임 중복체크 close() 오류: "+e2);
			}
		}
		return false;
	}
	

}
