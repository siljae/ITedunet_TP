<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    /* footer */
    .footer_box
    {   margin-top: 50px;
        background-color: #ff8fa4;    
    }
    .footer_box img
    {
        width: 100px;
        margin-top: 5px;
        margin-left: 5px;
    }

</style>
<title>Footer</title>
</head>
<body>
    <!-- 하단 -->
    <footer>
	    <div class="footer_box">
	        <a href="./index.jsp"><img src="<c:url value="/resources/img/logo3.png"/>"></a>
	        <a href="#">이용약관</a>
	        <a href="#">개인정보활용</a>                
	    </div>
    </footer>
</body>
</html>