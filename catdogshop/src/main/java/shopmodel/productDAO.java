package shopmodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shopdatabase.DBConnection;
import shopmodel.productDTO;

public class productDAO {
	
	private static productDAO instance;
	
	//재료를 준비해봅시다 !
	private productDAO() {}
	private ArrayList<productDTO> listOfProducts = new ArrayList<productDTO>();
	public ArrayList<productDTO> getAllProducts(){
		return listOfProducts;
	}
	public static productDAO getInstance() {
		if (instance == null)
			instance = new productDAO();
		return instance;
	}
	
	//product 테이블의 레코드 개수
	
	public int getListCount(String items, String text) {
		Connection conn = null;
		System.out.println("conn :" + conn);
		PreparedStatement pstmt = null;
		System.out.println("pstmt :" + pstmt);
		ResultSet rs = null; //가져온 result 담는곳 (받는곳 set)
		System.out.println("rs :" + rs);

		int x = 0; // 게시글 수를 표현하기 위한 변수

		String sql; //sql담음
		
		if (items == null && text == null)// if문을 이용해서 검색 기능을 구현하고 있음
			sql = "select count(*) from product"; //조건이 없는 경우
		else
			sql = "SELECT count(*) FROM product where " + items + " like '%" + text + "%'";
			// 조건이 있는 경우
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("conn2 :" +conn);
			pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt2 :" + pstmt);
			rs = pstmt.executeQuery(); //result 받아와서 넣음
			System.out.println("rs2 :" + rs);
			
			if (rs.next()) //result 꺼냄
				x = rs.getInt(1); 
				System.out.println("result 잘 꺼냈냐? :" +x);
			
		} catch (Exception ex) {
			System.out.println("getListCount() 에러: " + ex);
		} finally {			
			try {				
				if (rs != null) //rs 닫아줌
					rs.close();
				if (pstmt != null) //pstmt 닫아줌
					pstmt.close();				
				if (conn != null) //conn 닫아줌
					conn.close();												
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}			
		}
		return x; //총 게시글 수를 표현하기 위한 x, 총 게시글 수는 목록 번호를 내는 데에도 사용
	}
	
	//product 테이블의 레코드 가져오기
	
	public ArrayList<productDTO> getProductList(int page, int limit, String items, String text) {
		//dto에서 여러개를 한번에 담아서 꺼내오려면 ArrayList 써야함
		// int page는 view.jsp에서 파라미터 받아와서 씀. 따라서 현재 페이지가 들어가게 되어있음.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int total_record = getListCount(items, text); //검색도 겸해서 바로 위의 메소드에서 총 페이지 수 받아옴
		int start = (page - 1) * limit; // 현재 페이지가 2라면 1*5가 됨
		int index = start + 1; // 게시글의 시작 마지막 번호를 구하기. 현제 페이지가 2라면 6
		// 이런 수식을 쓰는 이유는 게시글 번호를 내름차순 해서 정렬할 것이기 때문
		// 현재 페이지 앞에 있는 limit의 개수만큼 게시글을 날리고, 그 다음부터 출력하겠다.
		
		String sql;
		
		if (items == null && text == null) // 검색 조건이 없는 경우
			sql = "select * from product ORDER BY p_num DESC";
		else
			sql = "select * from product where " + items + "like '%" + text + "%' ORDER BY p_num DESC";
			// 내림차순 정렬하는 이유는 첫 페이지에 모든 게시글을 띄우기 위함
		ArrayList<productDTO> list = new ArrayList<productDTO>();
		
		try { 
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.absolute(index)) {
				productDTO product = new productDTO();
				product.setNum(rs.getInt("p_num"));
				product.setName(rs.getString("p_name"));
				product.setCategory(rs.getString("p_category"));
				product.setTitlement(rs.getString("p_titlement"));
				product.setSimpledescripton(rs.getString("p_simpledescription"));
				product.setManufacturer(rs.getString("p_manufacturer"));
				product.setUnitprice(rs.getInt("p_unitprice"));
				product.setUntisinstock(rs.getLong("p_untisinstock"));
				product.setDetailfilename(rs.getString("p_detailfilename"));
				product.setTitlefilename(rs.getString("p_titlefilename"));
				
				list.add(product);
				
				if(index < (start + limit)&& index <= total_record)
					index++;
				else
					break;
			}
			return list; //dto에서 여러개를 꺼내오려면 arraylist써야함
		} catch (Exception ex) {
			System.out.println("getBoardList() 에러 : " + ex);
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
		//member 테이블에서 인증된 id의 사용자명 가져오기
	
	public String getLoginNameByNum(int num) {//인수에 name 받아옴. name은 세션에 저장되어있음
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String name=null;
		String sql = "select * from member where num = ?";
			
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num); //name 입력
			rs = pstmt.executeQuery();
				
			if(rs.next())
				name = rs.getString("name"); //쿼리로 닉네임출력
				
			return name;
		} catch (Exception ex) {
			System.out.println("getProductByNum() 에러 : " +ex);
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
	public void insertProduct(productDTO product) { //add폼에서 쓰는 dao. product는 dto로 폼에서 먼저 파라미터 값 가져옴
	//새로운 상품 등록하기
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBConnection.getConnection();
			
			String sql = "insert into product values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, product.getNum());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getCategory());
			pstmt.setString(4, product.getTitlement());
			pstmt.setString(5, product.getSimpledescripton());
			pstmt.setString(6, product.getManufacturer());
			pstmt.setInt(7, product.getUnitprice());
			pstmt.setLong(8, product.getUntisinstock());
			pstmt.setString(9, product.getDetailfilename());
			pstmt.setString(10, product.getTitlefilename());
			pstmt.setString(11, product.getDate());
			pstmt.setInt(11, product.getHit());			
			pstmt.executeUpdate(); // 실질적으론 파라미터 받아와서 넣는 일 밖에 하지 않음
			
		} catch(Exception ex) {
			System.out.println("insertProduct() 에러 : " +ex);
		}finally {
			try {									
				if (pstmt != null) 
					pstmt.close();				
				if (conn != null) 
					conn.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
	// 선택된 글 조회수 증가하기
	// 글 번호 인수로 받아와서 사용
	// 아래의 getProductByNum 메소드와 직접적으로 연관
	public void updateHit(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			
			String sql = "select hit from product where num = ?";
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			int hit = 0;
			
			if (rs.next())
				hit = rs.getInt("hit") + 1;
			
			sql = "update product set hit=? where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, hit);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();			
		} catch (Exception ex) {
			System.out.println("updateHit() 에러:" +ex);
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
		
	}
	public productDTO getProductByNum(int num, int page) {
		// page는 사실상 상관없는 인수. 조회는 num으로 함
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		productDTO product = null;
		
		updateHit(num);
		String sql = "select * from product where num =?";
		
		try { 
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				product.setNum(rs.getInt("num"));
				product.setName(rs.getString("name"));
				product.setCategory(rs.getString("category"));
				product.setTitlement(rs.getString("simpledescription"));
				product.setManufacturer(rs.getString("Manufacturer"));
				product.setUnitprice(rs.getInt("unitprice"));
				product.setUntisinstock(rs.getLong("untisinstock"));
				product.setDetailfilename(rs.getString("detailfilename"));
				product.setTitlefilename(rs.getString("titlefilename"));
				product.setDate(rs.getString("date"));
				product.setHit(rs.getInt("hit"));
			}
			return product;
		}catch (Exception ex) {
			System.out.println("getProductByNum() 에러 :" +ex);
		}finally {
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
	// 선택된 글 내용 수정하기
	public void updateProduct(productDTO product) {
		//미리 컨트롤러에서 dto를 만들어 보냄. 이를 이용.
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			String sql = "update product set name =?, subject=?, content=? where num=?";
			
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			conn.setAutoCommit(false);
			
			pstmt.setInt(1, product.getNum());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getCategory());
			pstmt.setString(4, product.getTitlement());
			pstmt.setString(5, product.getSimpledescripton());
			pstmt.setString(6, product.getManufacturer());
			pstmt.setInt(7, product.getUnitprice());
			pstmt.setLong(8, product.getUntisinstock());
			pstmt.setString(9, product.getDetailfilename());
			pstmt.setString(10, product.getTitlefilename());
			pstmt.setString(11, product.getDate());
			
			pstmt.executeUpdate();
			conn.commit();
		}catch (Exception ex) {
			System.out.println("updateProduct() 에러 : " +ex);
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			}catch (Exception ex) {
				throw new RuntimeException (ex.getMessage());
			}
		}
	}
	// 선택된 글 삭제하기
	public void deleteProduct(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from product where num = ?";
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("deleteProduct() 에러 : " +ex);
		}finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			}catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		}
	}
}
