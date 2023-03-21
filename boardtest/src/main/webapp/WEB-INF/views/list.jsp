<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/list.css"/>">
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<div class="container">
		<h1>목록</h1>
		<table >
			<tr class="head">
				<td class="head">제목</td>
				<td class="head">작성시간</td>
			</tr>
			<c:forEach items="${boardlist }" var="board">
				<tr>
					<td><a href="./view/${board.num}">글제목 : ${board.title }</a></td>
					<td><a href="./view/${board.num}">작성시간 : ${board.calregist_day }</a>  </td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>