<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>커뮤니티 게시글 등록</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/qnawrite.css"/>">
    <script src="https://kit.fontawesome.com/014e61e9c4.js" crossorigin="anonymous"></script>
    <script>
	    function printfile(){
	        let name = document.getElementById('file1').value;
            //파일 전체 경로를 \ 로 나눔
            let filePathSplit = name.split('\\');
            //파일 전체 경로를 \ 로 나눈 길이
            let filePathLength = filePathSplit.length;
            //마지막 경로
            let fileName = filePathSplit[filePathLength-1]
            
	        document.getElementById('result').value =fileName;
	    }
    </script>
</head>
<body>
	<jsp:include page="./header.jsp"/>
    <div class="lounge">
        <div class="midlounge">
            <div class="writebox">
                <div id="bodertitlebox">
                    <div class="title" >
                        <h2 class="tltelcss">게시글 수정</h2>
                    </div>
                    <hr class="hrline">
                    <div id="write_area">
                        <form:form modelAttribute="updateboard" enctype="multipart/form-data" action="./${num }" method="post">
                        	<input type="hidden" name="name" value="${name }"/>
                        	<input type="hidden" name="hit" value="${updateboard.hit }"/>
                        	<input type="hidden" name="filename" value="${updateboard.filename }"/>
                        	<div class="selectbox">
                        		<form:select path="board_type" class="board_type">
                        			<option id="opdefult" >게시판을 선택해주세요</option>
                        			<form:option value="commu">우리아이자랑</form:option>
                        			<form:option value="qna">묻고답하기</form:option>
                        			<form:option value="recom">추천해용</form:option>
                        			<c:if test="${level == 2 }">
                        				<form:option value="notice">공지사항</form:option>
                        			</c:if>
                        			<form:option value="event">이벤트</form:option>
                        			<form:option value="hosreview">동물병원후기</form:option>
                        		</form:select>
                        		<form:select path="animal_type" id="conoption">  
                                      <!-- 나중에 선택은 유효성 검사 통해서 잘못선택했다고 경고창 띄워줌 -->
                                    <option id="opdefult" >반려동물태그를 선택해주세요</option>                             
                                    <form:option value="cat">[고양이]</form:option>
                                    <form:option value="dog">[강아지]</form:option>
                                </form:select>
                        	</div>
                       			
                            <div id="in_title">
                                <p class="titletxt">제목</p>                                
                                <form:textarea path="title" id="utitle" rows="1" cols="55" maxlength="100" required="required" ></form:textarea>
                            </div>
                            <hr class="hrline">
                            <c:if test="${board.filename != null && !empty board.filename}">
			                    <div class="postbox_img">
			                        <img src="<c:url value="/resources/img/board/${updateboard.filename }"/>">
			                    </div>
		                    </c:if>
                            <div id="in_content">
                                <p class = "contenttxt">내용</p>
                                <form:textarea path="content" id="ucontent" value="" required="required"></form:textarea>
                            </div>
                            <hr class="hrline">
                            <div class="filebox">
                                <p class="filetitle">첨부파일1</p>
                                <input class="upload-name" id="result" placeholder="첨부파일" readonly="readonly" value="${updateboard.filename }"/>
                                <label for="file1"> 파일찾기</label>                                                                
                               	<form:input type="file" path="fileimage" id="file1" onchange="printfile()"/>                               
                            </div>
                            <hr class="hrline">
                            <hr class="hrline">
                            <div class="bt_se">
                                <button class="subutton" type="submit">글 작성</button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>