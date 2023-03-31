package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.domain.cartDTO;
import com.springmvc.repository.cartRepository;
@Service
public class cartServiceImpl implements cartService {
   @Autowired
   private cartRepository cr;
   
//   public void setNewCart(cartDTO cart) {
//      cr.setNewCart(cart);
//   };
   public void setNewCart(String productId, String name, int quantity)
   {
      cr.setNewCart(productId, name, quantity);
   }
   
   public List<cartDTO> getAllCartList(String name){
      return cr.getAllCartList(name);
   }
   
   public cartDTO getMemberByNmae(String m_name) {
      cartDTO MemberByName = cr.getMemberByNmae(m_name);
      return MemberByName;
   }
   
   public void setDeleteCart(String productId) {
	   cr.setDeleteCart(productId);
   }
   
   public void setAllDeleteCart(String name) {
	   cr.setAllDeleteCart(name);
   }
   
   public void setUpdateQnt(String productId, int quantity) {
	  
   }
   
}