package com.springmvc.repository;

import java.util.List;

import com.springmvc.domain.cartDTO;

public interface cartRepository {
   
   void setNewCart(String productId, String name, int quantity);
   public List<cartDTO> getAllCartList(String name);
   public cartDTO getMemberByNmae(String m_name);
   void setDeleteCart(String productId);
   void setAllDeleteCart(String name);
   void setUpdateQnt(String productId, int quantity);
   
//   cart create(cart Cart);
//   cart read(String cartId);
//   void update(String cartId, cart Cart);

}