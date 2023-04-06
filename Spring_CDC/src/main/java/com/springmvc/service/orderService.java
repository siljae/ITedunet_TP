package com.springmvc.service;

import java.util.List;

import com.springmvc.domain.orderDTO;

public interface orderService {
   void setNewOrder(String productId, String name, int totalPrice, orderDTO order, String onum);
   public List<orderDTO> getAllOrderList(String name);
   public orderDTO getMemberByName(String name);
}