package com.springmvc.service;

import java.util.List;

import com.springmvc.domain.cartDTO;

public interface cartService {
   
   void setNewCart(String productId, String name, int quantity);
   public List<cartDTO> getAllCartList(String name);
   public cartDTO getMemberByNmae(String m_name);
   void setDeleteCart(String productId);
   void setAllDeleteCart(String name);
   void setUpdateQnt(String productId, int quantity);
   
}