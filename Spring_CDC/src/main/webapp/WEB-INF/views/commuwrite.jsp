<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html>
<%
	String name = (String)	session.getAttribute("username");
	System.out.println("session의 유저닉네임: "+name);
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>커뮤니티 게시글 등록</title>
    <link rel="stylesheet" href="./resources/css/qnawrite.css">
    <script src="https://kit.fontawesome.com/014e61e9c4.js" crossorigin="anonymous"></script>
    <script>
	    function printfile(){
	        const name = document.getElementById('file1').value;
	        document.getElementById('result').value = name;
	    }
    </script>
</head>
<body>
	<jsp:include page="./header.jsp"/>
    <div class="container">
        <div class="bbubbu">
            <nav class="cos_nav">
                <ul>
                    <li class = "com_name">
                        SHOP
                    </li>
                </ul>
            </nav>
        </div>
    </div>
    <div class="lounge">
        <div class="midlounge">
            <div class="writebox">
                <div id="bodertitlebox">
                    <div class="title" >
                        <h2 class="tltelcss">게시글 작성</h2>
                    </div>
                    <hr class="hrline">
                    <div id="write_area">
                        <form enctype="multipart/form-data" action="./writeaction.action" method="post">
                        	<input type="hidden" name="name" value="<%=name%>">
                            <div id="in_title">
                                <p class="titletxt">제목</p>
                                <select name="animal_type" id="conoption">  
                                      <!-- 나중에 선택은 유효성 검사 통해서 잘못선택했다고 경고창 띄워줌 -->
                                    <option id="opdefult"value="none">반려동물태그</option>                             
                                    <option value="cat">[고양이]</option>
                                    <option value="dog">[강아지]</option>
                                </select>
                                <textarea name="title" id="utitle" rows="1" cols="55" maxlength="100" required></textarea>
                            </div>
                            <hr class="hrline">
                            <div id="in_content">
                                <p class = "contenttxt">내용</p>
                                <textarea name="content" id="ucontent" required></textarea>
                            </div>
                            <hr class="hrline">
                            <div class="filebox">
                                <p class="filetitle">첨부파일1</p>
                                <input class="upload-name" id="result" placeholder="첨부파일"/>
                                <label for="file1"> 파일찾기</label>
                                <input type="file" name="file" id="file1" onchange="printfile()">
                            </div>
                            <hr class="hrline">
                            <hr class="hrline">
                            <div class="bt_se">
                                <button class="subutton" type="submit">글 작성</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
</body>
</html>