package com.springmvc.repository;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
import com.springmvc.database.DBConnection;
import com.springmvc.domain.boardDTO;
import com.springmvc.domain.criteria;
import com.springmvc.domain.pageDTO;

@Repository
public class BoardRepositoryImpl implements BoardRepositoty {
	private JdbcTemplate template;
	private static final int LISTCOUNT =10;
	private List<boardDTO> listOfboards = new ArrayList<boardDTO>();
	
	@Autowired
	public void setJdbcTemplate(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	
	@Override //글쓰기 기능
	public void writeboard(boardDTO board,HttpServletRequest req) {
		//board_type에 따른 한글로 변환해주기
		if(board.getBoard_type().equals("commu")) {
			board.setBoard_type("자랑해요");
		}
		else if(board.getBoard_type().equals("qna")) {
			board.setBoard_type("Q&A");
		}
		else if(board.getBoard_type().equals("recom")) {
			board.setBoard_type("추천해요");
		}
		else if(board.getBoard_type().equals("event")) {
			board.setBoard_type("이벤트");
		}
		else if(board.getBoard_type().equals("hosreview")) {
			board.setBoard_type("후기에요");
		}
			
		
		//게시글 작성시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String regist_day = sdf.format(new Date());
		
		//이미지 파일업로드
		MultipartFile boardimg = board.getFileimage();
		
		//이미지 이름 가져오기
		String saveName = boardimg.getOriginalFilename();
		//이미지 이름 board객체에 저장
		board.setFilename(saveName);
		//이미지 경로 변수에 담기
		String path = req.getServletContext().getRealPath("/resources/img/board/");
		//파일 업로드
		File saveFile = new File(path,saveName);		
		if (boardimg != null && !boardimg.isEmpty()) {
            try {
            	boardimg.transferTo(saveFile);  
            } catch (Exception e) {
                throw new RuntimeException("게시판 이미지 업로드가 실패하였습니다", e);
            }
        }
		
		String sql = "insert into commuboard(cb_board_type,cb_animal_type,m_name, cb_title, cb_content, cb_regist_day, cb_filename, cb_hit, cb_recom) values(?,?,?,?,?,?,?,?,?)";
		template.update(sql, board.getBoard_type(), board.getAnimal_type(), board.getName(), board.getTitle(), board.getContent(), regist_day, board.getFilename(), board.getHit(), board.getRecom());
	}

	@Override //전체 게시글 불러오기
	public void boardlist(Model model,HttpServletRequest req) {
		List<boardDTO> boardlist = new ArrayList<boardDTO>();
		
		int pageNum =1;			//현재 페이지
		int limit = LISTCOUNT;	//한 페이지에 보여줄 게시글 숫자 (현재 : 10)
		
		if(req.getAttribute("pageNum") != null)
			pageNum = Integer.parseInt((String)req.getAttribute("pageNum"));
		
		String animal = (String)req.getParameter("animal_type");
		String content = (String)req.getParameter("content");	
		String sort = (String)req.getAttribute("sort");
		
		int total_record = getlistcount(animal,content);
		boardlist = getboardlist(pageNum, limit, animal, content, sort);
			
		
		for (boardDTO board : boardlist) {
			String regist_day = caltime(board.getRegist_day());
			
			if(board.getAnimal_type() != null && board.getAnimal_type().equals("cat")) {
				board.setCalregist(regist_day);
				board.setTag_src("catface.png");
				board.setTag_value("고양이");
			}
			else if(board.getAnimal_type() != null && board.getAnimal_type().equals("dog")) {
				board.setCalregist(regist_day);
				board.setTag_src("dogface.png");
				board.setTag_value("강아지");
			}
		}
		
		int total_page;
		
		if(total_record % limit ==0) {
			total_page = total_record/limit;
			Math.floor(total_page);
		}
		else {
			total_page = total_record/limit;
			Math.floor(total_page);
			total_page = total_page+1;
		};
		
		this.listOfboards = boardlist;
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("total_page", total_page);
		model.addAttribute("total_record", total_record);
		model.addAttribute("boardlist", boardlist);		
	}

	@Override //게시글 숫자 가져오기
	public int getlistcount(String animal, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		
		int x=0;
		
		if(animal == null && content == null) {
			sql="select count(*) from commuboard";
		}
			
		else if(animal != null){
			sql="select count(*) from commuboard where cb_animal_type='"+animal+"'";
		}
		else {
			sql="select count(*) from commuboard where cb_title like '%"+content+"%' or cb_content like '%"+content+"%'";
		}
			
			
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				x = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시판 목록 세기 에러: "+e);
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
				System.out.println("게시판 목록 세기 close() 에러: "+e2);
			}
		}
		
