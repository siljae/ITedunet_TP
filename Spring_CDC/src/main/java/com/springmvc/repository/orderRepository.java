package com.springmvc.repository;

import java.util.List;

import com.springmvc.domain.orderDTO;

public interface orderRepository {
   void setNewOrder(String productId, String name, int totalPrice, orderDTO order, String onum);
   public List<orderDTO> getAllOrderList(String name);
   public orderDTO getMemberByNmae(String name);
}