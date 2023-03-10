<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/css/Mypagecart.css"/>">
<script src="https://kit.fontawesome.com/014e61e9c4.js" crossorigin="anonymous"></script>
<title>마이페이지 - 장바구니</title>
<script>
    function selectall(selectall){
        const checkboxes = document.getElementsByName('chk');
        checkboxes.forEach((checkbox) =>{checkbox.checked = selectall.checked;} )
    }
    function chkselectall()  {
        // 전체 체크박스
        const checkboxes 
            = document.querySelectorAll('input[name="chk"]');
        // 선택된 체크박스
        const checked 
            = document.querySelectorAll('input[name="chk"]:checked');
        // select all 체크박스
        const selectAll 
            = document.querySelector('input[name="allchk"]');
        
        if(checkboxes.length === checked.length)  {
            selectAll.checked = true;
        }else {
            selectAll.checked = false;
        }
    }
    function removeFromCart(action){
    	document.removeForm.action = action;
    	document.removeForm.submit();
    	window.location.reload();
    }
    function clearCart(){
    	document.clearForm.submit();
    	window.location.reload();
    }
</script>
</head>
<body>
	<jsp:include page="./header.jsp"/>
	<section>
        <div class="cart_head">
            <h1>장바구니</h1>
        </div>
        <div class="container">
            <div class="mypagebox">
                <ul class="my_left">
                    <li class="my_left_li1">
                        <h2><a href="<c:url value="/mypage"/>">MyPage</a></h2>
                    </li>
                    <li class="my_left_li2">
                        <a href="<c:url value="/mypage/barrier"/>">
                            <label for="msb1">
                                <span>개인정보수정</span>
                                <i class="fas fa-chevron-right"></i>
                            </label>
                        </a>
                    </li>
                    <li class="my_left_li3">
                        <a href="<c:url value="/mypage/order"/>">
                            <label for="msb2">
                                <span>주문목록</span>
                                <i class="fas fa-chevron-right"></i>
                            </label>
                        </a>
                    </li>
                    <li class="my_left_li4">
                        <a href="<c:url value="/cart"/>">
                            <label for="msb3">
                                <span>장바구니</span>
                                <i class="fas fa-chevron-right"></i>
                            </label>
                        </a>
                    </li>
                    <li class="my_left_li5">                            
                        <a href="<c:url value="/mypage/chat"/>">
                            <label for="msb4">
                                <span>1:1 채팅창</span>
                                <i class="fas fa-chevron-right"></i>
                            </label>
                        </a>
                    </li>
                </ul>
                <div class="my_right">
                    <div class ="mr2">
	                    <p class="allchkbox">
	                        <label for="allproduct">전체선택</label>
	                        <input type="checkbox" id="allproduct" name="allchk" onclick="selectall(this)">
	                        <div class="quandel quandel2">
	                        	<form:form name="clearForm" method="delete">
	                        		<a href="clearForm()">전체삭제</a>
	                        	</form:form>
	                        </div> 
	                    </p>
                    </div>
                    <div>
                        <ul>
                            <li>
                                <div class="product_list">
                                <form:form name="removeForm" method="put">
                                <c:forEach items="${cart.cartitems}" var="item">                            
                                    <div class="product_img">
                                        <img src="./img/cart/product2.png" alt="product">
                                    </div>
                                    <div class="product_desc">
                                        <div class="product_desc_t">
                                            <h3>${item.value.product.productId}-${item.value.product.name}</h3>
                                            <input type="checkbox" id="allchk" name="chk" onclick="chkselectall()">
                                        </div>
                                        <div class="product_desc_b">
                                            <p> 금액 : ${item.value.product.unitprice}</p>
                                            <div class="quanbox">                                                
                                                수량 : ${item.value.product.quantity}
                                                <div class="quan_inbox">
                                                    <button onclick="fncalcount('p',this)" class="it_quan_p">+</button>
                                                    <input type="text"  value="1" class="it_quan" required>
                                                    <button onclick="fncalcount('m',this)" class="it_quan_m">-</button>
                                                </div>
                                            </div>                                           
                                            <div class="quandel"><a href="javascript:removeFromCart('/cart/remove/${item.value.product.productId}')">삭제</a></div>    
                                        </div>
                                        </c:forEach>
                                        </form:form>                                                                            
                                    </div>
                                </div>
                                <div class="hr"></div>
                            </li>
                            <!-- 지워도됨 예시임 -->
                            <li>
                                <div class="product_list">                            
                                    <div class="product_img">
                                        <img src="./img/cart/product2.png" alt="product">
                                    </div>
                                    <div class="product_desc">
                                        <div class="product_desc_t">
                                            <h3>상품명 : @1234</h3>
                                            <input type="checkbox" id="allchk" name="chk" onclick="chkselectall()">
                                        </div>
                                        <div class="product_desc_b">
                                            <p>상품금액 : 10,000원</p>
                                            <div class="quanbox">                                                
                                                수량 :
                                                <div class="quan_inbox">
                                                    <button onclick="fncalcount('p',this)" class="it_quan_p">+</button>
                                                    <input type="text"  value="1" class="it_quan" required>
                                                    <button onclick="fncalcount('m',this)" class="it_quan_m">-</button>
                                                </div>
                                            </div>                                            
                                            <div class="quandel"><a href="javascript:removeFromCart('/cart/remove/${item.value.product.productId}')">삭제</a></div>  
                                        </div>                                                                            
                                    </div>
                                </div>
                                <div class="hr"></div>
                            </li>
                        </ul>                        
                        <div class="product_sum">
                            <p>
                                총 상품가격 <span>${Cart.grandTotal}</span>원
                            </p> 
                            <i class="far fa-plus-square"></i>
                            <p>
                                총 배송비 <span>2500</span>원
                            </p>
                            <i class="fas fa-equals"></i>
                            <p>
                                총 주문 금액 <span>12,500</span>원
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>