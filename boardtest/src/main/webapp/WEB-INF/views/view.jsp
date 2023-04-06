<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/view.css"/>">
</head>
<body>
    <div class="board-container">
        <h1 class="board-title">${board.title}</h1>
        <p class="board-content">${board.content}</p>
        <div class="board-image">
          <img alt="" src="<c:url value='/resources/img/${board.imgname}'/>">
        </div>
        <div class="board-buttons">
          <a href="<c:url value="/"/>">돌아가기</a>
          <a href="<c:url value="/update/${board.num}"/>">수정</a>
          <a href="<c:url value="/delete/${board.num}"/>">삭제</a>
        </div>
      </div>
</body>
</html>