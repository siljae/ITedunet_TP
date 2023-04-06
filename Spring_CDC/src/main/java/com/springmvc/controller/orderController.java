package com.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.domain.orderDTO;
import com.springmvc.service.orderService;

@Controller
@RequestMapping(value="/order")
public class orderController {
   @Autowired
   private orderService os;
   
   @RequestMapping
   public String requestOrder(@ModelAttribute orderDTO order, HttpSession session) {
      System.out.println("ordercontroller 들어왔늬");
      String name = (String) session.getAttribute("name");
      System.out.println("ordercontroller session name : " + name);
      order.setName(name);
      return "redirect:/order/add";
   }
   
   @GetMapping("/add")
   public String NewOrder(@RequestParam("list") ArrayList<String> list, @RequestParam("totalPrice") int totalPrice, orderDTO order, HttpSession session) {
      String name = (String) session.getAttribute("name");
      Date date = new Date();
      SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String today = f.format(date);
      order.setOrderdate(today);
      Date now = new Date();
      SimpleDateFormat odate = new SimpleDateFormat("yyyyMMdd");
      String oday = odate.format(now);
      String onum = "s"+oday+"_"+totalPrice;
      order.setOnum(onum);
      System.out.println(onum);
      for(int i = 0; i < list.size(); i++)
      {
    	  String productId = list.get(i);
    	  System.out.println(productId + " ==== 이거 들고 db갔다 올게");
    	  os.setNewOrder(productId, name, totalPrice, order, onum);
      }
      System.out.println("ordercontroller session name : " + name);
      System.out.println("ordercontroller add totalPrice : " + totalPrice);
      return "redirect:/order/list";
   }
   
   @RequestMapping("/list")
   public ModelAndView orderlist(HttpSession session, Model model) {
	  System.out.println("ordercontroller list 들어왔늬");
      String name = (String) session.getAttribute("name");
      ModelAndView mav = new ModelAndView();
      List<orderDTO> list = os.getAllOrderList(name);
      mav.addObject("orderlist", list);
      mav.setViewName("mypageorder");
      return mav;
   }
   
}