<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/resources/css/board.css"/>">
	<title>커뮤니티 게시판</title>
	<!-- <script type="text/javascript">
		function searchForm(){
			let content = document.getElementById('content').value;
			location.href = '<c:url value="/board/search/content"/>';
		}
	</script> -->
</head>
<body>
	<jsp:include page="./header.jsp"/>
	<div class="com_name">
		<h1><a href="<c:url value="/board/${pageNum }"/>" style="color: #090909; border-bottom: 4px solid #fcd11e;">전체</a></h1>
		<h1><a href="<c:url value="/board/commu/${pageNum}"/>">자랑해요</a></h1>
		<h1><a href="<c:url value="/board/qna"/>">Q&A</a></h1>
		<h1><a href="<c:url value="/board/recom"/>">추천해요</a></h1>
    </div>
    <div class="container">
        <div class="midbox">
            <div class="seabox">
                <div class="search">
                <!-- onsubmit="searchForm()" -->
                	<form action="<c:url value="/board/search"/>"  method="post" style="width:70%">
	                    <input type="text" name="content" placeholder="찾으시는 글이 있으신가요?" maxlength="130" class="com_search" enterkeyhint="search" value="">
	                    <button class="button" type="submit" >
	                        <img src="<c:url value="/resources/img/seabut.png"/>" alt="search">
	                    </button>
                    </form>
                </div>
                <div class="textbox" id="hotline">인기글</div>
                <div class="bestlist">
                    <div class="bestbox">
                    	<c:forEach items="${recomlist }" var="recomboard">
	                        <div class="list1">
	                            <div class="listlabel">
	                                <div class="labelname">
	                                    <img class="catface" src="<c:url value="/resources/img/board/${recomboard.tag_src }"/>">
	                                    <div class="cattext">${recomboard.tag_value }
	                                    </div>
	                                </div>
	                                <div class="listhead">
	                                    ${recomboard.title }
	                                </div>
	                                <div class="listtext">
	                                    ${recomboard.content }
	                                </div>
	                                <div class="reply">
	                                    <span class="re1">
	                                        답변 
	                                        3
	                                    </span>
	                                    <span class="day">${recomboard.calregist }</span>
	                                </div>
	                                <div class="listgo">
	                                    <a href="<c:url value="/board/commu/view/${pageNum }/${recomboard.num }"/>" class="gobutton">바로가기</a>
	                                </div>
	                            </div>
	                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
	</div>
    <hr class="borderline">
    <div class="container">
        <div class="loungebox">
            <div class="filterbox">
                <div class="filterbt">
                    <div class="radiobox">
                        <input class="radiobt" type="radio" id="newest" name="question" value="최신순" checked>
                        <label for="newest">최신순</label>
                    </div>
                </div>
                <div class="filterbt">
                    <div class="radiobox">
                        <input class="radiobt" type="radio" id="popular" name="question" value="인기순">
                        <label for="popular">인기순</label>
                    </div>
                </div>
                <div class="filterbt">
                    <div class="radiobox">
                        <input class="radiobt" type="radio" id="views" name="question" value="조회순">
                        <label for="views">조회순</label>
                    </div>
                </div>
                <c:if test="${name != null }">
	                <div class="writebox">                	
	                   	<a href="<c:url value="/board/boardwrite"/>" class="write">글쓰기</a>                    
	                </div>
                </c:if>
            </div>
            <div class="loungelist">
                <div class="conlist">
                	<c:forEach items="${boardlist }" var="board">
                    <div class="content">
                    	<div class="content_2">
                    		<div>
		                        <div class="colist">
		                        	<div class="colup">
		                        		<div class="colut">${board.board_type }</div>
		                                <div class="colbt">
		                                    <img src="<c:url value="/resources/img/board/${board.tag_src }" />" alt="아이콘" >
		                                    <div class="cattext1">${board.tag_value }</div>
		                                </div>
		                            	<div class="colhit">조회수 : ${board.hit }</div>
	                                </div>
	                                <div>
		                                <a href="<c:url value="/board/commu/view/${pageNum }/${board.num }"/>" class="coltitle">${board.title }</a>
		                                <a href="<c:url value="/board/commu/view/${pageNum }/${board.num }"/>" class="coltext">${board.content }</a>	                                
		                            </div>
	                            </div>
	                       	    <div class="coreply">
		                            <div class="core1">
		                                답변 : 
		                            </div>
		                            <div class="renickname">${board.name }</div>
		                            <div class="retime">조회수 : ${board.calregist }</div>
		                        </div>
	                        </div>
                        	<c:if test="${board.filename != null && !empty board.filename}">
		                        <div class="colbox">
		                        	<a href="<c:url value="/commu/view/${pageNum }/${board.num }"/>" class="imgbox">
										<img class="listimg" src="<c:url value="/resources/img/board/${board.filename }"/>">
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
		                        	<div class="colup">
		                                <div class="colbt">
		                                    <img src="<c:url value="/resources/img/board/dogface.png" />" alt="아이콘" >
		                                    <div class="cattext1">강아지</div>
		                                </div>
		                            	<div class="colhit">조회수 : 0</div>
	                                </div>
	                                <div>
		                                <a href="./commuboardview.action?num=${board.num }&pageNum=" class="coltitle">반려동물 침대에서 함께자나요?</a>
		                                <a href="./commuboardview.action?num=${board.num }&pageNum=" class="coltext">안녕하세요. 3년차 강아지 키우고 있는 아빠입니다. 처음 데려올 땐 "사람과 동물이 어디 한 침대야~" 라며 절대불가를 외쳤지만 지금은 잘때 한 방에 없으면 서운하고 꼭  불러서 침대로 오게 만드는 라이프를 살고 있습니다. 워낙 첨엔 숙면을 잘 못취하기도 하고, 불편했는데 그냥 매번 올라오는 강아지를 떨쳐낼 수가 없어 고민을 해결하다 못해 이런 가구는 어떨까 하며 여기까지 생각이 오게 되었습니다 ^^ 그냥 거창한 설문 조사는 아니고, 주변 반려인들께서는 어떤 잠자리를 하고 계실까 다른 반려동물들은 어떻게 침대에서 생활할까 궁금하기도 하고 자료가 필요하기도 하여 서투른 질문지들을 작성하여 이렇게 요청을 드립니다. 가벼운 마음으로, 작성해주시면 정말 감사하겠습니다. 추첨 통해 스타벅스 기프티콘 드려요! </a>	                                
		                            </div>
	                            </div>
	                       	    <div class="coreply">
		                            <div class="core1">
		                                답변 : 0
		                            </div>
		                            <div class="renickname">봉봉</div>
		                            <div class="retime">2시간전</div>
		                        </div>
	                        </div>
                        	<%-- <c:if test="${board.filename != null }"> --%>
		                        <div class="colbox">
		                        	<a href="./commuboardview.action?num=${board.num }&pageNum=" class="imgbox">
										<img class="listimg" src="<c:url value="/resources/img/ggimu.jpg"/>">
									</a>
	                        	</div>	
                        	<%-- </c:if> --%>                     
                        </div>                        
                        <hr class="listgard">
                    </div>
                </div>
            </div>
        </div>
        <c:choose>
        <c:when test="${search != null }">
        <ul class="pagebutton">
        	<c:if test="${page.prev }">
            	<li>
            		<a href="<c:url value="/board/${search }/1"/>">‹‹</a>
            	</li>
            </c:if>
        	<c:if test="${page.cri.pagenum != 1 }">        	
	            <li class="leftbt">
	                <a href="${page.cri.pagenum-1 }" aria-label="Go to befor page">‹</a>
	            </li>
            </c:if>
            <c:forEach var="i" begin="${page.startpage }" end="${page.endpage }">
	            <li class="active">
	            	<a href="<c:url value="/board/${search }/${i}" />" aria-label="Go to page number 1">
	            		<c:choose>
		            		<c:when test="${page.cri.pagenum==i }">
		                		<font class="undefined"><b>${i }</b></font>
		                	</c:when>                	
		                	<c:otherwise>
		                		<font><b>${i }</b></font>
		                	</c:otherwise>
	                	</c:choose>
                	</a>
	            </li>
            </c:forEach>
            <c:if test="${page.cri.pagenum < page.endpage }">
	            <li>
	                <a href="${page.cri.pagenum+1 }" aria-label="Go to after number ">›</a>
	            </li>
            </c:if>            
            <c:if test="${page.next }">
            	<li>
            		<a href="${page.total }">››</a>
            	</li>
            </c:if>
        </ul>
        </c:when>
        <c:otherwise>
        <ul class="pagebutton">
        	<c:if test="${page.prev }">
            	<li>
            		<a href="<c:url value="/board/1"/>">‹‹</a>
            	</li>
            </c:if>
        	<c:if test="${pageNum != 1 }">        	
	            <li class="leftbt">
	                <a href="${pageNum-1 }" aria-label="Go to befor page">‹</a>
	            </li>
            </c:if>
            <c:forEach var="i" begin="${page.startpage }" end="${page.endpage }">
	            <li class="active">
	            	<a href="<c:url value="/board/${i}" />" aria-label="Go to page number 1">
	            		<c:choose>
		            		<c:when test="${pageNum==i }">
		                		<font class="undefined"><b>${i }</b></font>
		                	</c:when>                	
		                	<c:otherwise>
		                		<font><b>${i }</b></font>
		                	</c:otherwise>
	                	</c:choose>
                	</a>
	            </li>
            </c:forEach>
            <c:if test="${pageNum < total_page }">
	            <li>
	                <a href="${pageNum+1 }" aria-label="Go to after number ">›</a>
	            </li>
            </c:if>            
            <c:if test="${page.next }">
            	<li>
            		<a href="${total_page }">››</a>
            	</li>
            </c:if>
        </ul>
        </c:otherwise>
        </c:choose>
    </div>
	<jsp:include page="footer.jsp"/>
</body>
</html>