package com.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springmvc.domain.cart;
import com.springmvc.domain.cartitem;
import com.springmvc.domain.productDTO;
import com.springmvc.exception.productIdException;
import com.springmvc.service.cartService;
import com.springmvc.service.productService;

@Controller
@RequestMapping(value="/cart")
public class cartController {
	@Autowired
	private cartService cartservice;
	@Autowired
	private productService productservice;
	
	@GetMapping
	public String requestCartId(HttpServletRequest request) {
		// 웹요청 /cart일때 요청처리 메서드
		String sessionid = request.getSession(true).getId();
		// 세션 id 값 가져옴
		return "redirect:/cart" + sessionid;
		// 세션 id 값 uri cart/sessionid로 리다이렉션함
	}
	
	@PostMapping
	public @ResponseBody cart create(@RequestBody cart Cart) {
		// 웹 요청 uri가 /cart고 http메서드가 post방식이면 매핑 (사용자 요청 처리)
		return cartservice.create(Cart);
		//cart 클래스 정보를 http요청 body로 전달받아 장바구니를 새로 생성하고 http 응답 body로 전달
	}
	
	@GetMapping("/{cartId}")
	public String requestCartList(@PathVariable(value="cartId") String cartId, Model model) {
		// 웹 요청 uri가 /cart/cartid고 http 메서드가 get방식이면 매핑
		cart Cart = cartservice.read(cartId);
		// 요청 uri에서 경로변수 cartid에 대해 장바구니에 등록된 모든 정보 읽어옴 
		model.addAttribute("Cart", Cart);
		// 읽어온거 커맨드 객체 cart 속성에 등록
		return "mypagecart";
		//mypagecart.jsp 뷰 페이지 반환
	}
	
	@PutMapping("/{cartId}")
	public @ResponseBody cart read(@PathVariable(value="cartId")String cartId) {
		// 웹 요청 uri가 cart/cartid고 http 메서드가 put방식이면 매핑
		return cartservice.read(cartId);
		// 요청 url에서 경로변수인 장바구니id에 대해 당바구니에 등록된 모든 정보 가져옴
	}
	
	@PutMapping("/add/{cartId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	//오류 응답 상태 코드 설정
	public void addCartByNewItem(@PathVariable String productId, HttpServletRequest request) {
		// 장바구니 id인 세션id 가져오기
		String sessionId = request.getSession(true).getId();
		cart Cart = cartservice.read(sessionId);
		//장바구니에 등록된 모든 정보 얻어오기
		if (Cart == null)
		Cart = cartservice.create(new cart(sessionId));
		// 경로변수 productId에 대한 정보 얻어옴
		productDTO product = productservice.getProductById(productId);
		if (product == null)
		throw new IllegalArgumentException(new productIdException(productId));
		// productId에 대한 상품 정보를 장바구니에 등록하기
		Cart.addcartitem(new cartitem(product));
		cartservice.update(sessionId, Cart);
		//세션 id에 대한 장바구니 갱신하기
	}
	
	@DeleteMapping("/{cartId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteCartList(@PathVariable(value="cartId") String cartId) {
		cartservice.delete(cartId);
		// uri가 /cart/cartId이고ㅓ http 메서드가 delete 방식일때 매핑되는 요청 처리 메서드
		// 요청 url에서 경로변수인 장바구니 id에 대해 장바구니에 등록된 모든 정보 삭제
	}
	
	
}
