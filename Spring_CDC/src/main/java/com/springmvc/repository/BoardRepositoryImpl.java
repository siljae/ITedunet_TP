package com.springmvc.repository;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;
import com.springmvc.database.DBConnection;
import com.springmvc.domain.boardDTO;
import com.springmvc.domain.criteria;
import com.springmvc.domain.fileDTO;
import com.springmvc.domain.pageDTO;
import com.springmvc.mapper.BoardMapper;
import com.springmvc.mapper.FileMapper;

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
		board.setBoard_type("자랑해요");
		if(board.getAnimal_type() != null && board.getAnimal_type().equals("cat")) {
			board.setTagsrc("catface.png");
			board.setTagvalue("고양이");
		}
		else if(board.getAnimal_type() != null && board.getAnimal_type().equals("dog")) {
			board.setTagsrc("dogface.png");
			board.setTagvalue("강아지");
		}
		
		//게시글 작성시간
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String regist_day = sdf.format(new Date());
		
		// commuboard에 새로운 레코드 삽입
		String sql = "insert into commuboard(cb_board_type, cb_tagsrc, cb_tagvalue, m_name, cb_title, cb_content, cb_regist_day, cb_hit, cb_recom) values(?,?,?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(connection -> {
		    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    ps.setString(1, board.getBoard_type());
		    ps.setString(2, board.getTagsrc());
		    ps.setString(3, board.getTagvalue());
		    ps.setString(4, board.getName());
		    ps.setString(5, board.getTitle());
		    ps.setString(6, board.getContent());
		    ps.setString(7, regist_day);
		    ps.setInt(8, board.getHit());
		    ps.setInt(9, board.getRecom());
		    return ps;
		}, keyHolder);
		
		// 생성된 pk의 값을 num으로 가져오기
		int pk_num = keyHolder.getKey().intValue();
		//이미지 파일업로드
		List<MultipartFile> filelist = board.getFileimages();
		String path = req.getServletContext().getRealPath("/resources/img/board/");		
		String filesql = "insert into board_file(board_type, cb_num, filename) values(?,?,?)";
		for(MultipartFile mf : filelist) {
			//이미지 이름 가져오기			
			String saveName = mf.getOriginalFilename();
			//이미지 이름 board객체에 저장
			board.setFilename(saveName);
			//이미지 경로 변수에 담기
			
			//파일 업로드
			File saveFile = new File(path,saveName);		
			if (mf != null && !mf.isEmpty()) {
	            try {
	            	mf.transferTo(saveFile);  
	            } catch (Exception e) {
	                throw new RuntimeException("게시판 이미지 업로드가 실패하였습니다", e);
	            }
	        }
			template.update(filesql,board.getBoard_type(), pk_num, saveName);			
		}
	}

	@Override	//커뮤니티게시판 게시글 가져오기
	public List<boardDTO> getboardlist(pageDTO page) {
		int start = page.getCri().getpagestart();
		String sql = "select * from commuboard order by cb_num desc limit "+start+","+page.getCri().getAmount();
		List<boardDTO> boardlist =  template.query(sql, new BoardMapper());
		
		for(boardDTO board : boardlist) {
			board.setCalregist(caltime(board.getRegist_day())); 
			int cb_num = board.getNum();
			String boardtype = board.getBoard_type();
			String filesql = "select * from board_file where board_type=? and cb_num=?";
			List<fileDTO> files = template.query(filesql, new FileMapper(), boardtype, cb_num);
			board.setFiles(files);
		}
		
		return boardlist;
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
		
	@Override	//게시글 상세페이지 가져오기
	public boardDTO getboardview(int num) {
		String sql = "select * from commuboard where cb_num=?";
		return template.queryForObject(sql, new BoardMapper(), num);
	}
	
	@Override	//게시글 조회수 증가
	public void updatehit(int num) {
		String sql = "update commuboard set cb_hit = cb_hit+1 where cb_num=?";
		template.update(sql, num);
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
		String sql = "select*from commuboard where cb_recom >= 10 limit 3";
		List<boardDTO> recomlist = template.query(sql, new BoardMapper());
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
