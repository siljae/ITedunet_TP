<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>index</title>
    <script>
    	$(document).ready(function(){
    		let msg = ${msg}
    		if(msg == null){    			
    		}
    		else if(msg == 1){
    			alert("어서오세요! 오늘도 좋은 하루되세요!");
    		}
    		else if(msg == 2){
    			alert("회원가입을 축하합니다!\n로그인해주세요~");
    		}
    	});
    </script>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <!-- 슬라이드 배너 -->
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
                        <a href="#"><img src="<c:url value="/resources/img/index/holong1.jpg"/>" alt="slide01"></a>
                        <a href="#"><img src="<c:url value="/resources/img/index/holong2.jpg"/>" alt="slide02"></a>
                        <a href="#"><img src="<c:url value="/resources/img/index/holong3.jpg"/>" alt="slide03"></a>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- 글 상자 -->
    <section>
        <div class="container posts_box">
            <div class="post_up">
                <div class="up_left">
                    <h5><a href="<c:url value="/notice"/>">More</a></h5>                    
                    <div class="post_box">
                        <h4><a href="<c:url value="/notice"/>">공지사항</a></h4>
                        <ul>
                            <li><a href="#">첫번 째 글</a></li>
                            <li><a href="#">두번 째 글</a></li>
                            <li><a href="#">세번 째 글</a></li>
                        </ul>
                    </div>
                </div>
                <div class="up_right">
                    <h5><a href="<c:url value="/board/recom"/>">More</a></h5>                    
                    <div class="post_box">
                        <h4><a href="<c:url value="/board/recom"/>">추천해요</a></h4>
                        <ul>
                            <li><a href="#">첫번 째 글</a></li>
                            <li><a href="#">두번 째 글</a></li>
                            <li><a href="#">세번 째 글</a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="post_down">
                <div class="down_left">
                    <h5><a href="<c:url value="/board/qna"/>">More</a></h5>                    
                    <div class="post_box">
                        <h4><a href="<c:url value="/board/qna"/>">묻고답하기</a></h4>
                        <ul>
                            <li><a href="#">첫번 째 글</a></li>
                            <li><a href="#">두번 째 글</a></li>
                            <li><a href="#">세번 째 글</a></li>
                        </ul>
                    </div>
                </div>
                <div class="down_right">
                    <h5><a href="<c:url value="/hospital/reviews"/>">More</a></h5>                    
                    <div class="post_box">
                        <h4><a href="<c:url value="/hospital/reviews"/>">동물병원후기</a></h4>
                        <ul>
                            <li><a href="#">첫번 째 글</a></li>
                            <li><a href="#">두번 째 글</a></li>
                            <li><a href="#">세번 째 글</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- shop 광고 -->
    <section>
        <div class="container">
            <div class="index_ad">
                <a href="<c:url value="/notice/event"/>">과아아앙고</a>
            </div>
        </div>
    </section>
    <!-- 하단 -->
    <jsp:include page="footer.jsp"/>
</body>
</html>