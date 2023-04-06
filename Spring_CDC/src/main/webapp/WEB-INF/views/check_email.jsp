<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 중복 체크</title>
    <style>
        body
        {
            background-color: #fec6ca;
        }
        .email
        {
            height: 20px;
        }
        .email_check
        {
            border: none;
            background-color: #e4e4e6;
            padding: 4px;
            cursor: pointer;
        }
        .email_check_box
        {
            display: flex;
            justify-content: end;
            margin-top: 10px;
            margin-right: 20px;
        }
        .email_submit
        {
            border: 1px solid;
            border-radius: 5px;
            background-color: #ff8ea3;
            padding: 3px 10px;
        }
        .email_close
        {
            border: 1px solid;
            border-radius: 5px;
            background-color: #ff8ea3;
            padding: 3px 10px;
            margin-left: 10px;
        }
    </style>
    <script>
        window.onload = function(){            
            let msg = '${msg}';
            if(msg == null){}
            if (msg == 1){
                alert("해당 이메일은 사용중입니다!\n새로운 이메일을 입력해주세요!");               
            }
            if(msg == 2){
                alert("해당 이메일을 사용할 수 있습니다!");
                let email = document.getElementById('email').value;
                window.close();
                let parentemail = window.opener.document.getElementById('maskemail');
                parentemail.value = email.toString();
                window.opener.emaildecide();
            }
        }    
        function popup_close(){
            window.close();
        }
    </script>
            
    </head>
    <body>
        <div class="container">
            <h2>이메일 중복 체크</h2>
            <form action="<c:url value="/login/chkemail" />" method="post">
                이메일  <input type="email" id="email" name="email" class="email" value="${email}" required
                pattern="^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$"
                title="이메일은 영어와 숫자를 사용해야하며 '-','_','.' 이 사용가능합니다. ex)abc123@abc.com">

                <input type="submit" value="중복확인" class="email_check">
                <br>
            </form>
            <div class="email_check_box">
                <button type="button" onclick="popup_close()" class="email_close">닫기</button>
            </div>
        </div>
    </body>
</html>