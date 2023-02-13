package mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println(RequestURI);
		String contextPath = request.getContextPath();
		System.out.println(contextPath);
		String command = RequestURI.substring(contextPath.length());
		System.out.println(command);
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		if(command.equals("/login.do")) {//로그인 페이지로 이동
			RequestDispatcher rd = request.getRequestDispatcher("./login.jsp");
			rd.forward(request, response);
		}
		else if(command.equals("/loginaction.do")) {//로그인 기능 실행
			loginaction(request);
			RequestDispatcher rd = request.getRequestDispatcher("./index.jsp");
			rd.forward(request, response);			
		}
		else if(command.equals("/signup.do")) {//회원가입 페이지로 이동
			RequestDispatcher rd = request.getRequestDispatcher("./signup.jsp");
			rd.forward(request, response);
		}
		else if(command.equals("/signupaction.do")) {//회원가입 기능 실행
			signupaction(request);
			RequestDispatcher rd = request.getRequestDispatcher("./login.jsp");
			rd.forward(request, response);
		}
	}
	
	//로그인 기능 
	public void loginaction(HttpServletRequest request) {
		
	}
	
	//회원가입 기능
	public void signupaction(HttpServletRequest request) {
		memberDAO dao = memberDAO.getinstance();
		System.out.println(dao);
		System.out.println("dao주소 받아옴");
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
		System.out.println("이제 dto 다 저장했고");
		dao.insertmember(dto);
	}
}
