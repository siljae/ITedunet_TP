<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/css/MyPageorder.css"/>">
<script src="https://kit.fontawesome.com/014e61e9c4.js" crossorigin="anonymous"></script>
<title>마이페이지 주문목록</title>
<script>
    function list_open()
    {
        var show = document.getElementById("list");
        if(show.style.display=='none'){
            show.style.display = 'block';
        }
        else{
            show.style.display = 'none';
        }
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
                        <a href="<c:url value="/mypage/cart"/>">
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
                    <div>
                        <div class="my_r_top">
                            <h2>주문번호 : </h2>
                            <p>상품명 @1234 외 n개</p>
                            <p>주문일자 : </p>
                            <p>총 주문금액 : </p>
                            <button onclick="list_open()">상세정보</button>
                        </div>
                        <div class="hr"></div>
                        <ul id="list">
                            <h2>주문 상세 정보</h2>
                            <li>
                                <div class="product_list">                            
                                    <div class="product_img">
                                        <img src="./img/cart/product2.png" alt="product">
                                    </div>
                                    <div class="product_desc">
                                        <div class="product_desc_t">
                                            <h3>상품명 : @1234</h3>
                                        </div>
                                        <div class="product_desc_b">
                                            <p>상품금액 : 10,000원</p>
                                            <div class="quanbox">                                                
                                                수량 :
                                                <div class="quan_inbox">
                                                    <input type="text"  value="1" class="it_quan" readonly>
                                                </div>
                                            </div>                                            
                                        </div>                                                                            
                                    </div>
                                </div>
                            </li>
                            <div class="hr"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>