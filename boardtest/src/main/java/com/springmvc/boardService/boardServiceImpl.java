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
	private List<boardDTO> listOfboard;
	
	@Autowired	//데이베이스 연동을 위해 내가 설정한 db설정 값을 JdbcTemplate클래스에 전달용
	public void setjdbctemplate(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	
	@Override	//게시글 목록 가져오기
	public List<boardDTO> getboardlist() {
		String sql = "select*from board order by num desc";
		List<boardDTO> boardlist = template.query(sql, new boardRowMapper());
		for(boardDTO board : boardlist) {
			board.setCalregist_day(calTime(board.getRegist_day()));
		}
		listOfboard = boardlist;
		return boardlist;
	}

	@Override //게시글 등록
	public void setboard(boardDTO board,HttpServletRequest req) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String regist_day = sdf.format(new Date());
		
		if(board.getImg().getSize() != 0) {
			MultipartFile boardimg = board.getImg();
			String saveName = boardimg.getOriginalFilename();
			board.setImgname(saveName);
			String path = req.getServletContext().getRealPath("/resources/img/");
			
			File saveFile = new File(path, saveName);
			if(boardimg != null && !boardimg.isEmpty()) {
				try {
					boardimg.transferTo(saveFile);
				}catch(Exception e) {
					throw new RuntimeException("게시판 이미지 업로드 실패",e);
				}
			}
		}
		
		String sql = "insert into board(title, content, imgname, regist_day) values(?,?,?,?)";
		template.update(sql, board.getTitle(), board.getContent(), board.getImgname(), regist_day);		
	}

	@Override	//게시글 수정
	public void updateboard(boardDTO board,HttpServletRequest req) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String regist_day = sdf.format(new Date());
		
		if(board.getImg().getSize() != 0) {
			MultipartFile boardimg = board.getImg();
			String saveName = boardimg.getOriginalFilename();
			board.setImgname(saveName);
			String path = req.getServletContext().getRealPath("/resources/img/");
			
			File saveFile = new File(path, saveName);
			if(boardimg != null && !boardimg.isEmpty()) {
				try {
					boardimg.transferTo(saveFile);
				}catch(Exception e) {
					throw new RuntimeException("게시판 이미지 업로드 실패",e);
				}
			}
		}
		
		if(board.getImgname() != null) {
			String sql = "update board set title=?, content=?, imgname=?, regist_day=? where num=?";
			template.update(sql, board.getTitle(), board.getContent(), board.getImgname(), regist_day, board.getNum());
		}
		else {
			String sql = "update board set title=?, content=?, regist_day=? where num=?";
			template.update(sql, board.getTitle(), board.getContent(), regist_day, board.getNum());
		}
	}

	@Override	//게시글 삭제
	public void deleteboard(int num) {
		String sql = "delete from board where num=?";
		template.update(sql, num);		
	}

	@Override //게시글 목록에서 작성시간 비교하기
	public String calTime(String time) {
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

	@Override	//게시글 보여주는 기능
	public boardDTO view(int num) {
		boardDTO boardinfo = new boardDTO();
		for (int i = 0; i < listOfboard.size(); i++) {
			boardDTO board = new boardDTO();
			board = listOfboard.get(i);
			if (board.getNum() == num) {
				boardinfo = board;
			}
		}
		return boardinfo;
	}
	
	


	
}
