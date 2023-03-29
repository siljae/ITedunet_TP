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
    
    function chkForm(){
    	let name = "${name}";
    	const ischk = document.getElementById('allproduct').checked;
    	if(ischk == true){
    		return location.href="./alldelete?name="+name;
    	}
    	else {
    		alert("전체체크를 해주세요")
    			return false;
    	}
    }
    
    function deleteConfirm(id){
    	if(confirm("삭제합니다 !") == true) location.href="./delete?id="+id;
    	else return;
    }
    
    $(document).ready(function(){
    	setTotalInfo();
    });
    
    $("allproduct").on("change", function(){
    	setTotalInfo($(".product_list"))
    })
    
    function setTotalInfo(){
    	let totalprict = 0;
    	let totalcount = 0;
    	let delivertprice = 0;
    	let finaltotalprict = 0;
    	
    	$("allproduct").each(function(index, element){
    		totalprice += perseInt($(element).find(".totalprice_input").val());
    		totalcount += parseInt($(element).find(".totalcount_input").val());
    	});
    	
    	if(totalprice == 0){
    		deliveryprice = 0;
    	} else {
    		deliveryprice = 2500;
    	}
    	finaltotalprice = totalprice + deliveryprice;
    	
    	$(".totalprice_span").text(totalprice.toLocaleString());
    	$(".delivary_span").text(delivaryprice);
    	$(".finaltotalprice_span").text(finaltotalprice.toLocaleString());
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
                                 <a href="<c:url value = "javascript:chkForm()"/>">전체삭제</a>
                           </div> 
                       </p>
                    </div>
                    <c:forEach items="${cartlist}" var="item">
                    <div>
                            <div class="product_list">
                                <%-- <form:form name="removeForm" method="put">     
                                </form:form> --%>                        
                                    <div class="product_img">
                                        <img src="./img/cart/${item.tfilename}" alt="product">
                                    </div>
                                    <div class="product_desc">
                                        <div class="product_desc_t">
                                            <h3>${item.productId}</h3>
                                            <input type="checkbox" id="allchk" name="chk" onclick="chkselectall()">
                                        </div>
                                        <div class="product_desc_b">
                                            <p> 금액 : ${item.price}</p>
                                            <div class="quanbox">                                                
                                               <p> 수량 : </p>
                                                <div class="quan_inbox">
                                                    <input type="number"  value="${item.quantity}" class="it_quan" required>
                                                    <input type="hidden" class="totalcount_input" value="${item.quantity}">
                                                </div>
                                                <div class="allprice">
                                                	합계:<p class="sumprice"> ${item.price * item.quantity}</p> 원
                                                	<input type="hidden" class="totalprice_input" value="${item.price * item.quantity}">
                                                </div>
                                            </div>                                           
                                            <div class="quandel"><a href="<c:url value = "javascript:deleteConfirm('${item.productId}')"/>">삭제</a></div>    
                                        </div>
                                    </div>
                                </div>
                            </div>
                                <div class="hr"></div>
                      </c:forEach>
                          
                        <div class="product_sum">
                            <div>
                                총 상품가격 <span class="totalprice_span"></span>원
                            </div> 
                            <i class="far fa-plus-square"></i>
                            <p>
                                총 배송비 <span class="delivary_span"></span>원
                            </p>
                            <i class="fas fa-equals"></i>
                            <p>
                                총 주문 금액 <span class="finaltotalprice_span"></span>원
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
</html>