		return x;
	}

	@Override //게시글 목록 가져오기
	public ArrayList<boardDTO> getboardlist(int pageNum, int limit, String animal, String content, String sort) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		//전체 게시글 숫자
		int total_record = getlistcount(animal, content);
		//현재 페이지
		int start = (pageNum -1 )* limit;
		//다음 페이지
		int index = start +1;
		
		String sql = null;		
		if(animal == null && content == null && sort == null) {	//전체 게시판
			sql = "select*from commuboard order by cb_num desc";		
		}
		
		else if(animal != null) {	//태그 별 정렬(고양이, 강아지)
			String cb_animal_type = animal;
			sql = "select*from commuboard where"+cb_animal_type+"order by cb_num desc";
		}
		else if(sort != null) {	//최신순, 인기순, 조회순으로 정렬
			if(sort.equals("viewed")) {
				sql = "select*from commuboard order by cb_hit desc";			
			}
			else if(sort.equals("popular")) {
				sql = "select*from commuboard order by cb_recom desc";
			}
			else if(sort.equals("newest")) {
				sql = "select*from commuboard order by cb_num desc";
			}
		}
		else {	//게시글 검색 기능
			//이거 확인하고 적용시키셈 > 동작하는거 확인했음 
			sql = "select*from commuboard where cb_title like '%"+content+"%' or cb_content like '%"+content+"%' order by cb_num desc";			
		}
		
		ArrayList<boardDTO> list = new ArrayList<boardDTO>();
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();		
			while(rs.absolute(index)) {
				boardDTO board = new boardDTO();
				board.setNum(rs.getInt("cb_num"));
				board.setName(rs.getString("m_name"));
				board.setBoard_type(rs.getString("cb_board_type"));
				board.setAnimal_type(rs.getString("cb_animal_type"));
				board.setTitle(rs.getString("cb_title"));
				board.setContent(rs.getString("cb_content"));
				board.setRegist_day(rs.getString("cb_regist_day"));
				board.setFilename(rs.getString("cb_filename"));
				board.setHit(rs.getInt("cb_hit"));
				
				list.add(board);
				
				if(index < (start+limit) && index <= total_record)
					index++;
				else
					break;
			}				
			return list;
			
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
		return null;
	}
	
	@Override	//게시글 접속시간-작성시간 비교하는 기능
	public String caltime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String x="";
		try {
			Date date = sdf.parse(time);
			long curTime = System.currentTimeMillis();
			long regTime = date.getTime();
			long calTime = (curTime-regTime)/1000;
			if(calTime<60) {
				x="방금전";			
			}
			else if(calTime<3600) {
				x=(calTime/60)+"분전";
				return x;
			}
			else if(calTime<86400) {
				x=(calTime/3600)+"시간전";
				return x;
			}
			else if(calTime<(86400*30)) {
				x=(calTime/86400)+"일전";
				return x;
			}
			else {				
				x=sdf.format(date);
				return x;
			}		
		} catch (Exception e) {
			System.out.println("접속시간 에러: "+e);
		}
		return x;
		
	}
	//선택된 게시글 상세 페이지 가져오기
	public void requestboardview(Model model) {
		String numStr = (String) model.getAttribute("num");
		int num = Integer.parseInt(numStr);
		String pageNumStr = (String) model.getAttribute("pageNum");
		int pageNum = Integer.parseInt(pageNumStr);
		boardDTO board = new boardDTO();
		board = getboardbynum(num, pageNum);
		String tag_src;
		String tag_value;
		if(board.getAnimal_type() != null && board.getAnimal_type().equals("cat")) {
			tag_src = "catface.png";
			tag_value = "고양이";
			model.addAttribute("tag_src", tag_src);				
			model.addAttribute("tag_value", tag_value);
		}
		else if(board.getAnimal_type() != null && board.getAnimal_type().equals("dog")) {
			tag_src = "dogface.png";
			tag_value = "강아지";
			model.addAttribute("tag_src", tag_src);
			model.addAttribute("tag_value", tag_value);
		}
		
		model.addAttribute("num", num);
		model.addAttribute("page", pageNum);
		model.addAttribute("board", board);
	}
	
	//조회수 증가 기능
	public void updatehit(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "select cb_hit from commuboard where cb_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			int hit = 0;
			
			if(rs.next())
				hit = rs.getInt("cb_hit")+1;
			
			
			sql = "update commuboard set cb_hit=? where cb_num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hit);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("조회수 증가 에러: "+e);
		}
		finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}	
		}		
	}
		
	//선택된 글 상세 내용 가져오기
	public boardDTO getboardbynum(int num, int pageNum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boardDTO board = null; 
		updatehit(num);
		String sql = "select * from commuboard where cb_num = ?";

		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new boardDTO();
				board.setNum(rs.getInt("cb_num"));
				board.setName(rs.getString("m_name"));
				board.setAnimal_type(rs.getString("cb_animal_type"));
				board.setTitle(rs.getString("cb_title"));
				board.setContent(rs.getString("cb_content"));
				board.setRegist_day(rs.getString("cb_regist_day"));
				board.setFilename(rs.getString("cb_filename"));
				board.setHit(rs.getInt("cb_hit"));
				board.setRecom(rs.getInt("cb_recom"));
			}
			return board;
		} catch (Exception ex) {
			System.out.println("getBoardByNum() 에러 : " + ex);
		} finally {
			try {
				if (rs != null) 
					rs.close();							
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}		
		}
		return null;
	}

	@Override //글 수정하기
	public void updateboard(boardDTO board,HttpServletRequest req) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String regist_day = sdf.format(new Date());
		board.setHit(board.getHit()+1);
		if(board.getFileimage().getSize() != 0) {
			MultipartFile boardimg = board.getFileimage();
			
			String saveName = boardimg.getOriginalFilename();
			board.setFilename(saveName);
			String path = req.getServletContext().getRealPath("/resources/img/board/");
			File saveFile = new File(path,saveName);
			
			if (boardimg != null && !boardimg.isEmpty()) {
	            try {
	            	boardimg.transferTo(saveFile);  
	            } catch (Exception e) {
	                throw new RuntimeException("게시판 이미지 업로드가 실패하였습니다", e);
	            }
	        }
		}
		if(board.getFilename() != null) {
			String sql = "update commuboard set cb_board_type=?, cb_animal_type=?, m_name=?, cb_title=?, cb_content=?, cb_regist_day=?, cb_filename=?, cb_hit=?, cb_recom=? where cb_num=?";
			template.update(sql, board.getBoard_type(), board.getAnimal_type(), board.getName(), board.getTitle(), board.getContent(), regist_day, board.getFilename(), board.getHit(), board.getRecom(), board.getNum());
		}
		else if(board.getFilename() == null){
			String sql = "update commuboard set cb_board_type=?, cb_animal_type=?, m_name=?, cb_title=?, cb_content=?, cb_regist_day=?, cb_hit=?, cb_recom=? where cb_num=?";
			template.update(sql, board.getBoard_type(), board.getAnimal_type(), board.getName(), board.getTitle(), board.getContent(), regist_day, board.getHit(), board.getRecom(), board.getNum());
		}
	}
	
	@Override //게시글 번호를 이용해서 해당 게시글 내용 모델에 담기
	public boardDTO getByNum(int num) {
		boardDTO boardinfo=null;	
		for(int i=0;i<listOfboards.size();i++) {
			boardDTO board = listOfboards.get(i);
			if(board != null && board.getNum() == num) {
				boardinfo = board;
				break;
			}
		}		
		return boardinfo;
	}

	@Override //게시글 삭제 기능
	public void deleteboard(String num) {
		String sql = "delete from commuboard where cb_num=?";
		template.update(sql, num);
	}
	
	@Override //게시글 제목이나, 내용으로 검색하기
	public List<boardDTO> search(String content,pageDTO page) {
		int start = page.getCri().getpagestart();
		String sql = "select*from commuboard where cb_title like '%"+content+"%' or cb_content like '%"+content+"%' order by cb_num desc limit "+start+","+page.getCri().getAmount();
		List<boardDTO> boardlist = template.query(sql, new BoardMapper());	
		for (boardDTO board : boardlist) {
			String regist_day = caltime(board.getRegist_day());
			
			if(board.getAnimal_type() != null && board.getAnimal_type().equals("cat")) {
				board.setCalregist(regist_day);
				board.setTag_src("catface.png");
				board.setTag_value("고양이");
			}
			else if(board.getAnimal_type() != null && board.getAnimal_type().equals("dog")) {
				board.setCalregist(regist_day);
				board.setTag_src("dogface.png");
				board.setTag_value("강아지");
			}
		}
		return boardlist;		
	}
	
	@Override	//추천 기능
	public void recom(String num, String recom) {
		if(recom.equals("true")) {
			int cnt = 1;
			String sql = "update commuboard set cb_recom=cb_recom+? where cb_num=?";
			template.update(sql,cnt,num);
		}
		else if(recom.equals("false")) {
			int cnt = 1;
			String sql = "update commuboard set cb_recom=cb_recom-? where cb_num=?";
			template.update(sql,cnt,num);
		}
		
	}
	
	@Override	//인기글 가져오기
	public List<boardDTO> recomboard() {
		String sql = "select*from commuboard where cb_recom >= 10 limit 5";
		List<boardDTO> recomlist = template.query(sql, new BoardMapper());
		for (boardDTO board : recomlist) {
			String regist_day = caltime(board.getRegist_day());
			
			if(board.getAnimal_type() != null && board.getAnimal_type().equals("cat")) {
				board.setCalregist(regist_day);
				board.setTag_src("catface.png");
				board.setTag_value("고양이");
			}
			else if(board.getAnimal_type() != null && board.getAnimal_type().equals("dog")) {
				board.setCalregist(regist_day);
				board.setTag_src("dogface.png");
				board.setTag_value("강아지");
			}
		}
		return recomlist;
	}
	
	@Override
	public int getallcount() {
		String sql = "select count(*) from commuboard";
        int total_recond = template.queryForObject(sql, Integer.class);
        return total_recond;
	}
	
	
	@Override	//게시글 총 숫자 
	public int getcount(String content) {
		String sql = "select count(*) from commuboard where cb_title like '%"+content+"%' or cb_content like '%"+content+"%'";
		int total_recond = template.queryForObject(sql, Integer.class);
		return total_recond;
    }
	
	
}
