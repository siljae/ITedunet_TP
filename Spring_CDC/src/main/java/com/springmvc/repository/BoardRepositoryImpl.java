package com.springmvc.repository;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.springmvc.domain.boardDTO;

@Repository
public class BoardRepositoryImpl implements BoardRepositoty {
	private JdbcTemplate template;
	
	@Autowired
	public void setJdbcTemplate(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	
	@Override //글쓰기 기능
	public void writeboard(boardDTO board,HttpServletRequest req) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String regist_day = sdf.format(new Date());
		
		MultipartFile boardimg = board.getFileimage();
		
		String saveName = boardimg.getOriginalFilename();
		System.out.println("파일이름 : "+ saveName);
		board.setFilename(saveName);
		String path = req.getSession().getServletContext().getRealPath("/resources/img/board/");
		System.out.println("경로: "+path);
		File saveFile = new File(path,saveName);
		
		if (boardimg != null && !boardimg.isEmpty()) {
            try {
            	boardimg.transferTo(saveFile);  
            } catch (Exception e) {
                throw new RuntimeException("게시판 이미지 업로드가 실패하였습니다", e);
            }
        }
		
		String sql = "insert into commuboard(cb_board_type,cb_animal_type,m_name, cb_title, cb_content, cb_regist_day, cb_filename, cb_hit) values(?,?,?,?,?,?,?,?)";
		template.update(sql, board.getBoard_type(), board.getAnimal_type(), board.getName(), board.getTitle(), board.getContent(), regist_day, board.getFilename(), board.getHit());
	}

	@Override //전체 게시글 불러오기
	public void boardlist(Model model) {
		int pageNum =1;
		final int LISTCOUNT =5;
		
		if(model.getAttribute("pageNum") != null)
			pageNum = Integer.parseInt((String)model.getAttribute("pageNum"));
		
		String items = (String)model.getAttribute("items");
		String content = (String)model.getAttribute("content");
	}

	
	
	

}
