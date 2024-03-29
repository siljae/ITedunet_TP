package mvc.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.model.boardDAO;
import mvc.model.boardDTO;
import mvc.model.memberDAO;
import mvc.model.memberDTO;

@WebServlet("*.do")
public class controller extends HttpServlet{
	//직렬화
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//.do 경로 자르기
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if(command.equals("/login.do")) {//로그인 페이지로 이동
			RequestDispatcher rd = request.getRequestDispatcher("./login.jsp");
			rd.forward(request, response);
		}
		else if(command.equals("/loginaction.do")) {//로그인 기능 실행
			loginaction(request);
			HttpSession session = request.getSession(true);
			int msg = (Integer) request.getAttribute("msg");
			if(msg == -2){
				System.out.println("로그인프로세스 오류!");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp?msg=-2");
				rd.forward(request, response);
			}
			else if(msg == -1){
				System.out.println("로그인 DB접속 실패!");
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp?msg=-1");
				rd.forward(request, response);
			}
			else if(msg == 0){
				RequestDispatcher rd = request.getRequestDispatcher("/login.jsp?error=1");
				rd.forward(request, response);
			}
			else if(msg == 1){
				String email = (String)request.getAttribute("email");
				String username = (String)request.getAttribute("name");
				String level = (String)request.getAttribute("level");
				session.setAttribute("email", email);
				session.setAttribute("username", username);
				session.setAttribute("level", level);
				
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp?msg=1");
				rd.forward(request, response);			
			}
		}
		else if(command.equals("/signup.do")) {//회원가입 페이지로 이동
			RequestDispatcher rd = request.getRequestDispatcher("./signup.jsp");
			rd.forward(request, response);
		}
		else if(command.equals("/signupaction.do")) {//회원가입 기능 실행
			signupaction(request);
			RequestDispatcher rd = request.getRequestDispatcher("./login.jsp?msg=1");
			rd.forward(request, response);
		}
		else if(command.equals("/checkemail.do")) {//회원가입할 때 이메일 중복 체크
			checkemail(request);			
			boolean chk = (Boolean) request.getAttribute("Bemail");
			if(chk == true) {
				RequestDispatcher rd = request.getRequestDispatcher("./check_email.jsp?chk=1");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("./check_email.jsp?chk=2");
				rd.forward(request, response);
			}
		}
		else if(command.equals("/checkname.do")) {//회원가입할 때 닉네임 중복 체크
			checkname(request);
			boolean chk = (Boolean) request.getAttribute("Bname");
			if(chk == true) {
				RequestDispatcher rd = request.getRequestDispatcher("./check_name.jsp?chk=1");
				rd.forward(request, response);
			}
			else {
				RequestDispatcher rd = request.getRequestDispatcher("./check_name.jsp?chk=2");
				rd.forward(request, response);
			}
		}
		else if(command.equals("/commuboard.do")){//커뮤니티 게시판 이동
			RequestDispatcher rd = request.getRequestDispatcher("./board.jsp");
			rd.forward(request, response);
		}
	}
	
	//로그인 기능 
	public void loginaction(HttpServletRequest request) {
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String name=null;
		String level=null;
		int msg;
		
		if(email != null && pw != null) {
			memberDAO dao = memberDAO.getinstance();
			String[] result = dao.checklogin(email,pw);
			
			msg = Integer.parseInt(result[0]);
			name = result[1];
			level= result[2];
			
			request.setAttribute("msg", msg);
			request.setAttribute("email", email);
			request.setAttribute("name", name);
			request.setAttribute("level", level);
			
		}
		else {
			request.setAttribute("email", null);
			request.setAttribute("result", null);
		}
			
	}
	
	//회원가입 기능
	public void signupaction(HttpServletRequest request) {
		memberDAO dao = memberDAO.getinstance();
		memberDTO dto = new memberDTO();
		dto.setEmail(request.getParameter("email"));
		dto.setName(request.getParameter("name"));
		dto.setPw(request.getParameter("pw"));
		dto.setPhone1(request.getParameter("phone1"));
		dto.setPhone2(request.getParameter("phone2"));
		dto.setPhone3(request.getParameter("phone3"));
		dto.setPost(request.getParameter("post"));
		dto.setAddr1(request.getParameter("addr1"));
		dto.setAddr2(request.getParameter("addr2"));
		dao.insertmember(dto);
	}
	//회원가입시 이메일 중복체크 기능
	public void checkemail(HttpServletRequest request) {
		memberDAO dao = memberDAO.getinstance();
		String email = request.getParameter("email");
		boolean Bemail = dao.checkemail(email);
		request.setAttribute("Bemail", Bemail);
		
	}
	//회원가입시 닉네임 중복체크 기능
	public void checkname(HttpServletRequest request) {
		memberDAO dao = memberDAO.getinstance();
		String name = request.getParameter("name");
		boolean Bname = dao.checkname(name);		
		request.setAttribute("Bname", Bname);
		
	}

	
}
