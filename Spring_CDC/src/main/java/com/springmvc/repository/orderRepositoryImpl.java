package com.springmvc.repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springmvc.domain.cartDTO;
import com.springmvc.domain.orderDTO;
import com.springmvc.mapper.cartRowMapper;
import com.springmvc.mapper.orderRowMapper;

@Repository
public class orderRepositoryImpl implements orderRepository {

   private JdbcTemplate template;
   private List<orderDTO> orderlist = new ArrayList<orderDTO>();
   
   @Autowired
   public void setJdbctemplate(DataSource dataSource) {
      this.template = new JdbcTemplate(dataSource);
   }
   
   public void setNewOrder(String productId, String name, int totalPrice, orderDTO order, String onum) {
      String selectsql = "select * from cart where p_id=?";
      cartDTO cart = template.queryForObject(selectsql, new cartRowMapper(), productId);
      String insertsql = "insert into buy (o_num, p_id, m_name, b_qnt, b_date, b_orderprice, p_tfilename, p_price) values(?,?,?,?,?,?,?,?) ";
      template.update(insertsql, order.getOnum(), productId, name, cart.getQuantity(), order.getOrderdate(), totalPrice, cart.getTfilename(), cart.getPrice());
      String deletesql = "delete from cart where p_id=? and m_name=?";
      template.update(deletesql, productId, name);
   }
   
   public List<orderDTO> getAllOrderList(String name){
      String SQL = "select * from buy where m_name=?";
      List<orderDTO> listOforder = template.query(SQL, new orderRowMapper(), name);
      this.orderlist = listOforder;
      return listOforder;
   }
   
   public orderDTO getMemberByNmae(String name) {
         orderDTO MemberByName = null;
         for(int i = 0; i<orderlist.size(); i++) {
            orderDTO order = orderlist.get(i);
            if(order != null && order.getName() != null && order.getName().equals(name)) {
               MemberByName = order;
               break;
            }
         }
         return MemberByName;
      }
}