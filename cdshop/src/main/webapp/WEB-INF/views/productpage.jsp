<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<link rel = "stylesheet" href="<c:url value="/resources/css/productpage.css" />">
    <script src="https://kit.fontawesome.com/014e61e9c4.js" crossorigin="anonymous"></script>
	<script>
		function addToCart(action){
			document.addForm.action = action;
			document.addForm.submit();
			alert("상품이 장바구니에 추가되었습니다!")
		}
	</script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container">
        <div class="bbubbu">
            <div class="cos_nav">
                <ul>
                    <li class = "com_name">
                        SHOP
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="lounge">
        <div class="midbox">
            <div class="sidecate">
                <div class="sidehea">
                    <span class="catetitle">CATEGORY</span>
                </div>
                <hr class="sidehr">
                <div class="listnamebox">
                    <span class="listname">DOG</span>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <li class="catelist">
                            <a class="cate" href="<c:url value="/shopmain/dogfood"/>"><span class="text">사료</span></a>
                        </li>
                        <li class="catelist">
                            <a class="cate" href="<c:url value="/shopmain/dogsnack"/>"><span class="text2">간식</span></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href="<c:url value="/shopmain/dogsup"/>"><span class="text3">용품</span></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href="<c:url value="/shopmain/dogtoy"/>"><span class="text4">장난감</span></a>
                        </li>
                    </ul>
                </div>
                <div class="listnamebox">
                    <span class="listname">CAT</span>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <li class="catelist">
                            <a class="cate" href="<c:url value="/shopmain/catfood"/>"><span class="text">사료</span></a>
                        </li>
                        <li class="catelist">
                            <a class="cate" href="<c:url value="/shopmain/catsnack"/>"><span class="text">간식</span></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href="<c:url value="/shopmain/catsup"/>"><span class="text">용품</span></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href="<c:url value="/shopmain/cattoy"/>"><span class="text">장난감</span></a>
                        </li>
                    </ul>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <div class="listnamebox">
                            <span class="listname">My Page</span>
                        </div>
                        <li class="catelist">
                            <a class="cate" href="<c:url value="/mypage/cart"/>"><span class="text">장바구니</span></a>
                        </li>
                        <li class="catelist">
                            <a class="cate" href="<c:url value="/mypage/order"/>"><span class="text">주문목록</span></a>
                        </li>
                    </ul>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <div class="listnamebox">
                            <span class="listname">고객센터</span>
                        </div>
                        <li class="catelist">
                            <a class="cate" href="<c:url value="/board/qna"/>"><span class="text">QnA</span></a>
                        </li>
                        
                    </ul>
                </div>
                
            </div>
        </div>
        <div class="productpage">
            <div class="productbox">
                <div class="probox">
                    <div class="proname">
                        <div class="productname">
                            <h2 class="name">${product.name}</h2>
                        </div>
                        <hr class="namehr">
                    </div>
                    <div class="protext">
                        <div class="proimg">
                            <img src="/resources/img/${product.tfilename }" alt="">
                        </div>
                        <div class="productex">
                            <div class="proexbox">
                                <h4 class="extitle">제품 요약 설명</h4>
                            </div>
                            <div class="procompany">
                                <span class="comname">${product.manufacturer }</span>
                            </div>
                            <div class="prodetailbox">
                                <div class="prodecontainer">
                                    <div class="promidtitle">
                                        <p>
                                            <strong>${product.titlement}</strong>
                                        </p>
                                    </div>
                                    <div class="prosmalltitle">
                                        <p>
                                            <span class="emogi">${product.simpledescription}</span>
                                         </p>
                                    </div>
                                    
                                </div>
                            </div>
                            <div class="propricebox">
                                <div class="propricecontainer">
                                    <p class="price">
                                        <span class="proprice">₩</span>${product.unitprice}
                                    </p>
                                </div>
                            </div>
                            <div class="quanbox">                                                
                                <div class="quan_inbox">
                                    수량 <input type="text"  value="1" class="it_quan" readonly>
                                </div>
                            </div>    
                            <hr class="quanboxhr">                    
                        </div>
                    </div>
                    <div class="cartbox">
	                    <form:form name="addForm" method="put">
	                        <div class="cartbt"><a href="javascript:addToCart('/cart/add/${product.productId}')"><span class="material-symbols-outlined">
	                            </span>장바구니</a></div>
	                    </form:form>
	                </div>
                    <div class="gardhr">
                        <hr class="gardline">
                    </div>
                    <div class="detailsbox">
                        <div class="detailphoto">
                            <div class="detailimg">
                                <img src="/resources/img/${product.dfilename }" alt="">
                            </div>
                        </div>
                    </div>
                    <div class="gardhr2">
                        <hr class="gardline2">
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>