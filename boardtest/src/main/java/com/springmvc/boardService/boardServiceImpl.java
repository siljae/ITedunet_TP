package com.springmvc.boardService;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.domain.boardDTO;

@Service
public class boardServiceImpl implements boardService {
	
	private JdbcTemplate template;
	
	@Autowired	//데이베이스 연동을 위해 내가 설정한 db설정 값을 JdbcTemplate클래스에 전달용
	public void setjdbctemplate(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	
	@Override	//게시글 목록 가져오기
	public List<boardDTO> getboardlist() {
		String sql = "select*from board";
		List<boardDTO> boardlist = template.query(sql, new boardRowMapper());
		return boardlist;
	}

	@Override //게시글 등록
	public void setboard(boardDTO board,HttpServletRequest req) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String regist_day = sdf.format(new Date());
		
		if(board.getImg().getSize() != 0) {
			MultipartFile boardimg = board.getImg();			
			
			File saveFile = new File(req.getServletContext().getRealPath("/resources/img/"),boardimg.getOriginalFilename());
		}
		
		String sql = "insert into board(title, content, filename, regist_day) values(?,?,?,?)";
		template.update(sql, board.getTitle(), board.getContent(), board.getImgname(), regist_day);		
	}

	@Override	//게시글 수정
	public void updateboard(boardDTO board,HttpServletRequest req) {
		
		
	}

	@Override	//게시글 삭제
	public void deleteboard() {
		// TODO Auto-generated method stub
		
	}

	@Override //게시글 목록에서 작성시간 비교하기
	public void calTime() {
		// TODO Auto-generated method stub
		
	}


	
}
