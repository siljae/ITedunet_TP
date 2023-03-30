<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/resources/css/board.css"/>">
	<title>커뮤니티 게시판</title>
</head>
<body>
	<jsp:include page="./header.jsp"/>
	<div class="com_name">
		<h1><a href="<c:url value="/notice"/>" style="color: #090909; border-bottom: 4px solid #fcd11e;">공지사항</a></h1>
		<h1><a href="<c:url value="/notice/event"/>">이벤트</a></h1>
    </div>
    <div class="container">
        <div class="midbox">
            <div class="seabox">
                <div class="search">
                	<form action="<c:url value="/board/search"/>"  method="post" style="width:70%">
	                    <input type="text" name="content" placeholder="찾으시는 글이 있으신가요?" maxlength="130" class="com_search" enterkeyhint="search" value="">
	                    <button class="button" type="submit" >
	                        <img src="<c:url value="/resources/img/seabut.png"/>" alt="search">
	                    </button>
                    </form>
                </div>
            </div>
        </div>
        <hr class="borderline">
        <div class="loungebox">
            <div class="filterbox">
                <div class="writebox">
                    <a href="./commuwrite.action" class="write">글쓰기</a>
                </div>
            </div>
            <div class="loungelist">
                <div class="conlist">
                	<c:forEach items="${boardlist }" var="board">
                    <div class="content">
                    	<div class="content_2">
                    		<div>
		                        <div class="colist">
	                                <div>
		                                <a href="./view?num=${board.num }&pageNum=" class="coltitle">${board.title }</a>
		                                <a href="./view?num=${board.num }&pageNum=" class="coltext">${board.content }</a>	                                
		                            </div>
	                            </div>
	                       	    <div class="coreply">
		                            <div class="renickname">${board.name }</div>
		                            <div class="retime">조회수 : ${regist_day }</div>
		                        </div>
	                        </div>
                        	<c:if test="${board.filename != null }">
		                        <div class="colbox">
		                        	<a href="./view?num=${board.num }&pageNum=" class="imgbox">
										<img class="listimg" src="<c:url value="/resources/img/${board.filename }"/>">
									</a>
	                        	</div>	
                        	</c:if>                     
                        </div>                        
                        <hr class="listgard">
                    </div>
					</c:forEach>
                    <div class="content">
                    	<div class="content_2">
                    		<div>
		                        <div class="colist">
	                                <div>
		                                <a href="./notice/view?num=${board.num }&pageNum=" class="coltitle">SHOP 설날 배송 택배사 공지${board.title }</a>
		                                <a href="./notice/view?num=${board.num }&pageNum=" class="coltext">해피 뉴 이어 !${board.content }</a>	                                
		                            </div>
	                            </div>
	                       	    <div class="coreply">
		                            <div class="renickname">${board.name }관리자</div>
		                            <div class="retime">30일전 ${regist_day }</div>
		                        </div>
	                        </div>  
                        </div>                        
                        <hr class="listgard">
                    </div>
                </div>
            </div>
        </div>
        <ul class="pagebutton">
            <li class="leftbt">
                <a href="#" aria-label="Go to befor page">‹</a>
            </li>
            <li class="active">
                <a class="undefined" href="#" aria-label="Go to page number 1">1</a>
            </li>
            <li class>
                <a href="#" aria-label="Go to page number 2">2</a>
            </li>
            <li class>
                <a href="#" aria-label="Go to page number 3">3</a>
            </li>
            <li class>
                <a href="#" aria-label="Go to page number 4">4</a>
            </li>
            <li class>
                <a href="#" aria-label="Go to page number 5">5</a>
            </li>
            <li class>
                <a href="#" aria-label="Go to after number ">›</a>
            </li>
        </ul>
    </div>
	<jsp:include page="footer.jsp"/>
</body>
</html>