package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shopmodel.productDTO;
import shopmodel.productDAO;


public class controller extends HttpServlet {
	private static final long serialVarsionUID = 1L;
	static final int LISTCOUNT = 9;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String RequestURI = request.getRequestURI();// 요청 uri 받음 (상대경로)
		System.out.println("uri 잘 들어왔늬 :" + RequestURI);
		String contextPath = request.getContextPath();// 상대경로의 파일명 제외한 디렉토리 부분
		System.out.println("디렉토리 잘 들어왔늬 :" + contextPath); 
		String command = RequestURI.substring(contextPath.length()); //substring을 이용해 파일명만 빼옴 
		System.out.println("파일명 잘 들어왔늬 :" + command);
		
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		if (command.equals("/productlistaction.go")) 
		{//등록된 상품리스트 페이지 출력하기
			requestProductList(request); // 메소드(모델)로 리퀘스트 처리
			RequestDispatcher rd = request.getRequestDispatcher("./shopslide.jsp"); //뷰로 연결
			// RequestDispatcher = 요청 보내거나 결과를 얻어오거나 하는 애 
			rd.forward(request, response);
			// forward = 특정 다원으로 제어를 넘김
		}else if (command.equals("/prouductadd.go")) { // 상품등록 페이지 출력하기
			requestLoginName(request);
			RequestDispatcher rd = request.getRequestDispatcher("./productadd.jsp");
			rd.forward(request, response);				
		} else if (command.equals("/productaddaction.go")) {// 새로운 상품 등록하기
			requestProductAdd(request);
			RequestDispatcher rd = request.getRequestDispatcher("/productlistaction.jsp");
			rd.forward(request, response);						
		} else if (command.equals("/productviewaction.go")) {//선택된 상품 상세 페이지 가져오기
			requestProductView(request);
			RequestDispatcher rd = request.getRequestDispatcher("/productview.go");
			rd.forward(request, response);						
		} else if (command.equals("/productview.go")) { //상품 상세 페이지 출력하기
			RequestDispatcher rd = request.getRequestDispatcher("./productpage.jsp");
			rd.forward(request, response);	
		} else if (command.equals("/productupdate.go")) { //선택된 상품의 조회수 증가하기
			requestProductUpdate(request);
			RequestDispatcher rd = request.getRequestDispatcher("/productlistaction.go");
			rd.forward(request, response);
		}else if (command.equals("/productdeleteaction.go")) { //선택된 상품 삭제하기
			requestProductDelete(request);
			RequestDispatcher rd = request.getRequestDispatcher("/productlistaction.go");
			rd.forward(request, response);				
		} 
	}
	// 등록된 상품리스트 가져오깅
	public void requestProductList(HttpServletRequest request) {
		productDAO dao = productDAO.getInstance();
		List<productDTO> productlist = new ArrayList<productDTO>();
		
		int pageNum = 1;
		int limit = LISTCOUNT; // LISTCOUNT는 9. 한 페이지에 게시글 9개만 보여주겠다는 뜻.
		
		if(request.getParameter("pageNum")!=null)
			pageNum=Integer.parseInt(request.getParameter("pageNum")); //menu나 기타 창에서 던진 파라미터 받기.
		
		String items = request.getParameter("items");
		String text = request.getParameter("text");
		
		int total_record=dao.getListCount(items, text); // 전체 개수 표시
		System.out.println("어어어어어어억:" + total_record);
		productlist = dao.getProductList(pageNum, limit, items, text); //검색 기능을 겸한 리스트 도출 메서드
		
		for (productDTO dto : productlist) {
			System.out.println("카테고리"+dto.getCategory());
		}
		
		int total_page;
		
		if (total_record % limit == 0) {
			total_page =total_record/limit;//limit은 5이고, 5로 나눔
			Math.floor(total_page);
		}
		else { //나머지가 0이 아닌 경우.
			total_page = total_record/limit;
			Math.floor(total_page);
			total_page = total_page + 1;
		} //페이지 개수를 표시. +1을 해준 이유는 5개 미만의 게시물을 커버 하기 위함
		
		request.setAttribute("pageNum", pageNum);// 현재페이지
		request.setAttribute("total_page", total_page); // 총 페이지
		request.setAttribute("total_record", total_record);// 레코드 갯수
		request.setAttribute("productlist", productlist); // 게시글 제목들

	}
	// 인증된 사용자명 가져오기
	public void requestLoginName(HttpServletRequest request) {
		
		int num = Integer.parseInt(request.getParameter("num")); // num 파라미터는 list 창에서 유효성 검사로 걸러줌. 반드시 보통 가지고 있음
		
		productDAO dao = productDAO.getInstance(); //num가 있으므로 dao를 인스턴스
		
		String name = dao.getLoginNameByNum(num); // num 찾아서 name을 받기위해서 실행
		
		request.setAttribute("name", name); // name 파라미터에 id에서 추출한 name을 할당
	}
	
	
	public void requestProductAdd(HttpServletRequest request) { //productadd 에서 요청받아옴
		
		
		productDAO dao = productDAO.getInstance();//써먹으려고 메소드 실행
		
		productDTO product = new productDTO(); //dto 만듬. 상품 데이터를 담아 옮길것
		product.setNum(Integer.parseInt(request.getParameter("num")));
		product.setName(request.getParameter("name"));
		product.setCategory(request.getParameter("category"));
		product.setTitlement(request.getParameter("titlement"));
		product.setSimpledescripton(request.getParameter("simpledescription"));
		product.setManufacturer(request.getParameter("manufacturer"));
		product.setUnitprice(Integer.parseInt(request.getParameter("unitprice")));
		product.setUntisinstock(Integer.parseInt(request.getParameter("untisinstock")));
		product.setDetailfilename(request.getParameter("detailfilename"));
		product.setTitlefilename(request.getParameter("titlefile"));
		product.setDate(request.getParameter("date"));
		
		System.out.println(Integer.parseInt(request.getParameter("num")));
		System.out.println(request.getParameter("name"));
		System.out.println(request.getParameter("category"));
		System.out.println(request.getParameter("titlement"));
		System.out.println(request.getParameter("simpledescription"));
		System.out.println(request.getParameter("manufacturer"));
		System.out.println(Integer.parseInt(request.getParameter("unitprice")));
		System.out.println(Integer.parseInt(request.getParameter("untisinstock")));
		System.out.println(request.getParameter("detailfilename"));
		System.out.println(request.getParameter("titlefile"));
		System.out.println(request.getParameter("date"));
		
		product.setHit(0);
		
		dao.insertProduct(product);// 이 내용을 데이터 베이스에 옮김
		
	}
	
	//선택된 글 상세 페이지 가져오기
	public void requestProductView(HttpServletRequest request) { //글의 상세 정보인 productpage.jsp로 감
		productDAO dao = productDAO.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		productDTO product = new productDTO();
		product = dao.getProductByNum(num, pageNum); // 기본적으로 num통해서 조회
		
		request.setAttribute("num", num);
		request.setAttribute("page", pageNum);
		request.setAttribute("product", product); //productpage.jsp위해 파라미터 던져줌
	}
	
	//선택된 글 내용 수정하기 (관리자)
	public void requestProductUpdate(HttpServletRequest request) {
		
	}
	
	public void requestProductDelete(HttpServletRequest request) {
		
	}
	
}
