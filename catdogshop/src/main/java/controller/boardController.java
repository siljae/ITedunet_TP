package controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import shopmodel.boardDAO;
import shopmodel.boardDTO;

@WebServlet("*.action")
public class boardController extends HttpServlet{
	//	직렬화
		private static final long serialVersionUID = 1L;
		static final int LISTCOUNT = 5;
		
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			doPost(request, response);
		}
		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			//.do 경로 자르기
			String RequestURI = request.getRequestURI();
			System.out.println(RequestURI);
			String contextPath = request.getContextPath();
			System.out.println(contextPath);
			String command = RequestURI.substring(contextPath.length());
			System.out.println(command);
			
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("UTF-8");
			
			if(command.equals("/commuboard.action")) {//게시판 목록 보여주기
				boardlist(request);
				RequestDispatcher rd = request.getRequestDispatcher("/board.jsp");
				rd.forward(request, response);
			}
			else if(command.equals("/commuwrite.action")){//커뮤니티 게시판 글쓰기 페이지로 이동
				RequestDispatcher rd = request.getRequestDispatcher("/commuwrite.jsp");
				rd.forward(request, response);
			}
			else if(command.equals("/writeaction.action")) {//글 등록
				writeaction(request);
				boardlist(request);
				RequestDispatcher rd = request.getRequestDispatcher("/board.jsp");
				rd.forward(request, response);
			}
		}
		

		
		//게시판 목록
		public void boardlist(HttpServletRequest request) {
			boardDAO dao = boardDAO.getinstance();
			List<boardDTO> boardlist = new ArrayList<boardDTO>();
			
			int pageNum = 1;
			int limit=LISTCOUNT;
			
			if(request.getParameter("pageNum") != null)			
				pageNum=Integer.parseInt(request.getParameter("pageNum")); 
			
			String items = request.getParameter("items");
			String text = request.getParameter("text");
			
			int total_record = dao.getListCount(items,text);
			System.out.println("총게시글숫자: "+total_record);
			boardlist = dao.getBoardList(pageNum, limit, items, text);
			
			String animal_type;
			String tag_src;
			String tag_value;
			
			for (boardDTO dto : boardlist) {
				animal_type = dto.getAnimal_type();
				System.out.println("db에서 가져온 동물타입"+animal_type);
				
				if(animal_type != null && animal_type.equals("cat")) {
					tag_src = "./resources/img/board/catface.png";
					tag_value = "고양이";
					request.setAttribute("tag_src", tag_src);
					request.setAttribute("tag_value",tag_value);
				}
				else if(animal_type != null && animal_type.equals("dog")) {
					tag_src = "/resources/img/board/dogface.png";
					tag_value = "강아지";
					request.setAttribute("tag_src", tag_src);
					request.setAttribute("tag_value",tag_value);
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
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("total_page", total_page);
			request.setAttribute("total_record", total_record);
			request.setAttribute("boardlist", boardlist);
			
		}
		
		//글쓰기 등록 기능
		public void writeaction(HttpServletRequest request) {
			System.out.println("글등록함수로 왔어요");
			try {
				boardDAO dao = boardDAO.getinstance();
				boardDTO dto = new boardDTO();
				SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
				String regist_day = fm.format(new Date());
				
				String filename = "";
				String realfolder = request.getServletContext().getRealPath("/resources/img");
				int maxsize = 5*1024*1024;
				String enctype ="UTF-8";
				MultipartRequest multi = new MultipartRequest(request, realfolder,maxsize, enctype, new DefaultFileRenamePolicy());
				String name = multi.getParameter("name");
				String title = multi.getParameter("title");
				String content = multi.getParameter("content");
				Enumeration files = multi.getFileNames();
				String fname = (String)files.nextElement();
				filename =multi.getFilesystemName(fname);
				String animal_type = multi.getParameter("animal_type");
				System.out.println("동물타입머임??"+animal_type);
				
				dto.setName(name);
				dto.setTitle(title);
				dto.setContent(content);
				dto.setRegist_day(regist_day);
				dto.setFilename(filename);
				System.out.println(filename);
				dto.setHit(0);
				dto.setAnimal_type(animal_type);
				
				dao.writeacion(dto);
				
			} catch (Exception e) {
				System.out.println("파일업로드 에러: "+e);
			}			
		}
}