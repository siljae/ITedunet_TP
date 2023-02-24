<%@page import="java.util.*"%>
<%@page import="shopmodel.productDAO"%>
<%@page import="shopmodel.productDTO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	List<productDTO> productList = (List) request.getAttribute("productlist");
	System.out.print("11"+productList);
	int total_record = ((Integer) request.getAttribute("total_record")).intValue();
	int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
	int total_page = ((Integer) request.getAttribute("total_page")).intValue();
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<link rel = "stylesheet" href="./css/shopslide.css">
<meta charset="utf-8">
<title>Insert title here</title>
<style>
	
</style>
</head>
<body>
	<jsp:include page="header.jsp"/>
	<div class="container1">
        <div class="bbubbu" id="a">
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
                            <a class="cate" href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text">사료</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate" href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text2">간식</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text3">용품</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text4">장난감</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                    </ul>
                </div>
                <div class="listnamebox">
                    <span class="listname">CAT</span>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <li class="catelist">
                            <a class="cate" href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text">사료</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate" href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text">간식</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text">용품</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                        <li class="catelist">
                            <a class="cate"href=""><img src="./img/foot-removebg-preview.png" alt=""><span class="text">장난감</span><img src="./img/foot-removebg-preview.png" alt=""></a>
                        </li>
                    </ul>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <div class="listnamebox">
                            <span class="listname">My Page</span>
                        </div>
                        <li class="catelist">
                            <a class="cate" href=""><span class="text">장바구니</span></a>
                        </li>
                        <li class="catelist">
                            <a class="cate" href=""><span class="text">주문목록</span></a>
                        </li>
                    </ul>
                </div>
                <div class="listtitle">
                    <ul class="list">
                        <div class="listnamebox">
                            <span class="listname">고객센터</span>
                        </div>
                        <li class="catelist">
                            <a class="cate" href=""><span class="text">QnA</span></a>
                        </li>
                        
                    </ul>
                </div>
                
            </div>
        </div>
        <div class="shoplistmid">
            <div class="shoplounge">
                <section>
                    <div class="container">
                        <div class="slide_banner">
                            <input type="radio" id="slide01" name="slide" checked>
                            <input type="radio" id="slide02" name="slide">
                            <input type="radio" id="slide03" name="slide">
                            <div class="slide_control">
                                <label for="slide01">1</label>
                                <label for="slide02">2</label>
                                <label for="slide03">3</label>
                            </div>
            
                            <div class="slide_content">
                                <div class="slide_cont">
                                    <a href="#"><img src="./img/index/holong1.jpg" alt="slide01"></a>
                                    <a href="#"><img src="./img/index/holong2.jpg" alt="slide02"></a>
                                    <a href="#"><img src="./img/index/holong3.jpg" alt="slide03"></a>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <div class="alllistbox">
                <%
                	for(int i=0; i<productList.size();i++){
                		System.out.println("pro" + productList.get(i));
                		productDTO dt = new productDTO();
                		dt = productList.get(i);
                		
                		System.out.println("이름" + dt.getTitlefilename());
                	
                
                %>
                    <div class="listbox">
                        <a class="prodetail"href="productview.go"></a>
                        <div class="productbox">
	                        <a class="prodetail2" href="productview.go">
	                            <span class="proimg"><img src="./resources/img/<%=dt.getTitlefilename() %>" alt=""></span>
	                            <span class="procompany"><%=dt.getManufacturer() %></span>
	                            <div class="proname"><%=dt.getName() %></div>
	                            <hr class="prohr">
	                            <div class="proprice">\ <%=dt.getUnitprice() %>원</div>
	                            <input type="hidden" class="donnkow" value="">
	                        </a>
	                        <div class="cartbt"><a href="">장바구니</a></div>
                        </div>
                    </div>
                   <%
               	}
                   %>
                </div>
            </div>

        </div>
    </div>
   
</body>
</html>