<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*" %>
<%@ page import="mvc.model.boardDTO" %>
<% 
	String username = (String) session.getAttribute("username");
	System.out.println("게시판의닉넴: "+username);
	List boardlist = (List)request.getAttribute("boardlist");
	System.out.println("List: "+boardlist);
	int total_record = ((Integer) request.getAttribute("total_record")).intValue();
	System.out.println("전체글: "+total_record);
	int total_page = ((Integer) request.getAttribute("total_page")).intValue();
	System.out.println("전체페이지: "+total_page);
	int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
	System.out.println("현재페이지넘버: "+pageNum);
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/board.css">
	<title>커뮤니티 게시판</title>
</head>
<body>
	<jsp:include page="./header.jsp"/>
    <div class ="com_name">
        <h1>우리아이자랑</h1>
    </div>
    <div class="container">
        <div class="midbox">
            <div class="seabox">
                <div class="search">
                    <input type="text" placeholder="찾으시는 글이 있으신가요?" maxlength="130" class="com_search" enterkeyhint="search" value="">
                    <button class="button" type="submit" >
                        <img src="./resources/img/seabut.png" alt="search">
                    </button>
                </div>
                <div class="textbox" id="hotline">인기글</div>
                <div class="bestlist">
                    <div class="bestbox">
                        <div class="list1">
                            <div class="listlabel">
                                <div class="labelname">
                                    <img class="catface" src="./resources/img/catface.png" alt="">
                                    <div class="cattext">고양이
                                    </div>
                                </div>
                                <div class="listhead">
                                    울집고양이귀엽
                                </div>
                                <div class="listtext">
                                    대충 우리집 고양이가 귀엽다는 말
                                </div>
                                <div class="reply">
                                    <span class="re1">
                                        답변 
                                        3
                                    </span>
                                    <span class="day">2023.02.07</span>
                                </div>
                                <div class="listgo">
                                    <a href="#" class="gobutton">바로가기</a>
                                </div>
                            </div>
                        </div>
                        <div class="list1">
                            <div class="listlabel">
                                <div class="labelname">
                                    <img class="dogface" src="./resources/img/dogface.png" alt="">
                                    <div class="dog">강아지</div>
                                </div>
                                <div class="listhead">
                                    울집 강아지 왜이럼?
                                </div>
                                <div class="listtext">
                                    대충 우리집 강아지가 귀엽다는 말
                                </div>
                                <div class="reply">
                                    <span class="re1">
                                        답변 
                                        3
                                    </span>
                                    <span class="day">2023.02.07</span>
                                </div>
                                <div class="listgo">
                                    <a href="#" class="gobutton">바로가기</a>
                                </div>
                            </div>
                        </div>
                        <div class="list1">
                            <div class="listlabel">
                                <div class="labelname">
                                    <img src="./resources/img/catface.png" alt="">
                                    <div class="cattext">고양이</div>
                                </div>
                                <div class="listhead">
                                    울집 고양이가 낙법을 써요ㅇㅇㅇㅇ
                                </div>
                                <div class="listtext">
                                    대충 우리집 고양이가 귀엽다는 말
                                </div>
                                <div class="reply">
                                    <span class="re1">
                                        답변 
                                        3
                                    </span>
                                    <span class="day">2023.02.07</span>
                                </div>
                                <div class="listgo">
                                    <a href="#" class="gobutton">바로가기</a>
                                </div>
                            </div>
                        </div>
                        <div class="list1">
                            <div class="listlabel">
                                <div class="labelname">
                                    <img class="dogface" src="./resources/img/dogface.png" alt="">
                                    <div class="dog">강아지</div>
                                </div>
                                <div class="listhead">
                                    울집 강아지 왜이럼?
                                </div>
                                <div class="listtext">
                                    대충 우리집 강아지가 귀엽다는 말
                                </div>
                                <div class="reply">
                                    <span class="re1">
                                        답변 
                                        3
                                    </span>
                                    <span class="day">2023.02.07</span>
                                </div>
                                <div class="listgo">
                                    <a href="#" class="gobutton">바로가기</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr class="borderline">
        <div class="loungebox">
            <div class="filterbox">
                <div class="filterbt">
                    <div class="radiobox">
                        <input class="radiobt" type="radio" id="newest" name="question" value="최신순" checked>
                        <label for="newest">최신순</label>
                    </div>
                </div>
                <div class="filterbt">
                    <div class="radiobox">
                        <input class="radiobt" type="radio" id="popular" name="question" value="인기순">
                        <label for="popular">인기순</label>
                    </div>
                </div>
                <div class="filterbt">
                    <div class="radiobox">
                        <input class="radiobt" type="radio" id="views" name="question" value="조회순">
                        <label for="views">조회순</label>
                    </div>
                </div>
                <div class="writebox">
                    <a href="./commuwrite.action" class="write">글쓰기</a>
                </div>
            </div>
            <div class="loungelist">
                <div class="conlist">
                <%
                	for(int i = 0; i<boardlist.size();i++){
                		boardDTO board = (boardDTO) boardlist.get(i);
                	
                %>
                    <div class="content">
                        <div class="colist">
                            <div>
                                <div class="colbt">
                                    <img src="./resources/img/catface.png" alt="">
                                    <div class="cattext1">고양이</div>
                                </div>
                                <div class="coltitle"><%=board.getTitle() %></div>
                                <div class="coltext"><%=board.getContent() %></div>
                            </div>
                        </div>
                        <div class="coreply">
                            <div class="core1">
                                답변 :
                                0
                            </div>
                            <div class="renickname"><%=board.getName() %></div>
                            <div class="retime">59분 전</div>
                        </div>
                        <hr class="listgard">
                    </div>
				<%
					}
				%>
                    <div class="content">
                        <div class="colist">
                            <div>
                                <div class="colbt">
                                    <img src="./resources/img/dogface.png" alt="">
                                    <div class="cattext1">강아지</div>
                                </div>
                                <div class="listbox">
                                    <div class="cotextbox">
                                        <div class="coltitle">반려동물 침대에서 함께자나요?</div>
                                        <div class="coltext">안녕하세요. 3년차 강아지 키우고 있는 아빠입니다. 처음 데려올 땐 "사람과 동물이 어디 한 침대야~" 라며 절대불가를 외쳤지만 지금은 잘때 한 방에 없으면 서운하고 꼭  불러서 침대로 오게 만드는 라이프를 살고 있습니다. 워낙 첨엔 숙면을 잘 못취하기도 하고, 불편했는데 그냥 매번 올라오는 강아지를 떨쳐낼 수가 없어 고민을 해결하다 못해 이런 가구는 어떨까 하며 여기까지 생각이 오게 되었습니다 ^^ 그냥 거창한 설문 조사는 아니고, 주변 반려인들께서는 어떤 잠자리를 하고 계실까 다른 반려동물들은 어떻게 침대에서 생활할까 궁금하기도 하고 자료가 필요하기도 하여 서투른 질문지들을 작성하여 이렇게 요청을 드립니다. 가벼운 마음으로, 작성해주시면 정말 감사하겠습니다. 추첨 통해 스타벅스 기프티콘 드려요! </div>
                                    </div>
                                    <div class="imgbox">
                                        <img class="listimg" src="./resources/img/ggimu.jpg" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="coreply">
                            <div class="core1">
                                답변 :
                                0
                            </div>
                            <div class="renickname">봉봉</div>
                            <div class="retime">2시간 전</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <ul class="pagebutton">
            <li class="leftbt">
                <a href="#" aria-label="Go to befor page">‹</a>
            </li>
            <li class="active">
                <a class="undefined" href="#" aria-label="Go to page number 1">1</a>
            </li>
            <li class>
                <a href="#" aria-label="Go to page number 2">2</a>
            </li>
            <li class>
                <a href="#" aria-label="Go to page number 3">3</a>
            </li>
            <li class>
                <a href="#" aria-label="Go to page number 4">4</a>
            </li>
            <li class>
                <a href="#" aria-label="Go to page number 5">5</a>
            </li>
            <li class>
                <a href="#" aria-label="Go to after number ">›</a>
            </li>
        </ul>
    </div>
	<jsp:include page="footer.jsp"/>
</body>
</html>