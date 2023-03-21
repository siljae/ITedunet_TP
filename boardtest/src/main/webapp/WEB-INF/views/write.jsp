<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/view.css"/>">
<script>
	function printfile(){
		let name = document.getElementById('file').value;
		let filePathSplit = name.split('\\');
		let filePathLength = filePathSplit.length;
		let fileName = filePathSplit[filePathLength-1];
		
		document.getElementById('filename').value = fileName;
	}
	
	let msg = "${msg}";
	if(msg == 1){
		alert("글등록에 성공했습니다");
	}
	else if(msg != 1 && msg == null){
		alert("글등록에 실패했습니다");
	}
	else{
		
	}
</script>
</head>
<body>
	<h1>글쓰기입니다</h1>
	<div class="board-container">
		<form:form action="./write" modelAttribute="board" method="post" enctype="multipart/form-data">
			<label>제목</label>
			<form:input class="board-title" type="text" path="title"/>
			<br>
			<label>내용</label>
			<form:textarea class="board-content" path="content"/>
			<br>
			<label>이미지파일</label>
			<input type="text" id="filename" readonly="readonly">
			<label for="file">찾기</label>
			<form:input id="file" type="file" path="img" onchange="printfile()" style="display:none" />
			<br>
			<input type="submit" value="글등록" >
		</form:form>
	</div>
</body>
</html>