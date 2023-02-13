package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import mvc.database.DBConnection;

public class memberDAO {
	private static memberDAO instance = new memberDAO();
	private memberDAO() {}
	public static memberDAO getinstance() {
		return instance;
	}
	
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
			System.out.println("insertmember() 예외발생 : "+e);
		}
		finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (Exception e2) {
				System.out.println("insermember() close 예외발생 : "+e2);
			}
		}
	}
}
