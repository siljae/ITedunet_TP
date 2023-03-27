package com.springmvc.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.cartDTO;
import com.springmvc.domain.productDTO;
import com.springmvc.service.cartService;
import com.springmvc.service.productService;

@Controller
@RequestMapping(value="/cart")
public class cartController {
	@Autowired
	private cartService cs;
	@Autowired
	private productService ps;
	
	@RequestMapping
	public String requestCart(@ModelAttribute cartDTO dto, HttpSession session) {
		System.out.println("cartcontroller-requestCart 들어왔니?");
		String name = (String) session.getAttribute("name");
		System.out.println("cartcontroller-name 값 가져오니? :" + name);
		String productId = (String) session.getAttribute("productid");
		System.out.println("cartcontroller-productId 값 가져오니? :" + productId);
		int quantity = (Integer) session.getAttribute("quantity");
		System.out.println("cartcontroller-quantity 값 가져오니? :" + quantity);
		String tfilename = (String) session.getAttribute("tfilename");
		System.out.println("cartcontroller-tfilename 값 가져오니? :" + tfilename);
		dto.setName(name);
		return "redirect:/cart/add";
	}
	
	@RequestMapping("/add")
	public String NewCart(@Valid @ModelAttribute("NewCart") cartDTO cart, BindingResult result, HttpSession session) {
		System.out.println("cartcontroller-NewCart들어왔니?");
		cart.setName((String)session.getAttribute("name"));
		System.out.println("cartcontroller-newcart name :" + (String)session.getAttribute("name"));
		cart.setProductId((String) session.getAttribute("productId"));
		System.out.println("cartcontroller-newcart productId :" + (String)session.getAttribute("productId"));
		cart.setQuantity((Integer) session.getAttribute("quantity"));
		System.out.println("cartcontroller-newcart quantity :" + (Integer)session.getAttribute("quantity"));
		cart.setTfilename((String) session.getAttribute("tfilename"));
		System.out.println("cartcontroller-newcart tfilename :" + (String)session.getAttribute("tfilename"));
//		MultipartFile titleimage = cart.getTitleimage();
//		System.out.println("detailimage 넣었니" + titleimage);
//		String saveName1 = titleimage.getOriginalFilename();
//		System.out.println("originalname 넣었니" + saveName1);
//		File saveFile1 = new File("D:/KSH/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/cdshop/resources/img", saveName1);
//		System.out.println("파일 업로드에 들어갔니" + saveFile1);
//		if (titleimage != null && !titleimage.isEmpty()) {
//			System.out.println("예외처리 if문");
//			try {
//				titleimage.transferTo(saveFile1);
//				cart.setTfilename(saveName1);
//			}catch (Exception e) {
//				throw new RuntimeException("상품 이미지 업도르가 실패하였습니다.", e);
//			}
//		}
		cs.setNewCart(cart);
		System.out.println("cartcontroller- cart들어갔니? : " + cart);
		return "redirect:/cart/list";
		
	}
	
	@RequestMapping("/list")
	public ModelAndView cartlist(Model model) {
		System.out.println("cartcontroller-cartlist 들어왔니?");
		ModelAndView mav = new ModelAndView();
		System.out.println("cartcontroller-mav 생성했니? : " + mav);
		List<cartDTO> list = cs.getAllCartList();
		System.out.println("cartcontroller-cs에서 cartlist 가져오니? :" + list);
		for(cartDTO dto : list) {
			System.out.println(dto.getName());
		}
		mav.addObject("cartlist", list);
		mav.setViewName("mypagecart");
		return mav;
		
	}
	
//	
//	@PostMapping
//	public @ResponseBody cart create(@RequestBody cart Cart) {
//		// 웹 요청 uri가 /cart고 http메서드가 post방식이면 매핑 (사용자 요청 처리)
//		System.out.println("cartcontroller - post 얘는 언제들어오니?");
//		return cartservice.create(Cart);
//		//cart 클래스 정보를 http요청 body로 전달받아 장바구니를 새로 생성하고 http 응답 body로 전달
//	}
//	
//	@GetMapping("/{cartId}")
//	public String requestCartList(@PathVariable(value="cartId") String cartId, Model model) {
//		// 웹 요청 uri가 /cart/cartid고 http 메서드가 get방식이면 매핑
//		cart Cart = cartservice.read(cartId);
//		// 요청 uri에서 경로변수 cartid에 대해 장바구니에 등록된 모든 정보 읽어옴 
//		model.addAttribute("Cart", Cart);
//		// 읽어온거 커맨드 객체 cart 속성에 등록
//		return "mypagecart";
//		//mypagecart.jsp 뷰 페이지 반환
//	}
//	
//	@PutMapping("/{cartId}")
//	public @ResponseBody cart read(@PathVariable(value="cartId")String cartId) {
//		// 웹 요청 uri가 cart/cartid고 http 메서드가 put방식이면 매핑
//		return cartservice.read(cartId);
//		// 요청 url에서 경로변수인 장바구니id에 대해 당바구니에 등록된 모든 정보 가져옴
//	}
//	
//	@PutMapping("/add/{cartId}")
//	@ResponseStatus(value=HttpStatus.NO_CONTENT)
//	//오류 응답 상태 코드 설정
//	public void addCartByNewItem(@PathVariable String productId, HttpServletRequest request) {
//		// 장바구니 id인 세션id 가져오기
//		String sessionId = request.getSession(true).getId();
//		cart Cart = cartservice.read(sessionId);
//		//장바구니에 등록된 모든 정보 얻어오기
//		if (Cart == null)
//		Cart = cartservice.create(new cart(sessionId));
//		// 경로변수 productId에 대한 정보 얻어옴
//		productDTO product = productservice.getProductById(productId);
//		if (product == null)
//		throw new IllegalArgumentException(new productIdException(productId));
//		// productId에 대한 상품 정보를 장바구니에 등록하기
//		Cart.addcartitem(new cartitem(product));
//		cartservice.update(sessionId, Cart);
//		//세션 id에 대한 장바구니 갱신하기
//	}
//	
//	@DeleteMapping("/{cartId}")
//	@ResponseStatus(value=HttpStatus.NO_CONTENT)
//	public void deleteCartList(@PathVariable(value="cartId") String cartId) {
//		cartservice.delete(cartId);
//		// uri가 /cart/cartId이고ㅓ http 메서드가 delete 방식일때 매핑되는 요청 처리 메서드
//		// 요청 url에서 경로변수인 장바구니 id에 대해 장바구니에 등록된 모든 정보 삭제
//	}
//	
//	
}
