package mvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import mvc.database.DBConnection;

public class boardDAO {
	private static boardDAO instance = new boardDAO();
	private boardDAO() {}
	public static boardDAO getinstance() {
		return instance;
	}
	
	//게시판에 작성된 전체 글이 몇개인지 알아오는 기능, 특정 글자를 입력해서 그 글자에 맞는 게시글 숫자 가져오기
	public int getListCount(String items, String text) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		int x=0;
		
		if(items == null && text == null)
			sql="select count(*) from commuboard";
		else
			sql="select count(*) from commuboard where"+items+"like ' %" +text+ "%'";
		
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
	
	//글 목록 내용 가져오기
	public ArrayList<boardDTO> getBoardList(int page, int limit, String items, String text){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//전체 게시글 숫자
		int total_record = getListCount(items, text);
		//현재 페이지
		int start = (page -1 )* limit;
		//다음 페이지
		int index = start +1;
		
		String sql;
		
		if(items == null && text == null)
			sql = "select*from board order by num desc";
		else
			sql = "select*from board where"+items+"like '%"+text+ "%' order by num desc";
		
		ArrayList<boardDTO> list = new ArrayList<boardDTO>();
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
		} catch (Exception e) {
			System.out.println("게시판 목록 불러오기 에러: "+e);
		}
		return null;
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
