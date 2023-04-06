<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>메뉴</title>
    <style>
        *
        {
            margin: 0;
            padding: 0;
            list-style-type: none;
            text-decoration: none;
            box-sizing: border-box;
            color: black;            
        }
        .container
        {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #f7f7f7;
            border: 1px solid #ddd;
        }
        h1
        {
            margin-top: 0;
            margin-bottom: 50px;
            font-size: 32px;
            text-align: center;
            color: #333;
            position: relative;
        }

        .nav
        {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100px;            
            position: absolute;
            top: 0;
            right: 10%;
            font-size: 20px;
        }
        .nav_li a
        {
            padding: 5px 10px;
            margin-right: 5px;
            padding: 5px 10px; 
        }
        .nav_li a:hover
        {
            background:#555;
            color: #eee;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>게시판보완을 위한 게시판</h1>
        <ul class="nav">
            <li class="nav_li"><a href="./">게시판</a></li>
            <li class="nav_li"><a href="./write">글쓰기</a></li>
        </ul>
    </div>
</body>
</html>