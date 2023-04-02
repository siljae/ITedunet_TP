package com.springmvc.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	
	@Autowired
	public void setJdbcTemplate(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	
	@Override //글쓰기 기능
	public void writeboard(boardDTO board,HttpServletRequest req) {
		for (int i = 0; i < board.getFileimages().size(); i++) {
			MultipartFile mf = board.getFileimages().get(i);
			if(mf.getSize() == 0) {
				board.getFileimages().remove(i);
			}
		}
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
		board.setNum(keyHolder.getKey().intValue());
		//파일업로드하고 DB에 파일이름 저장
		List<MultipartFile> filelist = board.getFileimages();
		String path = req.getServletContext().getRealPath("/resources/img/board/");		
		String filesql = "insert into boardfile(board_type, cb_num, filename) values(?,?,?)";
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
			template.update(filesql,board.getBoard_type(), board.getNum(), saveName);
		}
		
	}

	@Override	//커뮤니티게시판 게시글 가져오기
	public List<boardDTO> getboardlist(pageDTO page) {
		int start = page.getCri().getpagestart();
		String sql = "select * from commuboard order by cb_num desc limit "+start+","+page.getCri().getAmount();
		List<boardDTO> boardlist =  template.query(sql, new BoardMapper());
		
		for(boardDTO board : boardlist) {
			board.setCalregist(caltime(board.getRegist_day()));
			String filesql = "select * from boardfile where board_type=? and cb_num=?";
			List<fileDTO> files = template.query(filesql, new FileMapper(), board.getBoard_type(), board.getNum());
			board.setFiles(files);
		}		
		return boardlist;
	}
	
	@Override //게시판 정렬 기능
	public List<boardDTO> getsortboardlist(pageDTO page, String sort){
		int start = page.getCri().getpagestart();
		String sql;
		List<boardDTO> boardlist = new ArrayList<boardDTO>();
		if(sort.equals("viewed")) {
			sql = "select*from commuboard order by cb_hit desc limit "+start+","+page.getCri().getAmount();
			boardlist = template.query(sql, new BoardMapper());
			for(boardDTO board : boardlist) {
				board.setCalregist(caltime(board.getRegist_day()));
				String filesql = "select * from boardfile where board_type=? and cb_num=?";
				List<fileDTO> files = template.query(filesql, new FileMapper(), board.getBoard_type(), board.getNum());
				board.setFiles(files);
			}
		}
		else if(sort.equals("popular")) {
			sql = "select*from commuboard order by cb_recom desc limit "+start+","+page.getCri().getAmount();
			boardlist = template.query(sql, new BoardMapper());
			for(boardDTO board : boardlist) {
				board.setCalregist(caltime(board.getRegist_day()));
				String filesql = "select * from boardfile where board_type=? and cb_num=?";
				List<fileDTO> files = template.query(filesql, new FileMapper(), board.getBoard_type(), board.getNum());
				board.setFiles(files);
			}
		}
		else if(sort.equals("newest")) {
			sql = "select*from commuboard order by cb_num desc limit "+start+","+page.getCri().getAmount();;
			boardlist = template.query(sql, new BoardMapper());
			for(boardDTO board : boardlist) {
				board.setCalregist(caltime(board.getRegist_day()));
				String filesql = "select * from boardfile where board_type=? and cb_num=?";
				List<fileDTO> files = template.query(filesql, new FileMapper(), board.getBoard_type(), board.getNum());
				board.setFiles(files);
			}
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
	public boardDTO getboardview(int num, HttpServletRequest req) {
		String sql = "select * from commuboard where cb_num=?";
		boardDTO board = template.queryForObject(sql, new BoardMapper(), num);
		
		String filesql = "select * from boardfile where board_type=? and cb_num=?";
		List<fileDTO> files = template.query(filesql, new FileMapper(), board.getBoard_type(), board.getNum());
		
		board.setFiles(files);
		
		String[] filenames = new String[files.size()];
		for(int i = 0;i < files.size();i++) {			
			fileDTO file = files.get(i);
			filenames[i] = file.getFilename();
		}
		
		board.setFilenames(filenames);
		
		return board;
	}
	
	@Override	//게시글 조회수 증가
	public void updatehit(int num) {
		String sql = "update commuboard set cb_hit = cb_hit+1 where cb_num=?";
		template.update(sql, num);
	}

	@Override //게시글 삭제 기능
	public void deleteboard(String num) {
		String sql = "delete from commuboard where cb_num=?";
		template.update(sql, num);
	}
	
	@Override //게시글 수정하기 기능
	public void updateboard(boardDTO board, HttpServletRequest req) {
		for (int i = 0; i < board.getFileimages().size(); i++) {
			MultipartFile mf = board.getFileimages().get(i);
			if(mf.getSize() == 0) {
				board.getFileimages().remove(i);
			}
		}
		board.setBoard_type("자랑해요");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String regist_day = sdf.format(new Date());
		
		//DB에 게시글 수정
		String sql = "update commuboard set cb_board_type=?, cb_tagsrc=?, cb_tagvalue=? , cb_title=?, cb_content=?, cb_regist_day=?, cb_hit=?, cb_recom=? where cb_num=?";
		template.update(sql, board.getBoard_type(), board.getTagsrc(), board.getTagvalue(), board.getTitle(), board.getContent(), regist_day, board.getHit(), board.getRecom(), board.getNum());
		
		//파일이 없으면 boardfile테이블에서 삭제
		if (board.getFileimages().size() == 0 && board.getFilenames() == null) {		    
		    String deletesql = "delete from boardfile where board_type=? and cb_num=?";
		    template.update(deletesql, board.getBoard_type(), board.getNum());
		}
		else {
			String path = req.getServletContext().getRealPath("/resources/img/board/");
			for(MultipartFile mf : board.getFileimages()) {
				String saveName = mf.getOriginalFilename();
				File saveFile = new File(path, saveName);
				if(mf != null && !mf.isEmpty()) {
					try {
						mf.transferTo(saveFile);
					}
					catch(Exception e) {
						throw new RuntimeException("게시판 이미지 수정 업로드에 실패했습니다",e);
					}
				}
			}
			String deletesql = "delete from boardfile where board_type=? and cb_num=?";
			template.update(deletesql, board.getBoard_type(), board.getNum());
			
			String insertsql = "insert into boardfile(board_type, cb_num, filename) values(?,?,?)";
			for(String filename : board.getFilenames()) {
				template.update(insertsql, board.getBoard_type(), board.getNum(), filename);
			}
		}
	}
	
	@Override //게시글 제목이나, 내용으로 검색하기
	public List<boardDTO> search(String content,pageDTO page) {
		int start = page.getCri().getpagestart();
		String sql = "select*from commuboard where cb_title like '%"+content+"%' or cb_content like '%"+content+"%' order by cb_num desc limit "+start+","+page.getCri().getAmount();
		List<boardDTO> boardlist = template.query(sql, new BoardMapper());
		return boardlist;		
	}
	
	@Override	//추천 기능
	public void recom(int num, String recom) {
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
	public List<boardDTO> getrecomboard() {
		String sql = "select*from commuboard where cb_recom >= 10 limit 3";
		List<boardDTO> recomlist = template.query(sql, new BoardMapper());
		return recomlist;
	}
	
	@Override	//게시글 전체 개수 가져오기
	public int getallcount() {
		String sql = "select count(*) from commuboard";
        int total_recond = template.queryForObject(sql, Integer.class);
        return total_recond;
	}
	
	
	@Override	//검색할 게시글의 전체 개수 가져오기
	public int getcount(String content) {
		String sql = "select count(*) from commuboard where cb_title like '%"+content+"%' or cb_content like '%"+content+"%'";
		int total_recond = template.queryForObject(sql, Integer.class);
		return total_recond;
    }
	
	
}
