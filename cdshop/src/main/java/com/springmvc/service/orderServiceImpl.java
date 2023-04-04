package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.orderDTO;
import com.springmvc.repository.orderRepository;

@Service
public class orderServiceImpl implements orderService {
   @Autowired
   private orderRepository or;
   
   public void setNewOrder(String productId, String name, int totalPrice, orderDTO order, String onum) {
	   System.out.println("orderservice setNewOrder 들어왔댜"); 
	   System.out.println("orderservice - name :" + name);
       or.setNewOrder(productId, name, totalPrice, order, onum);
   }
   
   public List<orderDTO> getAllOrderList(String name){
	   System.out.println("orderservice AllOrderList 들어왔댜"); 
       return or.getAllOrderList(name);
   }
   
   public orderDTO getMemberByName(String name) {
	  System.out.println("orderservice MemberByName 들어왔댜"); 
      orderDTO MemberByName = or.getMemberByNmae(name);
      return MemberByName;
   }
   	
}