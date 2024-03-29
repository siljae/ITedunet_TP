<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String username = (String) session.getAttribute("username");
	String lv = (String) session.getAttribute("level");
%>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
/* 초기화 */
* {
	margin: 0;
	padding: 0;
	list-style: none;
	text-decoration: none;
	box-sizing: border-box;
	color: black;
}

body {
	background-color: #fffef1;
}

.hea_con {
	width: 100%;
	height: 100px;
	background: #fec6ca;
	position: fixed;
	z-index: 10;
}

/* nav 시작 */
nav {
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 60%;
	height: 100px;
	font-weight: bold;
	margin: 0 auto;
}

.nav_logo {
	margin-left: 20px;
	width: 200px;
}

.nav_logo img {
	width: 100%;
}

.nav_ul {
	display: flex;
	justify-content: space-around;
	align-items: center;
	width: 50%;
	white-space: nowrap;
}

.nav_login {
	margin-right: 20px;
	border-radius: 5px;
	background-color: #ff8fa4;
	padding: 5px 10px;
}

.member {
	position: relative;
}

.m_ddb {
	position: absolute;
	display: none;
	white-space: nowrap;
	background-color: #fec6ca;
	border-radius: 5px;
	padding: 5px 10px;
}

.m_ddb li {
	margin-bottom: 10px;
}

.admin {
	position: relative;
}

.a_ddb {
	position: absolute;
	display: none;
	white-space: nowrap;
	background-color: #fec6ca;
	border-radius: 5px;
	padding: 5px 10px;
}

.a_ddb li {
	margin-bottom: 10px;
}

.nav_commu:hover .commu_div, .nav_notice:hover ul, .nav_hospital:hover ul,
	.nav_shop:hover ul {
	display: block;
}

.nav_login:hover .loginbox {
	color: #bc9044;
}

.member:hover .m_ddb {
	display: block;
}

.admin:hover .a_ddb {
	display: block;
}
</style>
<title>header</title>
</head>
<body>
	<!-- 상단 -->
	<header>
		<div class="hea_con">
			<nav>
				<div class="nav_logo">
					<a href="./index.jsp"><img src="./resources/img/logo3.png"
						alt="Logo"></a>
				</div>
				<ul class="nav_ul">
					<li class="nav_commu"><a href="./commuboard.action?pageNum=1">커뮤니티</a></li>
					<li class="nav_notice"><a href="#">캣독마당</a></li>
					<li class="nav_hospital"><a href="#">우리동네</a></li>
					<li class="nav_shop"><a href="#">SHOP</a></li>
				</ul>
				<div class="nav_login">
					<%
					if (username == null) {
					%>
					<div class="loginbox">
						<a href="./login.do">로그인</a>
					</div>
					<%
					} else if (username != null) {
					if (lv.equals("1")) {
					%>
					<div class="loginbox">
						<div class="member">
							<a href="#"><%=username%>님</a>
							<ul class="m_ddb">
								<li><a href="#">MyPage</a></li>
								<li><a href="#">주문목록</a></li>
								<li><a href="#">장바구니</a></li>
								<li><a href="#">1:1 채팅창</a></li>
								<li><a href="./commuwrite.action">글쓰기</a></li>
								<li><a href="./logout.jsp">로그아웃</a></li>
							</ul>
						</div>
					</div>
					<%
					} else if (lv.equals("2")) {
					%>
					<div class="loginbox">
						<div class="admin">
							<a href="#"><%=username%>님</a>
							<ul class="a_ddb">
								<li><a href="#">커뮤니티 관리</a></li>
								<li><a href="#">캣독마당 관리</a></li>
								<li><a href="#">우리동네병원 관리</a></li>
								<li><a href="#">회원 관리</a></li>
								<li><a href="#">SHOP 관리</a></li>
								<li><a href="./commuwrite.action">글쓰기</a></li>
								<li><a href="./logout.jsp">로그아웃</a></li>
							</ul>
						</div>
					</div>
					<%
						}
					}
					%>
				</div>
			</nav>
		</div>
	</header>
</body>

</html>