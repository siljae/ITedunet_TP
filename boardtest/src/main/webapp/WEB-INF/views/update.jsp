<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function printfile(){
		let name = document.getElementById('file').value;
		let filePathSplit = name.split('\\');
		let filePathLength = filePathSplit.length;
		let fileName = filePathSplit[filePathLength-1];
		
		document.getElementById('filename').value = fileName;
	}
</script>
</head>
<body>
	<h1>업데이트입니다</h1>
	<form:form action="./${board.num }" modelAttribute="board" method="post" enctype="multipart/form-data">
		<input type="hidden" name="filename" value="${board.imgname }">		
		<label>제목</label>
		<form:input type="text" path="title"/>
		<br>
		<label>내용</label>
		<form:textarea path="content"/>
		<br>
		<label>이미지파일</label>
		<input type="text" id="filename" readonly="readonly" value="${board.imgname }">
		<label for="file">찾기</label>
		<form:input id="file" type="file" path="img" onchange="printfile()" style="display:none" />
		<br>
		<input type="submit" value="글등록" >
	</form:form>
</body>
</html>