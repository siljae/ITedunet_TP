<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./resources/css/view.css">
<script src="https://kit.fontawesome.com/014e61e9c4.js" crossorigin="anonymous"></script>
<script>
        function chat_show(){
            let show = document.getElementById('chat');
            if(show.style.display == 'none'){
                show.style.display = 'block';
            }
            else{
                show.style.display = 'none';
            }
        }
        
        function write_update(){
        	location.href='commuboardupdate.action?page=${pageNum}';
        }
        function write_dalete(){
        	location.href='commuboarddelete.action?page=${pageNum}';
        }
    </script>
    
<title>커뮤니티 게시판의 게시글</title>
</head>
<body>
	<jsp:include page="./header.jsp"/>
    <section>
        <div class="view_head">
            <h1>우리아이자랑</h1>
        </div>
        <div class="container">
            <div class="view_tag">
            	<img src="${tag_src }">
                <div>${tag_value }</div>
            </div>
            <div>
                <div class="write_head">
                    <h2>${board.title }</h2>
                    <c:if test="${board.name == username }">                                       
                    <div>
                        <button class="btn" onclick="write_update()">수정</button>
                        <button class="btn" onclick="write_delete()">삭제</button>
                    </div>
                    </c:if>
                </div>
                <hr>
                <div class="write_postbox">
                    <div class="postbox_user">
                        <button onclick="chat_show()">
                            ${board.name }                        
                            <div id="chat" class="chat">
                                <a onclick="window.open('chat.html','_blank','width=500,height=500,top=200,left=200')">1:1 채팅하기</a>
                            </div>
                        </button>
                        <p class="postbox_date">${board.regist_day }</p>
                    </div>
                    <c:if test="${board.filename != null }">
	                    <div class="postbox_img">
	                        <img src="./resources/img/${board.filename }" alt="게시글사진">
	                    </div>
                    </c:if>
                    <p class="postbox_text">${board.content }</p>
                    <div>
                        <button class="recom" onclick="recom_sum()">
                            <i class="far fa-thumbs-up"></i>
                            <span>0</span>
                        </button>
                    </div>                
                </div>
                <div class="replybox">
                    <h3>댓글목록</h3>
                    <ul>
                        <li>
                            <div class="re_user_id">
                                <div>찌무맘</div>
                                <div class="rechat">
                                    <a onclick="window.open('chat.html','_blank','width=500,height=500,top=200,left=200')">1:1 채팅하기</a>
                                </div>
                            </div>
                            <div class="re_content">고양이가 너무 귀엽네요~~에구궁 ㅎ</div>
                            <div class="re_date">2022/02/07 11:12</div>
                            <div class="re_btn">
                                <button><i class="far fa-plus-square"></i></button>
                                <button><i class="far fa-window-close"></i></button>
                            </div>
                            <div class="hr"></div>
                        </li>
                        <li>
                            <div class="re_user_id">
                                <div>찌무맘</div>
                                <div class="rechat">
                                    <a onclick="window.open('chat.html','_blank','width=500,height=500,top=200,left=200')">1:1 채팅하기</a>
                                </div>
                            </div>
                            <div class="re_content">고양이가 너무 귀엽네요~~에구궁 ㅎ</div>
                            <div class="re_date">2022/02/07 11:12</div>
                            <div class="re_btn">
                                <button><i class="far fa-plus-square"></i></button>
                                <button><i class="far fa-window-close"></i></button>
                            </div>
                            <div class="hr"></div>
                        </li>
                    </ul>
                    <div>
                        <form action="#" method="post">
                            <!-- 아래 input에 유저아이디를 담아야됨 -->
                            <input type="hidden" name="user_id">
                            <div class="reply_input_box">
                                <textarea >댓글내용</textarea>
                                <div class="inputbox">
                                    <input type="submit" value="등록">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

</body>
</html>