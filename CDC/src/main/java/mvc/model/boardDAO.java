package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mvc.database.DBConnection;

public class boardDAO {
	private static boardDAO instance = new boardDAO();
	private boardDAO() {}
	public static boardDAO getinstance() {
		return instance;
	}
	
	//게시판 목록 불러오기
	public int getListCount(String items, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		int x=0;
		
		if(items == null && text == null)
			sql="select count(*) from board";
		else
			sql="select count(*) from board where"+items+"like ' %" +text+ "%'";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시판 목록 불러오기 에러: "+e);
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
				System.out.println("게시판 목록 불러오기 close() 에러: "+e2);
			}
		}
		
		return x;
	}
	
	//글쓰기 기능
	public void writeacion(boardDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			conn = DBConnection.getConnection();
			sql = "insert into commuboard(m_name, cb_title, cb_content, cb_regist_day,cb_filename,cb_hit) values (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3,dto.getContent());
			pstmt.setString(4,dto.getRegist_day());
			pstmt.setString(5, dto.getFilename());
			pstmt.setInt(6,dto.getHit());
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("글쓰기 프로세스 오류: "+e);
		}
		finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
				
			} catch (Exception e2) {
				System.out.println("글쓰기 프로세스 close() 예외 발생: "+e2);
			}
		}
		
	}
}
