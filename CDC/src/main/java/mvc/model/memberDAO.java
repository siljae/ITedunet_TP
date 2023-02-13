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
	
	//로그인 기능
	public int checklogin(String email, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		try {
			conn = DBConnection.getConnection();
			sql = "select m_pw from member where m_email=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(pw)) {
					return 1;
				}
				else
					return 0;
			}
			return -1;
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
		
		
		return -2;
		
	}
}
