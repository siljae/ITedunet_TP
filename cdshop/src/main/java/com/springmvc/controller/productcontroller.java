package com.springmvc.controller;

import java.io.File;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.productDTO;
import com.springmvc.exception.CategoryException;
import com.springmvc.service.productService;

@Controller
@RequestMapping("/shopmain")
public class productcontroller {
	@Autowired
	productService ps;
	
//	@RequestMapping //main 화면
//	public String shopmain(Model model) {
//		List<productDTO> list = ps.getAllProductList();
//		model.addAttribute("productlist", list);
//		return "shopslide";
//	}
	
	@GetMapping("/all")//카테고리안 페이지 눌렀을때 화면
	public ModelAndView productlist(Model model) {
		ModelAndView modelandview = new ModelAndView();
		List<productDTO> list = ps.getAllProductList();
		for(productDTO dto : list) {
			System.out.println(dto.getProductId());
		}
		modelandview.addObject("productlist", list);
		modelandview.setViewName("shopslide");
		return modelandview;
	}
	
	@GetMapping("/productview")//상품상세
	public String productview(@RequestParam("id") String productId, Model model) {
		System.out.println("너 productview 들어옴?");
		productDTO productById = ps.getProductById(productId);
		System.out.println(productById);
		model.addAttribute("product", productById);
		return "productpage";
	}
	
	@GetMapping("/add")//상품추가
	public String requestAddproductForm(@ModelAttribute("NewProduct") productDTO product) {
		return "productadd";
	}
	
	@PostMapping("/add")//상품추가
	public String submitAddNewProduct(@Valid @ModelAttribute("NewProduct") productDTO product, BindingResult result) {
		System.out.println("path상품 추가 들어왔니?");
		if(result.hasErrors()) {
			System.out.println("add if문");
			return "productadd";
		}
		
		MultipartFile detailimage = product.getDetailimage();
		System.out.println("detailimage 넣었니" + detailimage);
		String saveName = detailimage.getOriginalFilename();
		System.out.println("originalname 넣었니" + saveName);
		File saveFile = new File("D:/KSH/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/cdshop/resources/img", saveName);
		System.out.println("파일 업로드에 들어갔니" + saveFile);
		if (detailimage != null && !detailimage.isEmpty()) {
			System.out.println("예외처리 if문");
			try {
				detailimage.transferTo(saveFile);
				product.setDfilename(saveName);
			}catch (Exception e) {
				throw new RuntimeException("상품 이미지 업도르가 실패하였습니다.", e);
			}
		}
		MultipartFile titleimage = product.getTitleimage();
		System.out.println("detailimage 넣었니" + titleimage);
		String saveName1 = titleimage.getOriginalFilename();
		System.out.println("originalname 넣었니" + saveName1);
		File saveFile1 = new File("D:/KSH/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/cdshop/resources/img", saveName1);
		System.out.println("파일 업로드에 들어갔니" + saveFile1);
		if (titleimage != null && !titleimage.isEmpty()) {
			System.out.println("예외처리 if문");
			try {
				titleimage.transferTo(saveFile1);
				product.setTfilename(saveName1);
			}catch (Exception e) {
				throw new RuntimeException("상품 이미지 업도르가 실패하였습니다.", e);
			}
		}
		
		ps.setNewProduct(product);
		System.out.println("product" + product);
		return "redirect:/shopmain/all";
	}
	
//	@GetMapping("/dogfood/{category}")
//	public String requestProductByCategory(@PathVariable("category") String productCategory, Model model) {
//		List<productDTO> productByCategory = ps.getProductListByCategory(productCategory);
//		if (productByCategory == null || productByCategory.isEmpty()) {
//			throw new CategoryException();
//		}
//		model.addAttribute("productList", productByCategory);
//		return "p_dogfood";
//	}
//	
	@GetMapping("/dogfood")
	public ModelAndView p_dogfood(Model model) {
		ModelAndView modelandview = new ModelAndView();
		List<productDTO> list = ps.getAllProductList();
		for(productDTO dto : list) {
			System.out.println(dto.getProductId());
		}
		modelandview.addObject("productlist", list);
		modelandview.setViewName("p_dogfood");
		return modelandview;
	}
	
	@GetMapping("/dogsnack")
	public ModelAndView p_dogsnack(Model model) {
		ModelAndView modelandview = new ModelAndView();
		List<productDTO> list = ps.getAllProductList();
		for(productDTO dto : list) {
			System.out.println(dto.getProductId());
		}
		modelandview.addObject("productlist", list);
		modelandview.setViewName("p_dogsnack");
		return modelandview;
	}
	
	@GetMapping("/dogsup")
	public ModelAndView p_dogsup(Model model) {
		ModelAndView modelandview = new ModelAndView();
		List<productDTO> list = ps.getAllProductList();
		for(productDTO dto : list) {
			System.out.println(dto.getProductId());
		}
		modelandview.addObject("productlist", list);
		modelandview.setViewName("p_dogsup");
		return modelandview;
	}
	
	@GetMapping("/dogtoy")
	public ModelAndView p_dogtoy(Model model) {
		ModelAndView modelandview = new ModelAndView();
		List<productDTO> list = ps.getAllProductList();
		for(productDTO dto : list) {
			System.out.println(dto.getProductId());
		}
		modelandview.addObject("productlist", list);
		modelandview.setViewName("p_dogtoy");
		return modelandview;
	}
	
	@GetMapping("/catfood")
	public ModelAndView p_catfood(Model model) {
		ModelAndView modelandview = new ModelAndView();
		List<productDTO> list = ps.getAllProductList();
		for(productDTO dto : list) {
			System.out.println(dto.getProductId());
		}
		modelandview.addObject("productlist", list);
		modelandview.setViewName("p_catfood");
		return modelandview;
	}
	
	@GetMapping("/catsnack")
	public ModelAndView p_catsnack(Model model) {
		ModelAndView modelandview = new ModelAndView();
		List<productDTO> list = ps.getAllProductList();
		for(productDTO dto : list) {
			System.out.println(dto.getProductId());
		}
		modelandview.addObject("productlist", list);
		modelandview.setViewName("p_catsnack");
		return modelandview;
	}
	
	@GetMapping("/catsup")
	public ModelAndView p_catsup(Model model) {
		ModelAndView modelandview = new ModelAndView();
		List<productDTO> list = ps.getAllProductList();
		for(productDTO dto : list) {
			System.out.println(dto.getProductId());
		}
		modelandview.addObject("productlist", list);
		modelandview.setViewName("p_catsup");
		return modelandview;
	}
	
	@GetMapping("/cattoy")
	public ModelAndView p_cattoy(Model model) {
		ModelAndView modelandview = new ModelAndView();
		List<productDTO> list = ps.getAllProductList();
		for(productDTO dto : list) {
			System.out.println(dto.getProductId());
		}
		modelandview.addObject("productlist", list);
		modelandview.setViewName("p_cattoy");
		return modelandview;
	}
}
