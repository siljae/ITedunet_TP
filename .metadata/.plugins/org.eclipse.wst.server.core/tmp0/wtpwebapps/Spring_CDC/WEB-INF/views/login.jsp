<%@page import="java.io.PrintWriter"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%	
	//로그인 실패
	String error = request.getParameter("error");
	if (error != null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이메일과 비밀번호가 일치하지 않습니다')");
		script.println("history.back()");
		script.println("</script>");
		
	}
	//회원가입 성공
	String msg = request.getParameter("msg");
	System.out.println("msg: "+msg);
	if(msg == null){}
	else if(msg.equals("1")){
		System.out.println("여기로 오나");
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('회원가입을 축하드립니다! 로그인해주세요!')");
		script.println("</script>");
	}

%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value="/resources/css/login.css"/>">
    <script>
        function show()
        {
            var show = document.getElementById("email");
            if(show.style.display=='none'){
                show.style.display = 'block';
            }
            else{
                show.style.display = 'none';
            }
        }

    </script>
    <title>login</title>
</head>
<body>
    <section>
        <div class="container">
            <div class="login_box">
                <div class="login_logo">
                    <a href="./index.jsp"><img src="<c:url value="/resources/img/logo3.png"/>" alt="Logo"></a>
                </div>
                <div class="login_text">로그인</div>
                <div class="login_text2">간편하게 SNS 로그인</div>
                <div class="login_snsbox">
                    <div class="sns_naver">
                        <a href="#">
                            <img src="<c:url value="/resources/img/login/naver_logo.png"/>" alt="naver">
                            <p>네이버로 로그인</p>
                        </a>
                    </div>
                    <div class="sns_facebook">
                        <a href="#">
                            <img src="<c:url value="/resources/img/login/facebook_logo.JPG"/>" alt="facebook">
                            <p>페이스북으로 로그인</p>
                        </a>
                    </div>
                    <div class="sns_google">
                        <a href="#">
                            <img src="<c:url value="/resources/img/login/google_logo.png"/>" alt="google">
                            <p>구글로 로그인</p>
                        </a>
                    </div>
                </div>
            </div>
            <div class="hr"></div>
            <button class="login_email" onclick="show()">이메일로 로그인</button>
            <div class="email_box" id="email">
                <form  action="./login/chklogin" method="post">
                    <input type="email" class="email" name="email" placeholder="이메일" required="required"/>
                    <br>
                    <input type="password" class="pw" name="pw" placeholder="비밀번호" required="required"/>
                    <br>                    
                    <input type="submit" class="email_login_box" value="로그인">                    
                </form>
                <br>
                <a onclick="window.open('pw_search.html','_blank','width=500,height=200,top=200,left=200')">비밀번호 찾기</a>
            </div>
            <div class="sign">
                <a href="./login/signup">회원가입</a>
            </div>
        </div>
    </section>
</body>
</html>