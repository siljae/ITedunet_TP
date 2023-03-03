<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/css/hospitalmap.css"/>">
<title>우리동네동물병원</title>
</head>
<body>
	<jsp:include page="./header.jsp"/>
    <div class ="com_name">
		<h1><a href="<c:url value="/hospital"/>">우리동네동물병원</a></h1>
		<h1><a href="<c:url value="/hospital/reviews"/>">동물병원후기</a></h1>
    </div>
    <div class="container">
        <div style="margin-top: 20px;">
            <div></div>
            <div style="width: 100%;">
                <img style="width: 100%;" src="<c:url value="/resources/img/map/mapAPI.JPG"/>" alt="지도api">
            </div>
        </div>
	</div>
	<jsp:include page="./footer.jsp"/>
</body>
</html>