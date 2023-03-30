package com.springmvc.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springmvc.domain.cartDTO;
import com.springmvc.domain.productDTO;

@Repository
public class cartRepositoryImpl implements cartRepository {
   
   private JdbcTemplate template;
   private List<cartDTO> cartlist = new ArrayList<cartDTO>();
   
   @Autowired
   public void setJdbctemplate(DataSource dataSource) {
      this.template = new JdbcTemplate(dataSource);
   }
   
   
   public void setNewCart(String productId, String name, int quantity)
   {
      String selectSql = "select * from product where p_id=?";
      productDTO product = template.queryForObject(selectSql, new productRowMapper(), productId);
      String insertSql = "insert into cart (m_name, p_id, p_unitprice, ca_qnt, p_tfilename) values(?, ?, ?, ?, ?) on duplicate key update m_name=?, p_id=?, p_unitprice=?, ca_qnt=ca_qnt+?, p_tfilename=?";
      template.update(insertSql, name, product.getName(), product.getUnitprice(), quantity, product.getTfilename(), name, product.getName(), product.getUnitprice(), quantity, product.getTfilename());
   }
   
   public List<cartDTO> getAllCartList(String name){
      String SQL = "select * from cart where m_name=?";
      List<cartDTO> listOfcart = template.query(SQL, new cartRowMapper(), name);
      this.cartlist = listOfcart;
      return listOfcart;
   }
   
   public cartDTO getMemberByNmae(String name) {
      cartDTO MemberByName = null;
      for(int i = 0; i<cartlist.size(); i++) {
         cartDTO cart = cartlist.get(i);
         if(cart != null && cart.getName() != null && cart.getName().equals(name)) {
            MemberByName = cart;
            break;
         }
      }
      return MemberByName;
   }
   
   public void setDeleteCart(String productId) {
	   String sql = "delete from cart where p_id =?";
	   this.template.update(sql, productId);
   }
   
   public void setAllDeleteCart(String name) {
	   String sql = "delete from cart where m_name =?";
	   this.template.update(sql, name);
   }
   
   
//   public cart read(String cartId) {
//      //장바구니 id 이용해서 장바구니에 등록된 모든 정보 가져와 반환
//      System.out.println("cartrepository - 카트 정보 반환하니?");
//      
//      return listOfCarts.get(cartId);
//   }
//   
   
//   public void update(String cartId, cart Cart) {
//      if (!listOfCarts.keySet().contains(cartId)) {
//         throw new IllegalArgumentException(String.format("장바구니 목록을 갱신할 수 없습니다. 장바구니 id(%)가 존재하지 않습니다.", cartId));
//         // 장바구니 id가 존재하지 않을 경우 예외처리
//      }
//      listOfCarts.put(cartId, Cart);
//   }
//   
//   public void delete(String cartId) {
//      if(!listOfCarts.keySet().contains(cartId)) {
//         //장바구니 id가 존재하지 않으면 예외처리
//         throw new IllegalArgumentException(String.format("장바구니 목록을 삭제할 수 없습니다. 장바구니 id(%)가 존재하지 않습니다", cartId));
//      }
//      listOfCarts.remove(cartId);
//   }
}