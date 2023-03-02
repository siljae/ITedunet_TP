<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%
	String username = (String) session.getAttribute("username");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
    <title>index</title>
    <script>

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
                    <h5><a href="#">More</a></h5>                    
                    <div class="post_box">
                        <h4>우리아이 자랑 인기글</h4>
                        <ul>
                            <li><a href="#">첫번 째 글</a></li>
                            <li><a href="#">두번 째 글</a></li>
                            <li><a href="#">세번 째 글</a></li>
                        </ul>
                    </div>
                </div>
                <div class="up_right">
                    <h5><a href="#">More</a></h5>                    
                    <div class="post_box">
                        <h4>우리아이 자랑 인기글</h4>
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
                    <h5><a href="#">More</a></h5>                    
                    <div class="post_box">
                        <h4>우리아이 자랑 인기글</h4>
                        <ul>
                            <li><a href="#">첫번 째 글</a></li>
                            <li><a href="#">두번 째 글</a></li>
                            <li><a href="#">세번 째 글</a></li>
                        </ul>
                    </div>
                </div>
                <div class="down_right">
                    <h5><a href="#">More</a></h5>                    
                    <div class="post_box">
                        <h4>우리아이 자랑 인기글</h4>
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
                <a href="#">과아아앙고</a>
            </div>
        </div>
    </section>
    <!-- 하단 -->
    <jsp:include page="footer.jsp"/>
</body>
</html>